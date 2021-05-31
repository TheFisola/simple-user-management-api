package com.thefisola.usermanagement.event.listener;

import com.thefisola.usermanagement.event.UserDeactivatedEvent;
import com.thefisola.usermanagement.service.email.UserEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserDeactivatedEventListener {

    private final UserEmailService userEmailService;

    @Autowired
    public UserDeactivatedEventListener(UserEmailService userEmailService) {
        this.userEmailService = userEmailService;
    }

    @Async
    @EventListener
    public void handle(UserDeactivatedEvent userDeactivatedEvent) {
        userEmailService.sendUserDeactivationMail(userDeactivatedEvent.getUser());
    }
}
