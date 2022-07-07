package com.malpro.model.repository;

import com.malpro.model.model.EtimUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtimUnitRepository extends JpaRepository<EtimUnit, Long> {

    Optional<EtimUnit> findByCode(String code);

    Optional<EtimUnit> findByAbbreviation(String abbreviation);

}
