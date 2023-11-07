package com.goldenboy.server.repository;

import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotorbikeRepository extends BaseRepository<Motorbike, Long> {
    boolean existsByLicensePlates(String licensePlates);

    Optional<Motorbike> findByLicensePlates(String licensePlates);

    List<Motorbike> findMotorbikesByCustomersId(Long customerId);
}
