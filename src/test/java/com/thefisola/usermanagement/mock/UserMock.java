package com.thefisola.usermanagement.mock;

import com.github.javafaker.Faker;
import com.thefisola.usermanagement.constant.UserRole;
import com.thefisola.usermanagement.constant.UserStatus;
import com.thefisola.usermanagement.dto.UserDto;
import com.thefisola.usermanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public class UserMock {

    public static UserDto getUserMockUserDto() {
        UserDto userDto = new UserDto();
        Faker faker = new Faker();
        userDto.setTitle(faker.name().title());
        userDto.setFirstName(faker.name().firstName());
        userDto.setLastName(faker.name().lastName());
        userDto.setEmail(faker.internet().emailAddress());
        userDto.setMobile(faker.phoneNumber().phoneNumber());
        userDto.setPassword(faker.random().toString());
        userDto.setRole(UserRole.USER);
        return userDto;
    }

    public static UserDto getAdminMockUserDto() {
        UserDto userDto = getUserMockUserDto();
        userDto.setRole(UserRole.ADMIN);
        return userDto;
    }

    public static User getUserMock() {
        User user = new User();
        Faker faker = new Faker();
        user.setId(faker.random().toString());
        user.setTitle(faker.name().prefix());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setMobile(faker.phoneNumber().phoneNumber());
        user.setPassword(faker.random().toString());
        user.setRole(faker.random().nextBoolean() ? UserRole.USER : UserRole.ADMIN);
        user.setVerified(faker.random().nextBoolean());
        user.setDateDeactivated(new Date());
        user.setDateRegistered(new Date());
        user.setDateVerified((user.isVerified() ? new Date() : null));
        user.setStatus(user.isVerified() ? UserStatus.VERIFIED : UserStatus.REGISTERED);
        return user;
    }

    public static User getUserToBeDeactivatedMock() {
        User userMock = UserMock.getUserMock();
        userMock.setDateDeactivated(null);
        userMock.setVerified(true);
        userMock.setStatus(UserStatus.VERIFIED);
        return userMock;
    }

    public static User getUserToBeVerifiedMock() {
        User userMock = UserMock.getUserMock();
        userMock.setDateVerified(null);
        userMock.setVerified(false);
        userMock.setStatus(UserStatus.REGISTERED);
        return userMock;
    }

    public static Page<User> getUsersPageMock() {
        List<User> users = List.of(getUserMock(), getUserMock(), getUserMock(), getUserMock());
        int startIndex = 0;
        int endIndex = users.size();
        final Page<User> page = new PageImpl<>(users.subList(startIndex, endIndex), PageRequest.of(startIndex, endIndex), users.size());
        return page;

    }
}
