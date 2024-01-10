package com.malpro.model.dto.feature;

import java.util.Map;
import java.util.Optional;

import com.malpro.model.dto.KeyValueDto;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.dto.ProductFeatureNumericDto;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.util.FeatureHelper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by martin.fahian on 22.02.21.
 */

@AllArgsConstructor
@Slf4j
public class NumericFeatureFactory implements IFeatureFactory {

    @Override
    public ProductFeatureNumericDto createFeatureCode(EtimClassFeature etimClassFeature, String inputValue) {
        log.debug("Numeric value: {}", inputValue);
        Map<String, String> splitValues = FeatureHelper.splitSimpleValues(inputValue);

        final var productFeatureNumericDto = new ProductFeatureNumericDto();

        // setting feature
        productFeatureNumericDto.setEtimFeatureCode(etimClassFeature.getFeature().getCode());

        // setting value
        Optional.ofNullable(splitValues.get(FeatureHelper.VALUE)).ifPresent(productFeatureNumericDto::setNumericValue);

        // setting unit
        // todo - what if supplied unit is different from feature unit
        Optional.ofNullable(etimClassFeature.getUnitOfMeasure())
                .ifPresent(etimUnit -> productFeatureNumericDto.setEtimUnitCode(etimUnit.getCode()));

        return productFeatureNumericDto;
    }

    @Override
    public KeyValueDto createFeatureText(EtimClassFeature etimClassFeature, ProductFeatureDto productFeatureDto) {
        ProductFeatureNumericDto productFeatureNumericDto = (ProductFeatureNumericDto) productFeatureDto;

        return new KeyValueDto(etimClassFeature.getFeature().getDescription(), productFeatureNumericDto.getNumericValue() + " " + etimClassFeature.getUnitOfMeasure().getAbbreviation());
    }
}
