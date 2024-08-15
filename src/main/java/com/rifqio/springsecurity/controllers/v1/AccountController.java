package com.rifqio.springsecurity.controllers.v1;

import com.rifqio.springsecurity.commons.dto.response.ApiResponse;
import com.rifqio.springsecurity.commons.dto.response.ErrorResponse;
import com.rifqio.springsecurity.commons.dto.response.SuccessResponse;
import com.rifqio.springsecurity.model.Accounts;
import com.rifqio.springsecurity.model.Customers;
import com.rifqio.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<SuccessResponse<Customers>> getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();

        Customers accountDetails = accountService.getCurrentAccount(currentEmail).orElse(null);
        return ResponseEntity.ok(SuccessResponse.success("Account retrieved successfully", accountDetails));
    }

    @GetMapping("{email}")
    public ResponseEntity<ApiResponse<?>> getAccountByEmail(@PathVariable String email) {
        Optional<Customers> customer = accountService.getCurrentAccount(email);
        if (customer.isEmpty()) return ResponseEntity.badRequest().body(ErrorResponse.notFound("Account not found"));

        Accounts accounts = accountService.getAccountByCustomerId(customer.get().getId());
        return ResponseEntity.ok(SuccessResponse.success("Account retrieved successfully", accounts));
    }
}
