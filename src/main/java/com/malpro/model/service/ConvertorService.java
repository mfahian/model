package com.malpro.model.service;

import com.malpro.model.dto.KeyValue;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.dto.ProductFeaturesCodeDto;
import com.malpro.model.dto.ProductFeaturesTextDto;
import com.malpro.model.dto.ReferenceFeatureSystem;
import com.malpro.model.dto.feature.ProductFeatureFactory;
import com.malpro.model.model.EtimClassFeature;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by fahian on 07.07.22.
 */
@Service
public class ConvertorService implements IConvertorService {
    @Override
    public ProductFeaturesCodeDto convertFeaturesFromText(String etimClassCode,
                                                          ReferenceFeatureSystem etimVersion,
                                                          List<EtimClassFeature> classFeatures,
                                                          Map<String, String> inputFeatures) {

        Set<ProductFeatureDto> productFeatureDtoSet;

        if (inputFeatures.isEmpty()) {
            productFeatureDtoSet = new HashSet<>();
        } else {
            //        //todo procházet feature nebo mapu?
            productFeatureDtoSet = classFeatures.stream()
                    .filter(ecf -> inputFeatures.containsKey(ecf.getFeature().getDescription()))
                    .map(ecf -> prepareFeatureCode(ecf, inputFeatures.get(ecf.getFeature().getDescription())))
                    .collect(Collectors.toSet());
        }

        return ProductFeaturesCodeDto.builder()
                .etimClass(etimClassCode)
                .productFeature(productFeatureDtoSet)
                .referenceFeatureSystem(etimVersion)
                .build();
    }

    @Override
    //TODO napsat test
    public ProductFeaturesTextDto convertFeaturesFromCode(String etimClassCode,
                                                          ReferenceFeatureSystem etimVersion,
                                                          List<EtimClassFeature> classFeatures,
                                                          Set<ProductFeatureDto> inputFeatures) {
        final var productFeaturesTextDto = new ProductFeaturesTextDto();
        productFeaturesTextDto.setEtimClass(etimClassCode);
        productFeaturesTextDto.setReferenceFeatureSystem(etimVersion.toString());

        Map<String, EtimClassFeature> classFeaturesMap = new HashMap<>();
        classFeatures.forEach(cf -> classFeaturesMap.put(cf.getFeature().getCode(), cf));

        Map<String, String> productFeatureMap = inputFeatures.stream()
                .filter(icf -> classFeaturesMap.containsKey(icf.getEtimFeatureCode()))
                .map(icf -> prepareFeatureText(classFeaturesMap.get(icf.getEtimFeatureCode()),icf))
                .collect(Collectors.toMap(KeyValue::key,KeyValue::value));

        productFeaturesTextDto.setFeaturesMap(productFeatureMap);

        return productFeaturesTextDto;
    }

    private KeyValue prepareFeatureText(EtimClassFeature etimClassFeature, ProductFeatureDto productFeatureDto) {
        return ProductFeatureFactory.getFactory(etimClassFeature.getFeature().getType())
                .createFeatureText(etimClassFeature,productFeatureDto);
    }

    private ProductFeatureDto prepareFeatureCode(EtimClassFeature etimClassFeature, String inputValue) {
        return ProductFeatureFactory.getFactory(etimClassFeature.getFeature().getType())
                .createFeatureCode(etimClassFeature, inputValue);
    }
}
