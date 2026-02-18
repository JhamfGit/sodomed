package com.sodemed.services.notifications;

public interface NotificationEmailService {
    void sendForgotPassword(String userName, String newPassword, String email);
}
