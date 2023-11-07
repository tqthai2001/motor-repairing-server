package com.goldenboy.server.repository.base;

import com.goldenboy.server.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity, Long> extends JpaRepository<T, Long> {}
