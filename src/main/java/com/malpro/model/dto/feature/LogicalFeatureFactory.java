package com.malpro.model.dto.feature;

import com.malpro.model.dto.KeyValueDto;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.dto.ProductFeatureLogicalDto;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.util.FeatureHelper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by martin.fahian on 22.02.21.
 */
@AllArgsConstructor
@Slf4j
public class LogicalFeatureFactory implements IFeatureFactory {

    @Override
    public ProductFeatureLogicalDto createFeatureCode(EtimClassFeature etimClassFeature, String inputValue) {
        log.debug("Logical value: {}", inputValue);
        final var productFeatureLogicalDto = new ProductFeatureLogicalDto();

        // setting feature
        productFeatureLogicalDto.setEtimFeatureCode(etimClassFeature.getFeature().getCode());

        // setting value
        productFeatureLogicalDto.setBooleanValue(FeatureHelper.convertToBoolean(inputValue));

        return productFeatureLogicalDto;
    }

    @Override
    public KeyValueDto createFeatureText(EtimClassFeature etimClassFeature, ProductFeatureDto productFeatureDto) {
        ProductFeatureLogicalDto productFeatureLogicalDto = (ProductFeatureLogicalDto) productFeatureDto;

        String value = "";
        if (productFeatureLogicalDto.getBooleanValue() != null) {
            value = productFeatureLogicalDto.getBooleanValue().toString();
        }

        return new KeyValueDto(etimClassFeature.getFeature().getDescription(), value);
    }
}
