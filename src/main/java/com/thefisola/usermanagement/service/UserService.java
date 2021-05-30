package com.thefisola.usermanagement.service;

import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.dto.UserQuery;
import com.thefisola.usermanagement.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> getUsers(UserQuery userQuery);

    User registerUser(UserDto userDto);

    User updateUser(String id, UserDto userDto);

    User deactivateUser(String id);

    User verifyUser(String id);
}
