package com.thefisola.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thefisola.usermanagement.constant.CommonConstants;
import com.thefisola.usermanagement.constant.UserRole;
import com.thefisola.usermanagement.constant.UserStatus;
import com.thefisola.usermanagement.dto.UserDto;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String mobile;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean verified = false;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.REGISTERED;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DATETIME_FORMAT, timezone = CommonConstants.TIME_ZONE)
    private Date dateRegistered;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DATETIME_FORMAT, timezone = CommonConstants.TIME_ZONE)
    private Date dateVerified;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DATETIME_FORMAT, timezone = CommonConstants.TIME_ZONE)
    private Date dateDeactivated;

    @JsonIgnore
    public boolean isNotVerified() {
        return !verified;
    }

    @JsonIgnore
    public boolean isDeactivated() {
        return dateDeactivated != null && status.equals(UserStatus.DEACTIVATED);
    }

    public void deactivate() {
        status = UserStatus.DEACTIVATED;
        dateDeactivated = new Date();
    }

    public void verify() {
        status = UserStatus.VERIFIED;
        verified = true;
        dateVerified = new Date();
    }

    public User fromUserDto(UserDto userDto) {
        BeanUtils.copyProperties(userDto, this);
        return this;
    }
}
