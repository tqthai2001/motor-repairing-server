package com.goldenboy.server.repository;

import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
    boolean existsByPhone(String phone);

    Optional<Customer> findByPhone(String phone);

    Page<Customer> findByActive(boolean active, Pageable pageable);
}
