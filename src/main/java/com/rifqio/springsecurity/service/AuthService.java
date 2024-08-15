package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.commons.dto.request.auth.LoginRequestDTO;
import com.rifqio.springsecurity.commons.dto.response.auth.LoginResponseDTO;
import com.rifqio.springsecurity.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomersRepository customerRepository;

    public LoginResponseDTO authenticate(LoginRequestDTO payload) {
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(payload.getEmail(), payload.getPassword());
        return new LoginResponseDTO("jwtToken");
    }

    private boolean checkUserExist(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }
}
