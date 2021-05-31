package com.thefisola.usermanagement.dto;

import com.thefisola.usermanagement.constant.CommonConstants;
import com.thefisola.usermanagement.constant.UserRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @Size(max = 10)
    @NotBlank(message = "Please provide a title")
    private String title;

    @Size(max = 100)
    @NotBlank
    private String firstName;

    @Size(max = 100)
    @NotBlank
    private String lastName;

    @Size(max = 100)
    @NotBlank
    @Pattern(regexp = CommonConstants.EMAIL_REGEX_PATTERN, message = "Email should be valid")
    private String email;

    @Size(max = 20)
    @NotBlank
    private String mobile;

    // password is expected to come encrypted
    @NotBlank
    private String password;

    private UserRole role;
}
