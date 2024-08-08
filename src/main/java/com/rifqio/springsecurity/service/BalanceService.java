package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.model.AccountTransactions;
import com.rifqio.springsecurity.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final AccountTransactionsRepository accountTransactionsRepository;

    public List<AccountTransactions> getBalanceReport(Long accountId) {
        return accountTransactionsRepository.findByCustomerIdOrderByTransactionDateDesc(accountId);
    }
}
