package com.goldenboy.server.repository;

import com.goldenboy.server.entity.Brand;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends BaseRepository<Brand, Long> {
    boolean existsByBrandName(String brandName);

    Optional<Brand> findByBrandName(String brandName);
}
