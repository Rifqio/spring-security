package com.rifqio.springsecurity.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;


@Getter
public class CustomUserDetails extends User {
    private final Customers customers;

    public CustomUserDetails(Customers customers, List<SimpleGrantedAuthority> authorities) {
        super(customers.getEmail(), customers.getPassword(), authorities);
        this.customers = customers;
    }
}
