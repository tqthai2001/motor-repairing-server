package com.goldenboy.server.repository;

import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByCode(String code);

    boolean existsByPhone(String phone);

    Optional<Employee> findByPhone(String phone);

    Page<Employee> findByActive(boolean active, Pageable pageable);
}
