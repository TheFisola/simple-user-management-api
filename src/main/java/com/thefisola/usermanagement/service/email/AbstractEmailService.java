package com.thefisola.usermanagement.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component
public abstract class AbstractEmailService implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String subject, String from, String to, String body) {
        try {
            MimeMessage message = getMailMessage(subject, from, to, body);
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Error occurred while trying to send mail", e);
        }
    }

    private MimeMessage getMailMessage(String subject, String from, String to, String body) {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mailMessage;
    }
}
