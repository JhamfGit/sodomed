package com.sodemed.services.email;

public interface EmailService {

    boolean sendEmail(String to, String subject, String body);

    boolean sendEmailV2(String  subJect, String body, String email);

}
