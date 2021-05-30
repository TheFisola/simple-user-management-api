package com.thefisola.usermanagement.dto;

import com.thefisola.usermanagement.constant.CommonConstant;
import com.thefisola.usermanagement.constant.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
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
    @Pattern(regexp = CommonConstant.EMAIL_REGEX_PATTERN, message = "Email should be valid")
    private String email;

    @Size(max = 20)
    @NotBlank
    private String mobile;

    // password is expected to come encrypted
    @NotBlank
    private String password;

    @NotBlank
    private UserRole role;
}
