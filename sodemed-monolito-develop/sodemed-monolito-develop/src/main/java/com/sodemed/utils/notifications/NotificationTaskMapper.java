package com.sodemed.utils.notifications;

import com.sodemed.dtos.notifications.request.DtoRequestNotificationTask;
import com.sodemed.dtos.notifications.response.DtoResponseNotificationTask;
import com.sodemed.models.notifications.NotificationTask;

public class NotificationTaskMapper {

    public static DtoResponseNotificationTask entityToDtoResponse(NotificationTask notificationTask) {
        DtoResponseNotificationTask dtoResponseNotificationTask = new DtoResponseNotificationTask();
        dtoResponseNotificationTask.setId(notificationTask.getId());
        dtoResponseNotificationTask.setCreationDate(notificationTask.getCreationDate());
        dtoResponseNotificationTask.setIdMedicationOrder(notificationTask.getIdMedicationOrder());
        dtoResponseNotificationTask.setType(notificationTask.getType());
        dtoResponseNotificationTask.setSent(notificationTask.isSent());
        dtoResponseNotificationTask.setIdCLient(notificationTask.getIdClient());
        dtoResponseNotificationTask.setStatusOrder(notificationTask.getStatusOrder());
        return dtoResponseNotificationTask;
    }

    public static NotificationTask dtoRequestToEntity(DtoRequestNotificationTask dtoRequestNotificationTask) {
        NotificationTask notificationTask = new NotificationTask();
        notificationTask.setCreationDate(dtoRequestNotificationTask.getCreationDate());
        notificationTask.setIdMedicationOrder(dtoRequestNotificationTask.getIdMedicationOrder());
        notificationTask.setType(dtoRequestNotificationTask.getType());
        notificationTask.setSent(dtoRequestNotificationTask.isSent());
        notificationTask.setIdClient(dtoRequestNotificationTask.getIdClient());
        notificationTask.setStatusOrder(dtoRequestNotificationTask.getStatusOrder());
        return notificationTask;
    }

    public static DtoRequestNotificationTask entityToDtoReques(NotificationTask notificationTask) {
        DtoRequestNotificationTask dtoRequestNotificationTask = new DtoRequestNotificationTask();
        dtoRequestNotificationTask.setCreationDate(notificationTask.getCreationDate());
        dtoRequestNotificationTask.setIdMedicationOrder(notificationTask.getIdMedicationOrder());
        dtoRequestNotificationTask.setType(notificationTask.getType());
        dtoRequestNotificationTask.setSent(notificationTask.isSent());
        dtoRequestNotificationTask.setIdClient(notificationTask.getIdClient());
        dtoRequestNotificationTask.setStatusOrder(notificationTask.getStatusOrder());
        return dtoRequestNotificationTask;
    }
}
