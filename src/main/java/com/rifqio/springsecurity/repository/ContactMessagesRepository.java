package com.rifqio.springsecurity.repository;

import com.rifqio.springsecurity.model.ContactMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessagesRepository extends CrudRepository<ContactMessages, Long> {
}
