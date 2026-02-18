package com.sodemed.services.email.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sodemed.exceptions.NotFoundException;
import com.sodemed.models.email.EmailConfiguration;
import com.sodemed.repositories.email.EmailConfigurationRepository;
import com.sodemed.services.email.EmailService;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    private EmailConfigurationRepository emailConfigurationRepository;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            System.out.println("enviando correo a: "+ to +" "+subject);
            return true;
        } catch (Exception e) {
            return false; 
        }
       
    }

    @Override
    public boolean sendEmailV2(String  subJect, String body,String email) {
        EmailConfiguration emailConfiguration = getEmailConfigProps();
        Properties props = new Properties();
        props.put("mail.smtp.host", emailConfiguration.getHost());
        props.put("mail.smtp.port", emailConfiguration.getPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // Autenticación
        final String username = emailConfiguration.getUsername();
        final String password = emailConfiguration.getPassword();
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subJect);
            message.setContent(body, "text/html; charset=utf-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private EmailConfiguration getEmailConfigProps() {
      return  this.emailConfigurationRepository.findById(1L).orElseThrow(
                        () -> new NotFoundException("No se encontró configuracion para el envio de correos"));
    }

}
