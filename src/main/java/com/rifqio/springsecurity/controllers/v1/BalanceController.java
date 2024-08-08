package com.rifqio.springsecurity.controllers.v1;

import com.rifqio.springsecurity.commons.dto.response.SuccessResponse;
import com.rifqio.springsecurity.model.AccountTransactions;
import com.rifqio.springsecurity.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/balance")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<AccountTransactions>>> getBalanceReport(@RequestParam Long accountId) {
        List<AccountTransactions> accountTransactions = balanceService.getBalanceReport(accountId);
        return ResponseEntity.ok(SuccessResponse.success("Balance report retrieved successfully", accountTransactions));
    }
}
