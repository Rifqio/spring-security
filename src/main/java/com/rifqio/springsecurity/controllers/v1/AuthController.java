package com.rifqio.springsecurity.controllers.v1;

import com.rifqio.springsecurity.commons.dto.request.auth.RegisterDTO;
import com.rifqio.springsecurity.commons.dto.response.ApiResponse;
import com.rifqio.springsecurity.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterDTO payload) {
        authService.register(payload);
        return ResponseEntity.status(201).body(ApiResponse.success("User registered successfully"));
    }
}
