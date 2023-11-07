package com.goldenboy.server.repository;

import com.goldenboy.server.entity.Category;
import com.goldenboy.server.repository.base.BaseRepository;

public interface CategoryRepository extends BaseRepository<Category, Long> {
    boolean existsByCode(String code);
}
