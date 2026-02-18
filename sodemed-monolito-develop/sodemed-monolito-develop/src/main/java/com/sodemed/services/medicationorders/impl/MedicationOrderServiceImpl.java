package com.sodemed.services.medicationorders.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.sodemed.dtos.medicationorders.request.DtoRequestMedicationOrder;
import com.sodemed.dtos.medicationorders.request.RequestUpdate;
import com.sodemed.dtos.medicationorders.response.DtoResponseMedicationOrder;
import com.sodemed.exceptions.InvalidTakeException;
import com.sodemed.exceptions.NotCreateException;
import com.sodemed.exceptions.NotFoundException;
import com.sodemed.exceptions.NotUpdateException;
import com.sodemed.models.medicationorders.MedicalOrderRequestMovement;
import com.sodemed.models.medicationorders.MedicationOrder;
import com.sodemed.models.medicationorders.enums.StatusOrder;
import com.sodemed.models.notifications.NotificationTask;
import com.sodemed.models.notifications.enums.TypeNotification;
import com.sodemed.models.users.Client;
import com.sodemed.repositories.medicationorders.MedicationOrderRepository;
import com.sodemed.repositories.users.ClientRepository;
import com.sodemed.services.medicationorders.MedicalOrderRequestMovementService;
import com.sodemed.services.medicationorders.MedicationOrderService;
import com.sodemed.services.notifications.NotificationTaskService;
import com.sodemed.utils.medicationorders.FileUpload;
import com.sodemed.utils.medicationorders.FolderHandler;
import com.sodemed.utils.medicationorders.MedicationOrderMapper;
import com.sodemed.utils.notifications.NotificationTaskMapper;

@Service
public class MedicationOrderServiceImpl implements MedicationOrderService {

    @Autowired
    private MedicationOrderRepository medicationOrderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MedicalOrderRequestMovementService medicalOrderMovementService;
    @Autowired
    private NotificationTaskService notificationTaskService;

    @Override
    public DtoResponseMedicationOrder beforeCreate(String jsonDtoMedicationOrder, List<MultipartFile> files) {
        Gson gson = new Gson();
        DtoRequestMedicationOrder dtoRequestMedicationOrder = gson.fromJson(jsonDtoMedicationOrder,
                DtoRequestMedicationOrder.class);
        FolderHandler folderHandler = new FolderHandler();
        String globalPath = folderHandler.preparePath();
        if (!folderHandler.createFolders(globalPath)) {
            throw new NotCreateException("No se pudo crear la solicitud");
        }
        Map<String, String> fileName = afterCreate(files, null);
        dtoRequestMedicationOrder.setUrlFiles(globalPath);
        dtoRequestMedicationOrder
                .setUrlIdentificationFile(fileName.get(dtoRequestMedicationOrder.getUrlIdentificationFile()));
        dtoRequestMedicationOrder
                .setUrlMedicalHistoryFile(fileName.get(dtoRequestMedicationOrder.getUrlMedicalHistoryFile()));
        dtoRequestMedicationOrder
                .setUrlMedicationOrderFile(fileName.get(dtoRequestMedicationOrder.getUrlMedicationOrderFile()));
        // return create(dtoRequestMedicationOrder);
        return null;
    }

