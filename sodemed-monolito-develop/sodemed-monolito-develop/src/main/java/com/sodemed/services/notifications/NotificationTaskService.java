package com.sodemed.services.notifications;

import com.sodemed.dtos.notifications.request.DtoRequestNotificationTask;
import com.sodemed.dtos.notifications.response.DtoResponseNotificationTask;

public interface NotificationTaskService {

    DtoResponseNotificationTask create(DtoRequestNotificationTask dtoRequestNotificationTask);

    DtoResponseNotificationTask update(long id, DtoRequestNotificationTask dtoRequestNotificationTask);

    void sedNotificationTask();

}