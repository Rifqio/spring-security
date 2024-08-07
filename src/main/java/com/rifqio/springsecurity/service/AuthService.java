package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.commons.dto.request.auth.RegisterDTO;
import com.rifqio.springsecurity.model.Customer;
import com.rifqio.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterDTO payload) {
        String hashedPassword = passwordEncoder.encode(payload.getPassword());
        payload.setPassword(hashedPassword);
        Customer customer = new Customer(payload.getName(), payload.getEmail(), payload.getPassword(), "read");
        customerRepository.save(customer);
    }
}
