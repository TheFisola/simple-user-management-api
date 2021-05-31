package com.thefisola.usermanagement.service;

import com.thefisola.usermanagement.UserManagementApplication;
import com.thefisola.usermanagement.constant.UserStatus;
import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.dto.UserQuery;
import com.thefisola.usermanagement.exception.UserNotFoundException;
import com.thefisola.usermanagement.mock.UserMock;
import com.thefisola.usermanagement.model.User;
import com.thefisola.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

@SpringBootTest(classes = UserManagementApplication.class)
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(UserMock.getUsersPageMock());
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(UserMock.getUserMock());
        Mockito.doNothing().when(eventPublisher).publishEvent(Mockito.any());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test get users request")
    void getUsers() {
        UserQuery userQuery = new UserQuery();
        userQuery.setPageNumber(1);
        userQuery.setPageSize(4);
        Page<User> users = userService.getUsers(userQuery);
        Assertions.assertEquals(4, users.getContent().size());
        Assertions.assertTrue(users.isFirst());
    }

    @Test
    @DisplayName("Test that users are users are registered successfully")
    void registerUser() {
        UserDto userDto = UserMock.getUserMockUserDto();
        User user = userService.registerUser(userDto);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getDateRegistered());
        Assertions.assertEquals(user.isVerified(), user.getDateVerified() != null);
    }

    @Test
    @DisplayName("Test that update user throws exception for users not found")
    void testThatUpdateUserThrowsExceptionForNotFound() {
        UserDto userDto = UserMock.getUserMockUserDto();
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser("random_id_that_does_not_exist", userDto);
        });
    }

    @Test
    @DisplayName("Test that user is updated successfully")
    void updateUser() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(UserMock.getUserMock()));
        UserDto userDto = UserMock.getUserMockUserDto();
        User user = userService.updateUser(Mockito.anyString(), userDto);
        Assertions.assertNotNull(user);
    }

    @Test
    @DisplayName("Test that user is deactivated successfully")
    void deactivateUser() {
        User userMock = UserMock.getUserMock();
        userMock.setStatus(UserStatus.DEACTIVATED);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userMock);
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(UserMock.getUserToBeDeactivatedMock()));
        User user = userService.deactivateUser(Mockito.anyString());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(UserStatus.DEACTIVATED, user.getStatus());
        Assertions.assertNotNull(user.getDateDeactivated());
    }

    @Test
    @DisplayName("Test that user is verified successfully")
    void verifyUser() {
        User userMock = UserMock.getUserMock();
        userMock.setStatus(UserStatus.VERIFIED);
        userMock.setVerified(true);
        userMock.setDateVerified(new Date());
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userMock);
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(UserMock.getUserToBeVerifiedMock()));
        User user = userService.verifyUser(Mockito.anyString());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(UserStatus.VERIFIED, user.getStatus());
        Assertions.assertNotNull(user.getDateVerified());
    }
}