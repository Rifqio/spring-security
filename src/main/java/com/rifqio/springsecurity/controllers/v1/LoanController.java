package com.rifqio.springsecurity.controllers.v1;

import com.rifqio.springsecurity.commons.dto.response.ApiResponse;
import com.rifqio.springsecurity.commons.dto.response.ErrorResponse;
import com.rifqio.springsecurity.commons.dto.response.SuccessResponse;
import com.rifqio.springsecurity.model.Loans;
import com.rifqio.springsecurity.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<ApiResponse> getCurrentLoans(@RequestParam Long accountId) {
        List<Loans> loans = loanService.getCurrentLoans(accountId);
        if (loans.isEmpty()) return ResponseEntity.badRequest().body(ErrorResponse.notFound("No loans available"));
        return ResponseEntity.ok(SuccessResponse.success("Loans retrieved successfully", loans));
    }
}
