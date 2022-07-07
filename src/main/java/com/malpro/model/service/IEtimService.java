package com.malpro.model.service;

import com.malpro.model.model.EtimFeature;
import com.malpro.model.model.EtimGroup;
import com.malpro.model.model.EtimUnit;
import com.malpro.model.model.EtimValue;
import com.malpro.model.model.EtimClass;

import java.util.Optional;

/**
 * Created by martin.fahian on 07.02.21.
 */
public interface IEtimService {

    Optional<EtimGroup> findEtimGroupByCode(String code);

//    Optional<EtimUnit> findEtimUnitByCode(String code);
//
//    Optional<EtimUnit> findEtimUnitByAbbreviation(String abbreviation);
//
//    Optional<EtimValue> findEtimValueByCode(String code);
//
//    Optional<EtimFeature> findEtimFeatureByCode(String code);

    Optional<EtimClass> findEtimClassByCode(String code);

//    Optional<EtimValue> findEtimValueByDecsription(String description);
}
