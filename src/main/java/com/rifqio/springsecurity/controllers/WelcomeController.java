package com.rifqio.springsecurity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/welcome")
public class WelcomeController {
    @GetMapping
    public ResponseEntity<String> sayWelcome() {
        return ResponseEntity.ok("Welcome to Super Bank");
    }
}
