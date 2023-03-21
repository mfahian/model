package com.malpro.model.dto.feature;


import com.malpro.model.dto.KeyValueDto;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.model.EtimClassFeature;

/**
 * Created by martin.fahian on 22.02.21.
 */
public interface IFeatureFactory {

    ProductFeatureDto createFeatureCode(EtimClassFeature etimClassFeature, String inputValue);

    KeyValueDto createFeatureText(EtimClassFeature etimClassFeature, ProductFeatureDto productFeatureDto);
}