    @Override
    public DtoResponseMedicationOrder create(DtoRequestMedicationOrder requestMedicationOrder,
            MultipartFile fileIdentification, MultipartFile fileMedicationOrder, MultipartFile fileMedicalHistory) {
        try {
            Client client = this.clientRepository.findByIdentification(requestMedicationOrder.getUserCreate())
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            MedicationOrder medicationOrder = MedicationOrderMapper.medicaltionOrderToEntity(requestMedicationOrder);
            medicationOrder.setStatus(StatusOrder.pending);
            medicationOrder.setActive(true);
            medicationOrder.setUserCreate(client);
            if (medicationOrder != null) {
                FolderHandler folderHandler = new FolderHandler();
                String globalPath = folderHandler.preparePath();
                if (!folderHandler.createFolders(globalPath)) {
                    throw new NotCreateException("No se pudo crear la solicitud");
                }
                List<MultipartFile> files = new ArrayList<>();
                files.add(fileIdentification);
                files.add(fileMedicationOrder);
                files.add(fileMedicalHistory);
                String prefix = this.formatStringsWithUnderscores(
                        requestMedicationOrder.getUserOrderName(),
                        requestMedicationOrder.getUserOrderIdentification(),
                        this.formatDate(requestMedicationOrder.getCreationDate(), "yyyy_MM_dd"));
                Map<String, String> fileName = afterCreate(files, prefix);
                medicationOrder.setUrlFiles(globalPath);
                medicationOrder
                        .setUrlIdentificationFile(fileName.get(fileIdentification.getOriginalFilename()));
                medicationOrder
                        .setUrlMedicalHistoryFile(fileName.get(fileMedicalHistory.getOriginalFilename()));
                medicationOrder
                        .setUrlMedicationOrderFile(fileName.get(fileMedicationOrder.getOriginalFilename()));
            }
            medicationOrder.setObservations("Solicitud creada con éxito");
            this.medicationOrderRepository.save(medicationOrder);
            resolveNotificationTask(medicationOrder.getStatus(), medicationOrder.getId(),
                    medicationOrder.getUserCreate().getId());
            return MedicationOrderMapper.entityToDtoMedicaltionOrder(medicationOrder);
        } catch (Exception e) {
            throw new NotCreateException("No se pudo crear la solicitud");
        }
    }

    @Override
    public Map<String, String> afterCreate(List<MultipartFile> files, String prefix) {
        Map<String, String> fileName = new HashMap<String, String>();
        files.stream().forEach(file -> {
            String newNameFile = FileUpload.FileSave(file, prefix);
            fileName.put(file.getOriginalFilename(), newNameFile);
        });
        return fileName;
    }

    @Override
    public DtoResponseMedicationOrder update(long id, DtoRequestMedicationOrder dtoRequestMedicationOrder) {
        try {
            MedicationOrder medicationOrder = this.medicationOrderRepository
                    .save(MedicationOrderMapper.medicaltionOrderToEntity(dtoRequestMedicationOrder));
            return MedicationOrderMapper.entityToDtoMedicaltionOrder(medicationOrder);
        } catch (Exception e) {
            throw new NotUpdateException("No se pudo actualizar la solicitud");
        }
    }

    @Override
    public List<DtoResponseMedicationOrder> findAll(String start_date, String end_Date, StatusOrder statusOrder,
            String identification) {
        try {
            Date date_init = null;
            Date date_final = null;
            if (start_date != null && !start_date.isEmpty()) {
                date_init = this.parseDate(start_date, "yyyy-MM-dd");
            }
            if (end_Date != null && !end_Date.isEmpty()) {
                end_Date = end_Date + "T23:59:00Z";
                date_final = this.parseDate(end_Date, "yyyy-MM-dd'T'HH:mm:ss'Z'");
            }
            return this.medicationOrderRepository
                    .fetchFiltersAll(date_init, date_final, statusOrder,
                            identification)
                    .stream()
                    .map(MedicationOrderMapper::entityToDtoMedicaltionOrder).collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("No se encontró solicitudes");
        }
    }

    @Override
    public DtoResponseMedicationOrder delete(long id) {
        try {
            MedicationOrder medicationOrder = this.medicationOrderRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró solicitud con el ID " + id));
            medicationOrder.setActive(false);
            return MedicationOrderMapper
                    .entityToDtoMedicaltionOrder(this.medicationOrderRepository.save(medicationOrder));
        } catch (Exception e) {
            throw new NotFoundException("No se pudo eliminar la solicitud con ID " + id);
        }
    }

    @Override
    public DtoResponseMedicationOrder findById(long id) {
        try {
            MedicationOrder medicationOrder = this.medicationOrderRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró solicitud con el ID " + id));
            return MedicationOrderMapper.entityToDtoMedicaltionOrder(medicationOrder);
        } catch (Exception e) {
            throw new NotFoundException("No se encontró solicitud con el ID " + id);
        }
    }

