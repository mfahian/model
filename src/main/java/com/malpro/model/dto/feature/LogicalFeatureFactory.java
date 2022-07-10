package com.malpro.model.dto.feature;

import com.malpro.model.dto.KeyValue;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.dto.ProductFeatureLogicalDto;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.util.FeatureHelper;
import lombok.AllArgsConstructor;

/**
 * Created by martin.fahian on 22.02.21.
 */
@AllArgsConstructor
public class LogicalFeatureFactory implements IFeatureFactory {

    @Override
    public ProductFeatureLogicalDto createFeatureCode(EtimClassFeature etimClassFeature, String inputValue) {

        final var productFeatureLogicalDto = new ProductFeatureLogicalDto();

        // setting feature
        productFeatureLogicalDto.setEtimFeatureCode(etimClassFeature.getFeature().getCode());

        // setting value
        productFeatureLogicalDto.setBooleanValue(FeatureHelper.convertToBoolean(inputValue));

        return productFeatureLogicalDto;
    }

    @Override
    public KeyValue createFeatureText(EtimClassFeature etimClassFeature, ProductFeatureDto productFeatureDto) {
        ProductFeatureLogicalDto productFeatureLogicalDto = (ProductFeatureLogicalDto) productFeatureDto;

        String value = "";
        if (productFeatureLogicalDto.getBooleanValue() != null) {
            value = productFeatureLogicalDto.getBooleanValue().toString();
        }

        return new KeyValue(etimClassFeature.getFeature().getDescription(), value);
    }
}
