package com.sodemed.services.notifications.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodemed.exceptions.InvalidSendEmailException;
import com.sodemed.services.email.EmailService;
import com.sodemed.services.notifications.NotificationEmailService;
import com.sodemed.utils.email.EmailPasswordChangeTemplate;

@Service
public class NotificationEmailServiceImpl implements NotificationEmailService {

    @Autowired
    private EmailService emailService;

    @Override
    public void sendForgotPassword(String userName, String newPassword, String email) {
        String body = EmailPasswordChangeTemplate.getTemplate(userName, newPassword);
        boolean emailSent = emailService.sendEmailV2("Cambio de contraseña", body, email);
        if (!emailSent) {
            throw new InvalidSendEmailException(
                    "Error al enviar el correo para el cambio de correo" + email);
        }
    }

}
