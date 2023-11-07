package com.goldenboy.server.service.base;

import com.goldenboy.server.entity.base.BaseEntity;

import java.util.List;
import java.util.Map;

public interface BaseService<T extends BaseEntity> {
    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    T updateById(Long id, T newEntity);

    void deleteById(Long id);

    Map<String, Object> findAllPaging(int page, int size);
}
