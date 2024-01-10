package com.malpro.model.dto.feature;

import com.malpro.model.dto.KeyValueDto;
import com.malpro.model.dto.ProductFeatureAlphanumericDto;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.model.EtimClassFeatureValue;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Created by martin.fahian on 22.02.21.
 */
@AllArgsConstructor
@Slf4j
public class AlphanumericFeatureFactory implements IFeatureFactory {

    @Override
    public ProductFeatureAlphanumericDto createFeatureCode(EtimClassFeature etimClassFeature, String inputValue) {
        log.debug("Alphanumeric value: {}", inputValue);
        final var productFeatureListDto = new ProductFeatureAlphanumericDto();

        // setting feature
        productFeatureListDto.setEtimFeatureCode(etimClassFeature.getFeature().getCode());
        log.debug("Alphanumeric feature: {}", productFeatureListDto.getEtimFeatureCode());
        // setting value
        // Todo NPE for VALUE not checked
        etimClassFeature.getValues().stream()
                .map(EtimClassFeatureValue::getValue)
                .filter(etimValue -> etimValue.getDescription().equals(inputValue))
                .findFirst()
                .ifPresent(etimValue -> productFeatureListDto.setEtimValueCode(etimValue.getCode()));

        return productFeatureListDto;
    }

    @Override
    public KeyValueDto createFeatureText(EtimClassFeature etimClassFeature, ProductFeatureDto productFeatureDto) {
        ProductFeatureAlphanumericDto productFeatureAlphanumericDto = (ProductFeatureAlphanumericDto) productFeatureDto;
        final Optional<EtimClassFeatureValue> etimValue = etimClassFeature.getValues().stream()
                .filter(ev -> ev.getValue().getCode().equals(productFeatureAlphanumericDto.getEtimValueCode()))
                .findFirst();
        String value = "";
        if (etimValue.isPresent()) {
            value = etimValue.get().getValue().getDescription();
        }

        return new KeyValueDto(etimClassFeature.getFeature().getDescription(), value);
    }
}
