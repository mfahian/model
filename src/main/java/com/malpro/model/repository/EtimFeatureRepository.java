package com.malpro.model.repository;

import com.malpro.model.model.EtimFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtimFeatureRepository extends JpaRepository<EtimFeature, String> {

    Optional<EtimFeature> findByCode(String code);

}
