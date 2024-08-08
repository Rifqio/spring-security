package com.rifqio.springsecurity.commons.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String email) {
        super("User with email " + email + " already registered");
    }
}
