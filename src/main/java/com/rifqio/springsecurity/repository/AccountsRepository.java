package com.rifqio.springsecurity.repository;

import com.rifqio.springsecurity.model.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<Accounts, Long> {
}
