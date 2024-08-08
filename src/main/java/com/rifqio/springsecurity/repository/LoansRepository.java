package com.rifqio.springsecurity.repository;

import com.rifqio.springsecurity.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {
}
