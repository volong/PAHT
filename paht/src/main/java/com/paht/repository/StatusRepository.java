package com.paht.repository;

import com.paht.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {


    @Query(value = "SELECT * FROM statuses", nativeQuery = true)
    List<Status> findAll();
}
