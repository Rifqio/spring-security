package com.rifqio.springsecurity.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }

    public static BusinessException userAlreadyExist(String email) {
        throw new BusinessException("User with email " + email + " already registered");
    }

    public static BusinessException invalidPassword() {
        throw new BusinessException("Invalid password");
    }

    public static BusinessException userNotFound() {
        throw new BusinessException("User not found");
    }

    public static BusinessException invalidCredentials() {
        throw new BusinessException("Invalid credentials");
    }

    public static BusinessException tokenExpired() {
        throw new BusinessException("Token expired");
    }

    public static BusinessException invalidToken() {
        throw new BusinessException("Invalid token");
    }
}
