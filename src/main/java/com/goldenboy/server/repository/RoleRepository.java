package com.goldenboy.server.repository;

import com.goldenboy.server.common.ERole;
import com.goldenboy.server.entity.Role;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
