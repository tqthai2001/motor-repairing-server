package com.goldenboy.server.repository;

import com.goldenboy.server.entity.Service;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends BaseRepository<Service, Long> {
    boolean existsByCode(String code);

    Page<Service> findByActive(boolean active, Pageable pageable);
}
