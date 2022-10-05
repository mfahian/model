package com.malpro.model.controller;

import com.malpro.model.configuration.ApiConfiguration;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.dto.ProductFeaturesTextDto;
import com.malpro.model.dto.ProductFeaturesCodeDto;
import com.malpro.model.model.EtimClass;
import com.malpro.model.service.IConvertorService;
import com.malpro.model.service.IEtimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by fahian on 20.05.22.
 */
@RestController
@RequestMapping(ApiConfiguration.API_URI_V1 + ConvertorController.URL)
@RequiredArgsConstructor
public class ConvertorController {
    public static final String URL = "/convert";

    private final IConvertorService iConvertorService;
    private final IEtimService iEtimService;

    @PostMapping(path = "/to-code", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductFeaturesCodeDto> toCode(@RequestBody ProductFeaturesTextDto productFeaturesTextDto) {

        final Optional<EtimClass> etimClass = iEtimService.findEtimClassByCode(productFeaturesTextDto.getEtimClass());

        //todo obecnou responseTO
        if (etimClass.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Map<String, String> features = productFeaturesTextDto.getFeaturesMap() == null ? new HashMap<>() : productFeaturesTextDto.getFeaturesMap();

        final ProductFeaturesCodeDto productFeaturesCodeDto = iConvertorService.convertFeaturesFromText(etimClass.get().getCode(),
                productFeaturesTextDto.getReferenceFeatureSystem(),
                etimClass.get().getFeatures(),
                features);

        return new ResponseEntity<>(productFeaturesCodeDto, HttpStatus.OK);
    }

    @PostMapping(path = "/to-text", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductFeaturesTextDto> toText(@RequestBody ProductFeaturesCodeDto productFeaturesCodeDto) {

        final Optional<EtimClass> etimClass = iEtimService.findEtimClassByCode(productFeaturesCodeDto.getEtimClass());

        //todo obecnou responseTO
        if (etimClass.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        final Set<ProductFeatureDto> features = productFeaturesCodeDto.getProductFeature() == null ? new HashSet<>() : productFeaturesCodeDto.getProductFeature();

        final var featureDataTextDto = iConvertorService.convertFeaturesFromCode(etimClass.get().getCode(),
                productFeaturesCodeDto.getReferenceFeatureSystem(),
                etimClass.get().getFeatures(),
                features);

        return new ResponseEntity<>(featureDataTextDto, HttpStatus.OK);
    }
}
