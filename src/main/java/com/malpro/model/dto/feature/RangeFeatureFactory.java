package com.malpro.model.dto.feature;

import com.malpro.model.dto.KeyValue;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.dto.ProductFeatureRangeDto;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.util.FeatureHelper;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Optional;

/**
 * Created by martin.fahian on 22.02.21.
 */

@AllArgsConstructor
public class RangeFeatureFactory implements IFeatureFactory {

    @Override
    public ProductFeatureRangeDto createFeatureCode(EtimClassFeature etimClassFeature, String inputValue) {
        Map<String,String> splitValues = FeatureHelper.splitRangeValues(inputValue);

        final var productFeatureRangeDto = new ProductFeatureRangeDto();

        // setting feature
        productFeatureRangeDto.setEtimFeatureCode(etimClassFeature.getFeature().getCode());

        // setting lower and upper bound
        Optional.ofNullable(splitValues.get(FeatureHelper.LOWER_BOUND)).ifPresent(productFeatureRangeDto::setLowerBound);
        Optional.ofNullable(splitValues.get(FeatureHelper.UPPER_BOUND)).ifPresent(productFeatureRangeDto::setUpperBound);

        // setting unit
        // todo - what if supplied unit is different from feature unit
        productFeatureRangeDto.setEtimUnitCode(etimClassFeature.getUnitOfMeasure().getCode());

        return productFeatureRangeDto;
    }

    @Override
    public KeyValue createFeatureText(EtimClassFeature etimClassFeature, ProductFeatureDto productFeatureDto) {
        ProductFeatureRangeDto productFeatureRangeDto = (ProductFeatureRangeDto) productFeatureDto;

        return new KeyValue(etimClassFeature.getFeature().getDescription(),
                productFeatureRangeDto.getLowerBound() + " - " + productFeatureRangeDto.getUpperBound() + " " + etimClassFeature.getUnitOfMeasure().getAbbreviation());

    }
}