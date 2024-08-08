package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.model.Accounts;
import com.rifqio.springsecurity.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountsRepository accountsRepository;

    public Accounts getAccountByCustomerId(Long customerId) {
        return accountsRepository.findByCustomerId(customerId);
    }
}
