package com.thefisola.usermanagement.service;

import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User registerUser(UserDto userDto);

    User updateUser(String id, UserDto userDto);

    User deactivateUser(String id);

    User verifyUser(String id);
}
