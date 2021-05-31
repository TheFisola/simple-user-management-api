package com.thefisola.usermanagement.event.listener;

import com.thefisola.usermanagement.UserManagementApplication;
import com.thefisola.usermanagement.event.UserDeactivatedEvent;
import com.thefisola.usermanagement.mock.UserMock;
import com.thefisola.usermanagement.service.email.EmailService;
import com.thefisola.usermanagement.service.email.UserEmailService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = UserManagementApplication.class)
class UserDeactivatedEventListenerTest {

    @MockBean
    private EmailService emailService;
    @MockBean
    private UserEmailService userEmailService;
    @Autowired
    private UserDeactivatedEventListener userDeactivatedEventListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.doNothing().when(emailService).sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handle() {
        UserDeactivatedEvent userDeactivatedEvent = new UserDeactivatedEvent(UserMock.getUserMock());
        userDeactivatedEventListener.handle(userDeactivatedEvent);
        Mockito.verify(userEmailService, Mockito.times(1)).sendUserDeactivationMail(Mockito.any());
    }
}