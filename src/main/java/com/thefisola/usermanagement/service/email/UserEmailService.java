package com.thefisola.usermanagement.service.email;

import com.thefisola.usermanagement.model.User;

public interface UserEmailService {
    void sendUserVerificationMail(User user);
    void sendUserDeactivationMail(User user);
}
