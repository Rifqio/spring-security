package com.rifqio.springsecurity.repository;

import com.rifqio.springsecurity.model.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, Long> {
    Optional<Customers> findByEmail(String email);
}
