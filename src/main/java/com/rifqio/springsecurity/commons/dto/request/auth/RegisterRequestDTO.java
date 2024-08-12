package com.rifqio.springsecurity.commons.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequestDTO {
    @Email(message = "email should be valid")
    @NotBlank(message = "email cannot be null")
    private String email;

    @NotBlank(message = "name cannot be null")
    private String name;

    @Size(min = 6, message = "password should have at least 6 characters")
    @NotBlank(message = "password cannot be null")
    private String password;

    @NotBlank(message = "phone number cannot be null")
    @Size(min = 10, message = "phone number should have at least 10 characters")
    private String phoneNumber;
}
