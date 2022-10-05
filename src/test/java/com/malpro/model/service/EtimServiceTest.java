package com.malpro.model.service;

import com.malpro.model.model.EtimClass;
import com.malpro.model.model.EtimGroup;
import com.malpro.model.repository.EtimClassRepository;
import com.malpro.model.repository.EtimGroupRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fahian on 07.07.22.
 */
@ExtendWith({MockitoExtension.class})
class EtimServiceTest {

    @Mock
    private EtimClassRepository etimClassRepository;
    @Mock
    private EtimGroupRepository etimGroupRepository;
    @InjectMocks
    private EtimService etimService;

    @Test
    @DisplayName("Find ETIM group by code test")
    void findEtimGroupByCodeTest() {
        var etimGourp = mock(EtimGroup.class);
        var groupCode = RandomStringUtils.randomAlphanumeric(10);

        when(etimGroupRepository.findByCode(groupCode)).thenReturn(Optional.of(etimGourp));

        final Optional<EtimGroup> foundEtimGroup = etimService.findEtimGroupByCode(groupCode);

        assertThat(foundEtimGroup.isPresent(), Matchers.is(true));

        verify(etimGroupRepository).findByCode(groupCode);
    }

    @Test
    @DisplayName("Find ETIM group by code - not found test")
    void findEtimGroupByCodeNotFoundTest() {
        var groupCode = RandomStringUtils.randomAlphanumeric(10);

        when(etimGroupRepository.findByCode(groupCode)).thenReturn(Optional.empty());

        final Optional<EtimGroup> foundEtimGroup = etimService.findEtimGroupByCode(groupCode);

        assertThat(foundEtimGroup.isPresent(), Matchers.is(false));

        verify(etimGroupRepository).findByCode(groupCode);
    }

    @Test
    @DisplayName("Find ETIM class by code test")
    void findEtimClassByCodeTest() {
        var etimClass = mock(EtimClass.class);
        var classCode = RandomStringUtils.randomAlphanumeric(10);

        when(etimClassRepository.findByCode(classCode)).thenReturn(Optional.of(etimClass));

        final Optional<EtimClass> foundEtimGroup = etimService.findEtimClassByCode(classCode);

        assertThat(foundEtimGroup.isPresent(), Matchers.is(true));

        verify(etimClassRepository).findByCode(classCode);
    }

    @Test
    @DisplayName("Find ETIM class by code - not found test")
    void findEtimClassByCodeNotFoundTest() {
        var classCode = RandomStringUtils.randomAlphanumeric(10);

        when(etimClassRepository.findByCode(classCode)).thenReturn(Optional.empty());

        final Optional<EtimClass> foundEtimGroup = etimService.findEtimClassByCode(classCode);

        assertThat(foundEtimGroup.isPresent(), Matchers.is(false));

        verify(etimClassRepository).findByCode(classCode);
    }
}