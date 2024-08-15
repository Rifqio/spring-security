package com.rifqio.springsecurity.controllers.v1;

import com.rifqio.springsecurity.commons.dto.request.auth.LoginRequestDTO;
import com.rifqio.springsecurity.commons.dto.response.SuccessResponse;
import com.rifqio.springsecurity.commons.dto.response.auth.LoginResponseDTO;
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


    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO payload) {
        LoginResponseDTO response = authService.authenticate(payload);
        return ResponseEntity.ok(SuccessResponse.success("Login successful", response));
    }
}
