package com.thefisola.usermanagement.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public void getUsers(){
    }

    @PostMapping("/user")
    public void registerUser(){
    }

    @PutMapping("/user/{id}")
    public void updateUser(){
    }

    @DeleteMapping("/user/{id}")
    public void deactivateUser(){
    }
}
