package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.commons.dto.request.auth.AuthRole;
import com.rifqio.springsecurity.commons.dto.request.auth.RegisterDTO;
import com.rifqio.springsecurity.commons.exception.UserAlreadyRegisteredException;
import com.rifqio.springsecurity.model.Customers;
import com.rifqio.springsecurity.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomersRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterDTO payload) throws UserAlreadyRegisteredException {
        if (checkUserExist(payload.getEmail())) {
            throw new UserAlreadyRegisteredException(payload.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(payload.getPassword());
        Customers customers = new Customers();

        customers.setEmail(payload.getEmail());
        customers.setName(payload.getName());
        customers.setPassword(hashedPassword);
        customers.setPhoneNumber(payload.getPhoneNumber());
        customers.setRole(AuthRole.USER.toString());
        customers.setCreatedAt(new Date());

        customerRepository.save(customers);
    }

    private boolean checkUserExist(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }
}
