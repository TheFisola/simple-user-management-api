package com.thefisola.usermanagement.service.email.impl;

import com.thefisola.usermanagement.model.User;
import com.thefisola.usermanagement.service.email.AbstractEmailService;
import com.thefisola.usermanagement.service.email.UserEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
public class UserEmailServiceImpl extends AbstractEmailService implements UserEmailService {

    @Value("${user.email.from}")
    private String from;
    @Value("${user.email.verificationSubject}")
    private String verificationSubject;
    @Value("${user.email.deactivationSubject}")
    private String deactivationSubject;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public void sendUserVerificationMail(User user) {
        String body = "Hi <b>" + user.getFirstName() + "</b>, you have successfully registered. <a href='" + getVerificationLink(user) + "' target='_blank'>Click here to verify your account!</a>";
        sendEmail(verificationSubject, from, user.getEmail(), body);
    }

    @Override
    public void sendUserDeactivationMail(User user) {
        String body = "Hi <b>" + user.getFirstName() + "</b>, your account has been deactivated!";
        sendEmail(deactivationSubject, from, user.getEmail(), body);
    }

    private String getVerificationLink(User user) {
        return baseUrl + "/api/user/" + user.getId() + "/verify";
    }
}
