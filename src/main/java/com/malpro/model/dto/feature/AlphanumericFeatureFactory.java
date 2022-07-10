package com.malpro.model.dto.feature;

import com.malpro.model.dto.KeyValue;
import com.malpro.model.dto.ProductFeatureAlphanumericDto;
import com.malpro.model.dto.ProductFeatureDto;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.model.EtimClassFeatureValue;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

/**
 * Created by martin.fahian on 22.02.21.
 */
@AllArgsConstructor
@Log4j2
public class AlphanumericFeatureFactory implements IFeatureFactory {

    @Override
    public ProductFeatureAlphanumericDto createFeatureCode(EtimClassFeature etimClassFeature, String inputValue) {

        final var productFeatureListDto = new ProductFeatureAlphanumericDto();

        // setting feature
        productFeatureListDto.setEtimFeatureCode(etimClassFeature.getFeature().getCode());

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
    public KeyValue createFeatureText(EtimClassFeature etimClassFeature, ProductFeatureDto productFeatureDto) {
        ProductFeatureAlphanumericDto productFeatureAlphanumericDto = (ProductFeatureAlphanumericDto) productFeatureDto;
        final Optional<EtimClassFeatureValue> etimValue = etimClassFeature.getValues().stream()
                .filter(ev -> ev.getValue().getCode().equals(productFeatureAlphanumericDto.getEtimValueCode()))
                .findFirst();
        String value = "";
        if (etimValue.isPresent()) {
            value = etimValue.get().getValue().getDescription();
        }

        return new KeyValue(etimClassFeature.getFeature().getDescription(), value);
    }
}
