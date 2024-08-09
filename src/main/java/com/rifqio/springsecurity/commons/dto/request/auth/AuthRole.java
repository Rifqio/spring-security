package com.rifqio.springsecurity.commons.dto.request.auth;

public enum AuthRole {
    USER,
    ADMIN;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
