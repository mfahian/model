package com.malpro.model.service;

import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.dto.ProductFeaturesCodeDto;
import com.malpro.model.dto.ProductFeaturesTextDto;
import com.malpro.model.dto.ReferenceFeatureSystem;
import com.malpro.model.model.EtimClassFeature;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by fahian on 07.07.22.
 */
public interface IConvertorService {
    ProductFeaturesCodeDto convertFeaturesFromText(String etimClassCode,
                                                   ReferenceFeatureSystem etimVersion,
                                                   List<EtimClassFeature> classFeatures,
                                                   Map<String, String> inputFeatures);

    ProductFeaturesTextDto convertFeaturesFromCode(String etimClassCode,
                                                   ReferenceFeatureSystem etimVersion,
                                                   List<EtimClassFeature> classFeatures,
                                                   Set<ProductFeatureDto> inputFeatures);
}
