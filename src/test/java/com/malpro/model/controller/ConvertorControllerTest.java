package com.malpro.model.controller;

import com.malpro.model.dto.ProductFeaturesTextDto;
import com.malpro.model.dto.ProductFeaturesCodeDto;
import com.malpro.model.dto.ReferenceFeatureSystem;
import com.malpro.model.model.EtimClass;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.service.IConvertorService;
import com.malpro.model.service.IEtimService;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fahian on 03.07.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class ConvertorControllerTest {

    @Mock
    private IEtimService iEtimService;
    @Mock
    private IConvertorService iConvertorService;
    @InjectMocks
    private ConvertorController convertorController;

    @Test
    @DisplayName("Convert feature data to code - etim class not found test")
    void convertFeatureDataToCodeEtimClassNotFoundTest(@Random ProductFeaturesTextDto productFeaturesTextDto) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(iEtimService.findEtimClassByCode(productFeaturesTextDto.getEtimClass())).thenReturn(Optional.empty());

        ResponseEntity<ProductFeaturesCodeDto> responseEntity = convertorController.toCode(productFeaturesTextDto);

        verify(iEtimService).findEtimClassByCode(productFeaturesTextDto.getEtimClass());
        verify(iConvertorService, never()).convertFeaturesFromText(any(), any(), any(), any());

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.NOT_FOUND));
    }

    @Test
    @DisplayName("Convert feature data to code test")
    @SuppressWarnings("unchecked")
    void convertFeatureDataToCodeTest(@Random ProductFeaturesTextDto productFeaturesTextDto) {
        final var etimClass = mock(EtimClass.class);
        final List<EtimClassFeature> features = mock(List.class);
        final var productFeaturesDto = mock(ProductFeaturesCodeDto.class);
        productFeaturesTextDto.setReferenceFeatureSystem("ETIM-8.0");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(iEtimService.findEtimClassByCode(productFeaturesTextDto.getEtimClass())).thenReturn(Optional.of(etimClass));
        when(etimClass.getCode()).thenReturn(productFeaturesTextDto.getEtimClass());
        when(etimClass.getFeatures()).thenReturn(features);
        when(iConvertorService.convertFeaturesFromText(productFeaturesTextDto.getEtimClass(),
                ReferenceFeatureSystem.ETIM_8_0,
                etimClass.getFeatures(),
                productFeaturesTextDto.getFeaturesMap())).thenReturn(productFeaturesDto);

        ResponseEntity<ProductFeaturesCodeDto> responseEntity = convertorController.toCode(productFeaturesTextDto);

        verify(iEtimService).findEtimClassByCode(productFeaturesTextDto.getEtimClass());
        verify(iConvertorService).convertFeaturesFromText(productFeaturesTextDto.getEtimClass(),
                ReferenceFeatureSystem.ETIM_8_0,
                etimClass.getFeatures(),
                productFeaturesTextDto.getFeaturesMap());

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.OK));
    }

    @Test
    @DisplayName("Convert feature data to code - no input features test")
    @SuppressWarnings("unchecked")
    void convertFeatureDataToCodeNoInputFeaturesTest(@Random ProductFeaturesTextDto productFeaturesTextDto) {
        productFeaturesTextDto.setFeaturesMap(null);
        productFeaturesTextDto.setReferenceFeatureSystem("ETIM-8.0");

        final var etimClass = mock(EtimClass.class);
        final List<EtimClassFeature> features = mock(List.class);
        final var productFeaturesDto = mock(ProductFeaturesCodeDto.class);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(iEtimService.findEtimClassByCode(productFeaturesTextDto.getEtimClass())).thenReturn(Optional.of(etimClass));
        when(etimClass.getCode()).thenReturn(productFeaturesTextDto.getEtimClass());
        when(etimClass.getFeatures()).thenReturn(features);
        when(iConvertorService.convertFeaturesFromText(eq(productFeaturesTextDto.getEtimClass()),
                eq(ReferenceFeatureSystem.ETIM_8_0),
                eq(features),
                anyMap())).thenReturn(productFeaturesDto);

        ResponseEntity<ProductFeaturesCodeDto> responseEntity = convertorController.toCode(productFeaturesTextDto);

        verify(iEtimService).findEtimClassByCode(productFeaturesTextDto.getEtimClass());
        verify(iConvertorService).convertFeaturesFromText(eq(productFeaturesTextDto.getEtimClass()),
                eq(ReferenceFeatureSystem.ETIM_8_0),
                eq(features),
                anyMap());

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.OK));
    }

    @Test
    @DisplayName("Convert feature data to text - etim class not found test")
    void convertFeatureDataToTextEtimClassNotFoundTest(@Random ProductFeaturesCodeDto productFeaturesCodeDto) {

        when(iEtimService.findEtimClassByCode(anyString())).thenReturn(Optional.empty());

        final ResponseEntity<ProductFeaturesTextDto> responseEntity = convertorController.toText(productFeaturesCodeDto);

        verify(iEtimService).findEtimClassByCode(anyString());
        verify(iConvertorService, never()).convertFeaturesFromCode(
                anyString(),
                any(ReferenceFeatureSystem.class),
                anyList(),
                anySet());

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.NOT_FOUND));
    }

    @Test
    @DisplayName("Convert feature data to text test")
    void convertFeatureDataToTextTest(@Random ProductFeaturesCodeDto productFeaturesCodeDto) {
        final var etimClass = new EtimClass();
        final String classCode = "EC00001";
        etimClass.setCode(classCode);
        etimClass.setFeatures(Collections.emptyList());

        when(iEtimService.findEtimClassByCode(anyString())).thenReturn(Optional.of(etimClass));

        final ResponseEntity<ProductFeaturesTextDto> responseEntity = convertorController.toText(productFeaturesCodeDto);

        verify(iEtimService).findEtimClassByCode(anyString());
        verify(iConvertorService).convertFeaturesFromCode(
                eq(classCode),
                any(ReferenceFeatureSystem.class),
                anyList(),
                anySet());

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.OK));
    }

    @Test
    @DisplayName("Convert feature data to text - no features test")
    void convertFeatureDataToTextNoFeaturesTest(@Random ProductFeaturesCodeDto productFeaturesCodeDto) {
        productFeaturesCodeDto.setProductFeature(null);

        final var etimClass = new EtimClass();
        final String classCode = "EC00001";
        etimClass.setCode(classCode);
        etimClass.setFeatures(Collections.emptyList());

        when(iEtimService.findEtimClassByCode(anyString())).thenReturn(Optional.of(etimClass));

        final ResponseEntity<ProductFeaturesTextDto> responseEntity = convertorController.toText(productFeaturesCodeDto);

        verify(iEtimService).findEtimClassByCode(anyString());
        verify(iConvertorService).convertFeaturesFromCode(
                eq(classCode),
                any(ReferenceFeatureSystem.class),
                anyList(),
                anySet());

        assertThat(responseEntity.getStatusCode(), Matchers.is(HttpStatus.OK));
    }
}