package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.model.Accounts;
import com.rifqio.springsecurity.model.Customers;
import com.rifqio.springsecurity.repository.AccountsRepository;
import com.rifqio.springsecurity.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountsRepository accountsRepository;
    private final CustomersRepository customersRepository;

    public Accounts getAccountByCustomerId(Long customerId) {
        return accountsRepository.findByCustomerId(customerId);
    }

    public Optional<Customers> getCurrentAccount(String email) {
        return customersRepository.findByEmail(email);
    }
}
