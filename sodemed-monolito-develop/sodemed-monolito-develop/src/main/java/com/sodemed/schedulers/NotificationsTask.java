package com.sodemed.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sodemed.services.notifications.NotificationTaskService;
@Component
public class NotificationsTask {

    @Autowired
    private NotificationTaskService notificationTaskService;

    @Scheduled(cron = "0 */1 * * * ?")
    public void sendEmail() {
        notificationTaskService.sedNotificationTask();
    }

}
