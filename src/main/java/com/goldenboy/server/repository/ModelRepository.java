package com.goldenboy.server.repository;

import com.goldenboy.server.entity.Model;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends BaseRepository<Model, Long> {
    boolean existsByModelNameAndBrandId(String modelName, Long brandId);

    Optional<Model> findByModelNameAndBrandId(String modelName, Long brandId);
}
