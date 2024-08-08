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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<?>> getAccountById(@PathVariable Long id) {
        Accounts account = accountService.getAccountByCustomerId(id);
        if (account == null) return ResponseEntity.badRequest().body(ErrorResponse.notFound("Account not found"));
        return ResponseEntity.ok(SuccessResponse.success("Account retrieved successfully"));
    }
}
