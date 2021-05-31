package com.thefisola.usermanagement.event;

import com.thefisola.usermanagement.model.User;
import lombok.Getter;

@Getter
public class UserDeactivatedEvent {
    private final User user;

    public UserDeactivatedEvent(User user) {
        this.user = user;
    }
}
