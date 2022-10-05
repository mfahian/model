package com.malpro.model.service;

import com.malpro.model.model.EtimClass;
import com.malpro.model.model.EtimGroup;
import com.malpro.model.repository.EtimClassRepository;
import com.malpro.model.repository.EtimGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by martin.fahian on 07.02.21.
 */
@Service
public class EtimService implements IEtimService {

    private final EtimClassRepository etimClassRepository;
    private final EtimGroupRepository etimGroupRepository;

    public EtimService(final EtimClassRepository etimClassRepository,
                       final EtimGroupRepository etimGroupRepository) {
        this.etimClassRepository = etimClassRepository;
        this.etimGroupRepository = etimGroupRepository;
    }

    @Override
    public Optional<EtimGroup> findEtimGroupByCode(String code) {
        return etimGroupRepository.findByCode(code);
    }

    @Override
    public Optional<EtimClass> findEtimClassByCode(String code) {
        return etimClassRepository.findByCode(code);
    }

}
