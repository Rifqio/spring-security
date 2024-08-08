package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.model.Loans;
import com.rifqio.springsecurity.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoansRepository loanRepository;

    public List<Loans> getCurrentLoans(Long customerId) {
        return loanRepository.findByCustomerIdOrderByStartDateDesc(customerId);
    }
}
