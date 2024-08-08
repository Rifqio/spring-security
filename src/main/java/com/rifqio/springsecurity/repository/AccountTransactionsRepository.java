package com.rifqio.springsecurity.repository;

import com.rifqio.springsecurity.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;

public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Long> {
}