    private String formatStringsWithUnderscores(String... parts) {
        return Arrays.stream(parts)
                .map(part -> part.replaceAll(":", "_").replaceAll("\\s+", "_").toLowerCase())
                .collect(Collectors.joining("_"));
    }

    private String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    private Date parseDate(String date, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new NotFoundException("Sucedió un error inesperado");
        }
    }

    @Override
    public List<DtoResponseMedicationOrder> findAllByUser(long idUserCreate, StatusOrder statusOrder) {
        try {
            List<MedicationOrder> orders = statusOrder != null
                    ? this.medicationOrderRepository.findByUserCreate_IdAndStatusAndActive(idUserCreate, statusOrder,
                            true)
                    : this.medicationOrderRepository.findByUserCreate_IdAndActive(idUserCreate, true);
            List<DtoResponseMedicationOrder> dtoOrders = orders.stream()
                    .map(MedicationOrderMapper::entityToDtoMedicaltionOrder)
                    .collect(Collectors.toList());
            return dtoOrders;
        } catch (Exception e) {
            throw new NotFoundException("No se encontró solicitudes");
        }
    }

    public Page<DtoResponseMedicationOrder> findAllPageable(String start_date, String end_Date, StatusOrder statusOrder,
            String identification, int page, int size) {
        try {
            Date date_init = null;
            Date date_final = null;
            if (start_date != null && !start_date.isEmpty()) {
                date_init = this.parseDate(start_date, "yyyy-MM-dd");
            }
            if (end_Date != null && !end_Date.isEmpty()) {
                end_Date = end_Date + "T23:59:00Z";
                date_final = this.parseDate(end_Date, "yyyy-MM-dd'T'HH:mm:ss'Z'");
            }
            Pageable pageable = PageRequest.of(page - 1, size);
            return this.medicationOrderRepository
                    .fetchFilters(date_init, date_final, statusOrder,
                            identification, pageable)
                    .map(MedicationOrderMapper::entityToDtoMedicaltionOrder);
        } catch (Exception e) {
            throw new NotFoundException("No se encontró solicitudes");
        }
    }

    @Override
    public DtoResponseMedicationOrder updateWhitMovement(long id, DtoRequestMedicationOrder dtoRequestMedicationOrder,
            Long idEmployee, String nameEmployee) {
        try {
            MedicationOrder medicationOrder = this.medicationOrderRepository
                    .save(MedicationOrderMapper.medicaltionOrderToEntity(dtoRequestMedicationOrder));
            this.resolveMedicalOrderMovement(new Date(), idEmployee, nameEmployee, medicationOrder.getId(),
                    medicationOrder.getStatus(), null, false, 0, null);
            this.resolveNotificationTask(medicationOrder.getStatus(), medicationOrder.getId(),
                    medicationOrder.getUserCreate().getId());
            return MedicationOrderMapper.entityToDtoMedicaltionOrder(medicationOrder);
        } catch (Exception e) {
            throw new NotUpdateException("No se pudo actualizar la solicitud");
        }
    }

    @Override
    public DtoResponseMedicationOrder takeMedicationOrder(long id, Long idEmployee, String nameEmployee) {
        try {
            MedicationOrder medicationOrder = this.medicationOrderRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró solicitud con el ID " + id));
            if (!medicationOrder.getStatus().equals(StatusOrder.pending)) {
                throw new InvalidTakeException("Error al tomar la solicitud");
            }
            medicationOrder.setStatus(StatusOrder.processing);
            this.medicationOrderRepository.save(medicationOrder);
            this.resolveMedicalOrderMovement(new Date(), idEmployee, nameEmployee, medicationOrder.getId(),
                    medicationOrder.getStatus(), null, false, 0, null);
            return MedicationOrderMapper.entityToDtoMedicaltionOrder(medicationOrder);
        } catch (InvalidTakeException invalidTakeException) {
            throw new InvalidTakeException(invalidTakeException.getMessage());
        } catch (Exception e) {
            throw new NotFoundException("Error al tomar la solicitud");
        }
    }

    private void resolveMedicalOrderMovement(Date effDate, Long idEmployee, String nameEmployee,
            Long medicationOrder, StatusOrder statusOrder, String observations, boolean partial, int totalPending,
            String detailsPending) {
        MedicalOrderRequestMovement medicalOrderRequestMovement = new MedicalOrderRequestMovement(effDate,
                idEmployee, nameEmployee, medicationOrder, statusOrder, observations, partial, totalPending,
                detailsPending);
        this.medicalOrderMovementService.create(medicalOrderRequestMovement);
    }

    @Override
    public List<DtoResponseMedicationOrder> findAllByUserHistory(long idUserCreate) {
        try {
            List<MedicationOrder> orders = this.medicationOrderRepository.findByUserCreate_IdAndStatusNot(idUserCreate,
                    StatusOrder.pending);
            List<DtoResponseMedicationOrder> dtoOrders = orders.stream()
                    .map(MedicationOrderMapper::entityToDtoMedicaltionOrder)
                    .collect(Collectors.toList());
            return dtoOrders;
        } catch (Exception e) {
            throw new NotFoundException("No se encontró solicitudes");
        }
    }

    @Override
    public DtoResponseMedicationOrder partialUpdateMedicationOrder(long id, RequestUpdate requestUpdate) {
        try {
            MedicationOrder medicationOrder = this.medicationOrderRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró solicitud con el ID " + id));
            medicationOrder.setStatus(requestUpdate.getStatusOrder());
            medicationOrder.setIdEmployee(requestUpdate.getIdEmployee());
            medicationOrder.setNameEmployee(requestUpdate.getNameEmployee());
            medicationOrder.setObservations(requestUpdate.getComments());
            medicationOrder.setPartial(requestUpdate.isPartial());
            medicationOrder.setTotalPending(requestUpdate.getTotalPending());
            medicationOrder.setDetailsPending(requestUpdate.getDetailsPending());
            Date now = new Date();
            medicationOrder.setLastModificationDate(now);
            medicationOrder = this.medicationOrderRepository.save(medicationOrder);
            this.resolveMedicalOrderMovement(now, requestUpdate.getIdEmployee(), requestUpdate.getNameEmployee(),
                    medicationOrder.getId(), medicationOrder.getStatus(), requestUpdate.getComments(),
                    requestUpdate.isPartial(), requestUpdate.getTotalPending(), requestUpdate.getDetailsPending());

            this.resolveNotificationTask(medicationOrder.getStatus(), medicationOrder.getId(),
                    medicationOrder.getUserCreate().getId());

            return MedicationOrderMapper.entityToDtoMedicaltionOrder(medicationOrder);
        } catch (InvalidTakeException invalidTakeException) {
            throw new InvalidTakeException(invalidTakeException.getMessage());
        } catch (Exception e) {
            throw new NotFoundException("Error al procesar la solicitud");
        }
    }

    @Override
    public ResponseEntity<Resource> loadResource(String filename, String mediaLocation) {
        try {
            Path rootLocation = Paths.get(mediaLocation);
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new NotFoundException("Recurso no encontrado");
            }
            String contentType = Files.probeContentType(resource.getFile().toPath());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(resource);
        } catch (Exception e) {
            throw new NotFoundException("Recurso no encontrado");
        }
    }

    public void resolveNotificationTask(StatusOrder statusOrder, long idMedicationOrder, long idClient) {
        if(!statusOrder.equals(StatusOrder.delivered)) {
            NotificationTask notificationTask = new NotificationTask();
            notificationTask.setCreationDate(new Date());
            notificationTask.setIdMedicationOrder(idMedicationOrder);
            notificationTask.setType(TypeNotification.email);
            notificationTask.setIdClient(idClient);
            notificationTask.setStatusOrder(statusOrder);
            this.notificationTaskService.create(NotificationTaskMapper.entityToDtoReques(notificationTask));
        }
    }

}
