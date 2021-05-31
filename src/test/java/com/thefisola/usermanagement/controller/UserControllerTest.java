package com.thefisola.usermanagement.controller;

import com.thefisola.usermanagement.UserManagementApplication;
import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.dto.UserQuery;
import com.thefisola.usermanagement.mock.UserMock;
import com.thefisola.usermanagement.model.User;
import com.thefisola.usermanagement.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = UserManagementApplication.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(userService.getUsers(Mockito.any())).thenReturn(UserMock.getUsersPageMock());
        Mockito.when(userService.registerUser(Mockito.any())).thenReturn(UserMock.getUserMock());
        Mockito.when(userService.updateUser(Mockito.anyString(), Mockito.any(UserDto.class))).thenReturn(UserMock.getUserMock());
        Mockito.when(userService.verifyUser(Mockito.anyString())).thenReturn(UserMock.getUserMock());
        Mockito.when(userService.deactivateUser(Mockito.anyString())).thenReturn(UserMock.getUserMock());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsers() {
        ResponseEntity<Page<User>> users = userController.getUsers(Mockito.any(UserQuery.class));
        Assertions.assertEquals(HttpStatus.OK, users.getStatusCode());
        Assertions.assertNotNull(users.getBody());
        Assertions.assertEquals(1, users.getBody().getTotalPages());
    }

    @Test
    void registerUser() {
        ResponseEntity<User> user = userController.registerUser(Mockito.any());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(HttpStatus.CREATED, user.getStatusCode());
    }

    @Test
    void updateUser() {
        ResponseEntity<User> user = userController.updateUser(Mockito.anyString(), Mockito.any());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(HttpStatus.OK, user.getStatusCode());
    }

    @Test
    void verifyUser() {
        ResponseEntity<User> user = userController.verifyUser(Mockito.any());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(HttpStatus.OK, user.getStatusCode());
    }

    @Test
    void deactivateUser() {
        ResponseEntity<User> user = userController.deactivateUser(Mockito.any());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(HttpStatus.OK, user.getStatusCode());
    }
}