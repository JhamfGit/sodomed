package com.sodemed.services.notifications.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodemed.dtos.notifications.request.DtoRequestNotificationTask;
import com.sodemed.dtos.notifications.response.DtoResponseNotificationTask;
import com.sodemed.exceptions.InvalidSendEmailException;
import com.sodemed.exceptions.NotCreateException;
import com.sodemed.exceptions.NotFoundException;
import com.sodemed.exceptions.NotUpdateException;
import com.sodemed.models.medicationorders.MedicationOrder;
import com.sodemed.models.notifications.NotificationTask;
import com.sodemed.models.users.User;
import com.sodemed.repositories.email.EmailConfigurationRepository;
import com.sodemed.repositories.medicationorders.MedicationOrderRepository;
import com.sodemed.repositories.notifications.NotificationTaskRepository;
import com.sodemed.repositories.users.UserRepository;
import com.sodemed.services.email.EmailService;
import com.sodemed.services.notifications.NotificationTaskService;
import com.sodemed.utils.email.EmailMeticationOrderSendTemplate;
import com.sodemed.utils.notifications.NotificationTaskMapper;

@Service
public class NotificationTasKServiceImpl implements NotificationTaskService {

    @Autowired
    NotificationTaskRepository notificationTaskRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MedicationOrderRepository medicationOrderRepository;
    @Autowired
    private EmailConfigurationRepository emailConfigurationRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public DtoResponseNotificationTask create(DtoRequestNotificationTask dtoRequestNotificationTask) {
        try {
            NotificationTask notificationTask = NotificationTaskMapper.dtoRequestToEntity(dtoRequestNotificationTask);
            notificationTask.setSent(false);
            return NotificationTaskMapper.entityToDtoResponse(this.notificationTaskRepository
                    .save(notificationTask));
        } catch (NotCreateException e) {
            throw new NotCreateException("Proceso de notificación no creado");
        } catch (Exception e) {
            throw new NotCreateException("Proceso de notificación no creado");
        }
    }

    @Override
    public DtoResponseNotificationTask update(long id, DtoRequestNotificationTask dtoRequestNotificationTask) {
        try {
            NotificationTask notificationTaskExist = this.notificationTaskRepository.findById(id)
                    .orElseThrow(() -> new NotUpdateException("Proceso de notificación no encontrado"));
            notificationTaskExist = NotificationTaskMapper.dtoRequestToEntity(dtoRequestNotificationTask);
            notificationTaskExist.setId(id);
            return NotificationTaskMapper
                    .entityToDtoResponse(this.notificationTaskRepository.save(notificationTaskExist));
        } catch (Exception e) {
            throw new NotUpdateException("No se pudo actualizar el proceso de notificación");
        }
    }

    @Override
    public void sedNotificationTask() {
        try {
            List<NotificationTask> notificationTaskList = this.notificationTaskRepository.findBySent(false);
            if (notificationTaskList != null && !notificationTaskList.isEmpty()) {
                notificationTaskList.stream().forEach(notificationTask -> {
                    try {
                        Optional<User> client = getClient(notificationTask);
                        //consultar orden y sacar la informacion
                        Optional medicationOrder = medicationOrderRepository.findById(notificationTask.getIdMedicationOrder());
                        sendEmail(notificationTask, client, (MedicationOrder) medicationOrder.get());
                        updateNotificationTask(notificationTask);
                    } catch (Exception e) {
                        throw new InvalidSendEmailException("Error al enviar el correo para la tarea: "
                                + notificationTask.getId() + " - " + e.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            throw new NotFoundException("Tarea de notificacion no encontrada");
        }

    }

    private void updateNotificationTask(NotificationTask notificationTask) {
        notificationTask.setSent(true);
        notificationTaskRepository.save(notificationTask);
    }

    private void sendEmail(NotificationTask notificationTask, Optional<User> client, MedicationOrder medicationOrder) {
        String subject = "";
        String title = "";
        switch (notificationTask.getStatusOrder()) {
            case processing:
            title = "¡Tu solicitud de medicamentos se está procesando!!";
            subject = "Solicitud de Orden de medicamento en proceso";
                break;
            case accept:
            title = "¡Tu solicitud de medicamentos ha sido aceptada!";
            subject = "Solicitud de Orden de medicamento aceptada";
                break;
            case send:
            title = "¡Tu solicitud de medicamentos ha sido enviada!";
            subject = "Solicitud de Orden de medicamento  enviada";
                break;
            case reject:
            title = "¡Tu solicitud de medicamentos ha sido cancelada!";
            subject = "Solicitud de Orden de medicamento cancelada";
                break;
            default:
            title = "¡Tu solicitud de medicamentos se ha registrado correctamente!";
            subject = "Solicitud de orden de medicamentos registrada";
                break;
        }
        String eff_date = simpleDateFormat.format(medicationOrder.getCreationDate());
        String orderNumber = String.valueOf(notificationTask.getIdMedicationOrder());
        String totalPending = String.valueOf(medicationOrder.getTotalPending());
        String detailsPending = String.valueOf(medicationOrder.getDetailsPending());
        String email = client.get().getEmail(); 
        String userName = client.get().getName().concat(" ").concat(client.get().getLastName());
        String body = EmailMeticationOrderSendTemplate.getTemplate(title, userName, eff_date, 
            orderNumber, medicationOrder.getObservations() != null ? medicationOrder.getObservations(): "",totalPending, detailsPending, medicationOrder.isPartial());
         boolean emailSent = emailService.sendEmailV2(subject, body, email);
        if (!emailSent) {
            throw new InvalidSendEmailException(
                    "Error al enviar el correo para la tarea: " + notificationTask.getId());
        }
    }

    private Optional<User> getClient(NotificationTask notificationTask) {
        Optional<User> client = this.userRepository.findById(notificationTask.getIdClient());
        if (client.isEmpty()) {
            throw new InvalidSendEmailException(
                    "Error al enviar el correo para el cliente, cliente no encontrado: "
                            + notificationTask.getIdClient());
        }
        return client;
    }


}
