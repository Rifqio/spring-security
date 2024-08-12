package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.commons.exception.BusinessException;
import com.rifqio.springsecurity.model.Customers;
import com.rifqio.springsecurity.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final CustomersRepository customerRepository;

    @Primary
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customers customer = customerRepository.findByEmail(username).orElseThrow(BusinessException::invalidCredentials);
        List<SimpleGrantedAuthority> authorities = customer
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorities()))
                .toList();
//        return new CustomUserDetails(customer, authorities);
        return new User(customer.getEmail(), customer.getPassword(), authorities);
    }
}
