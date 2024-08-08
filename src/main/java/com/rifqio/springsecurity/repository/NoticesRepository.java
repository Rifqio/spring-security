package com.rifqio.springsecurity.repository;

import com.rifqio.springsecurity.model.Notices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticesRepository extends CrudRepository<Notices, Long> {
    @Query(value = "SELECT * FROM notices WHERE CURDATE() BETWEEN begin_date AND end_date", nativeQuery = true)
    List<Notices> findAllActiveNotices();
}
