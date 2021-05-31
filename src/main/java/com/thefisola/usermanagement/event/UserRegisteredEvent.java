package com.thefisola.usermanagement.event;

import com.thefisola.usermanagement.model.User;
import lombok.Getter;

@Getter
public class UserRegisteredEvent {
    private final User user;

    public UserRegisteredEvent(User user) {
        this.user = user;
    }
}
