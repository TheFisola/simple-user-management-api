package com.thefisola.usermanagement.service.email;

public interface EmailService {
    void sendEmail(String subject, String from, String to, String body);
}
