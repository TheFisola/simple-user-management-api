package com.thefisola.usermanagement.controller;

import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.dto.UserQuery;
import com.thefisola.usermanagement.model.User;
import com.thefisola.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(@Valid UserQuery userQuery){
        return new ResponseEntity<>(userService.getUsers(userQuery), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/verify")
    public ResponseEntity<User> verifyUser(@PathVariable String id){
        return new ResponseEntity<>(userService.verifyUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deactivateUser(@PathVariable String id){
        return new ResponseEntity<>(userService.deactivateUser(id), HttpStatus.OK);
    }
}
