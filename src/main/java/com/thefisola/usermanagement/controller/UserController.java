package com.thefisola.usermanagement.controller;

import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.model.User;
import com.thefisola.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user")
    public User registerUser(@RequestBody @Validated UserDto userDto){
        return userService.registerUser(userDto);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable String id, @RequestBody @Validated UserDto userDto){
        return userService.updateUser(id, userDto);
    }

    @GetMapping("/user/{id}/verify")
    public User verifyUser(@PathVariable String id){
        return userService.verifyUser(id);
    }

    @DeleteMapping("/user/{id}")
    public User deactivateUser(@PathVariable String id){
        return userService.deactivateUser(id);
    }
}
