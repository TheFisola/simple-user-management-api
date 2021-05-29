package com.thefisola.usermanagement.service.impl;

import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.exception.UserNotFoundException;
import com.thefisola.usermanagement.model.User;
import com.thefisola.usermanagement.repository.UserRepository;
import com.thefisola.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User().fromUserDto(userDto);
        return userRepository.save(user);
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
        user.deactivate();
        userRepository.save(user);
        return null;
    }

    private User validateUserId(String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("User with Id: '"+id+"' not found");
        return user.get();
    }
}
