package com.malpro.model.repository;

import com.malpro.model.model.EtimClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtimClassRepository extends JpaRepository<EtimClass, String> {

    Optional<EtimClass> findByCode(String code);

}
