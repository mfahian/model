package com.malpro.model.repository;

import com.malpro.model.model.EtimGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtimGroupRepository extends JpaRepository<EtimGroup, Long> {

    Optional<EtimGroup> findByCode(String code);

}
