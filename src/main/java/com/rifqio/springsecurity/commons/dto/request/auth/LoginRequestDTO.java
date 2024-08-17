package com.rifqio.springsecurity.commons.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDTO {
    @Email(message = "email should be valid")
    @NotBlank(message = "email cannot be null")
    public String email;

    @NotBlank(message = "password cannot be null")
    public String password;
}
