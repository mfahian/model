package com.malpro.model.service;

import com.malpro.model.model.EtimClass;
import com.malpro.model.model.EtimGroup;

import java.util.Optional;

/**
 * Created by martin.fahian on 07.02.21.
 */
public interface IEtimService {
    Optional<EtimGroup> findEtimGroupByCode(String code);
    Optional<EtimClass> findEtimClassByCode(String code);
}
