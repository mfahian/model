package com.malpro.model.controller;

import com.malpro.model.model.EtimClass;
import com.malpro.model.model.EtimGroup;
import com.malpro.model.service.IEtimService;
import com.malpro.model.random.RandomBeansExtension;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fahian on 06.07.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class EtimEntitiesControllerTest {
    @Mock
    private IEtimService iEtimService;
    @InjectMocks
    EtimEntitiesController etimEntitiesController;

    @Test
    @DisplayName("Get group test")
    void getGroupTest() {
        var etimGroup = mock(EtimGroup.class);
        var groupCode = RandomStringUtils.randomAlphanumeric(10);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(iEtimService.findEtimGroupByCode(groupCode)).thenReturn(Optional.of(etimGroup));

        ResponseEntity<EtimGroup> responseEntity = etimEntitiesController.getGroup(groupCode);

        verify(iEtimService).findEtimGroupByCode(groupCode);

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.OK));
    }

    @Test
    @DisplayName("Get group - not found test")
    void getGroupNotFoundTest() {
        var groupCode = RandomStringUtils.randomAlphanumeric(10);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(iEtimService.findEtimGroupByCode(groupCode)).thenReturn(Optional.empty());

        ResponseEntity<EtimGroup> responseEntity = etimEntitiesController.getGroup(groupCode);

        verify(iEtimService).findEtimGroupByCode(groupCode);

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.NOT_FOUND));
    }

    @Test
    @DisplayName("Get class test")
    void getClassTest() {
        var etimClass = mock(EtimClass.class);
        var groupCode = RandomStringUtils.randomAlphanumeric(10);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(iEtimService.findEtimClassByCode(groupCode)).thenReturn(Optional.of(etimClass));

        ResponseEntity<EtimClass> responseEntity = etimEntitiesController.getClassGroup(groupCode);

        verify(iEtimService).findEtimClassByCode(groupCode);

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.OK));
    }

    @Test
    @DisplayName("Get class - not found test")
    void getClassNotFoundTest() {
        var groupCode = RandomStringUtils.randomAlphanumeric(10);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(iEtimService.findEtimClassByCode(groupCode)).thenReturn(Optional.empty());

        ResponseEntity<EtimClass> responseEntity = etimEntitiesController.getClassGroup(groupCode);

        verify(iEtimService).findEtimClassByCode(groupCode);

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.NOT_FOUND));
    }
}