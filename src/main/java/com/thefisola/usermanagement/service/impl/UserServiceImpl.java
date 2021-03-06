package com.thefisola.usermanagement.service.impl;

import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.dto.UserQuery;
import com.thefisola.usermanagement.event.UserDeactivatedEvent;
import com.thefisola.usermanagement.event.UserRegisteredEvent;
import com.thefisola.usermanagement.exception.UnverifiedUserException;
import com.thefisola.usermanagement.exception.UserNotFoundException;
import com.thefisola.usermanagement.model.User;
import com.thefisola.usermanagement.repository.UserRepository;
import com.thefisola.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Page<User> getUsers(UserQuery userQuery) {
        Pageable pageable = PageRequest.of(userQuery.getPageNumber() > 0 ?
                userQuery.getPageNumber() - 1 : userQuery.getPageNumber(), userQuery.getPageSize());
        return userRepository.findAll(pageable);
    }

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User().fromUserDto(userDto);
        user = userRepository.save(user);
        eventPublisher.publishEvent(new UserRegisteredEvent(user));
        return user;
    }

    @Override
    public User updateUser(String id, UserDto userDto) {
        User user = validateUserId(id);
        user.fromUserDto(userDto);
        return userRepository.save(user);
    }

    @Override
    public User deactivateUser(String id) {
        User user = validateUserId(id);
        if (user.isNotVerified()) throw new UnverifiedUserException("You cannot deactivate a user who is not verified");
        user.deactivate();
        user = userRepository.save(user);
        eventPublisher.publishEvent(new UserDeactivatedEvent(user));
        return user;
    }

    @Override
    public User verifyUser(String id) {
        User user = validateUserId(id);
        user.verify();
        return userRepository.save(user);
    }

    private User validateUserId(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || user.get().isDeactivated())
            throw new UserNotFoundException("User with Id: '" + id + "' not found");
        return user.get();
    }
}
