package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.commons.constants.ApplicationConstant;
import com.rifqio.springsecurity.commons.dto.request.auth.AuthRole;
import com.rifqio.springsecurity.commons.dto.request.auth.LoginRequestDTO;
import com.rifqio.springsecurity.commons.dto.request.auth.RegisterRequestDTO;
import com.rifqio.springsecurity.commons.dto.response.auth.LoginResponseDTO;
import com.rifqio.springsecurity.commons.exception.BusinessException;
import com.rifqio.springsecurity.commons.exception.UserAlreadyRegisteredException;
import com.rifqio.springsecurity.model.Customers;
import com.rifqio.springsecurity.repository.CustomersRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomersRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final Environment env;

    public void register(RegisterRequestDTO payload) throws UserAlreadyRegisteredException {
        if (checkUserExist(payload.getEmail())) {
            throw new UserAlreadyRegisteredException(payload.getEmail());
        }
        Customers customers = createCustomer(payload);
        customerRepository.save(customers);
    }

    public LoginResponseDTO authenticate(LoginRequestDTO payload) {
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(payload.getEmail(), payload.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        String jwtToken = generateToken(authenticationResponse);
        if (!authenticationResponse.isAuthenticated()) throw BusinessException.invalidCredentials();
        return new LoginResponseDTO(jwtToken);
    }

    private Customers createCustomer(RegisterRequestDTO payload) {
        String hashedPassword = passwordEncoder.encode(payload.getPassword());
        Customers customers = new Customers();

        customers.setEmail(payload.getEmail());
        customers.setName(payload.getName());
        customers.setPassword(hashedPassword);
        customers.setPhoneNumber(payload.getPhoneNumber());
        customers.setRole(AuthRole.USER.toString());
        customers.setCreatedAt(new Date());

        return customers;
    }

    private boolean checkUserExist(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

    private String generateToken(Authentication user) {
        String jwtSecret = env.getProperty(ApplicationConstant.JWT_SECRET);
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        String authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        return Jwts.builder()
                .issuer("superbank")
                .subject("Auth Token")
                .claim("email", user.getName())
                .claim("details", user.getDetails())
                .claim("authorities", authorities)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 3000000))
                .signWith(secretKey)
                .compact();
    }
}
