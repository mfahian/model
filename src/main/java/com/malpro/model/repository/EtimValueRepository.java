package com.malpro.model.repository;

import com.malpro.model.model.EtimValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtimValueRepository extends JpaRepository<EtimValue, Long> {

    Optional<EtimValue> findByCode(String code);

    Optional<EtimValue> findByDescription(String description);
}
