package com.malpro.model.dto.feature;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malpro.model.dto.KeyValueDto;
import com.malpro.model.dto.ProductFeatureAlphanumericDto;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.model.EtimClassFeatureValue;
import com.malpro.model.model.EtimValue;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;

/**
 * Created by fahian on 26.06.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class AlphanumericFeatureFactoryTest {

    @InjectMocks
    private AlphanumericFeatureFactory alphanumericFeatureFactory;

    @Test
    @DisplayName("Create alphanumeric feature test")
    void createAlphanumericFeatureTest(@Random EtimClassFeature etimClassFeature,
                                       @Random EtimClassFeatureValue etimClassFeatureValue) {
        etimClassFeature.setValues(List.of(etimClassFeatureValue));

        final ProductFeatureAlphanumericDto productFeatureAlphanumericDto = alphanumericFeatureFactory.createFeatureCode(etimClassFeature, etimClassFeatureValue.getValue().getDescription());

        assertThat(productFeatureAlphanumericDto.getEtimValueCode(), Matchers.is(etimClassFeatureValue.getValue().getCode()));
        assertThat(productFeatureAlphanumericDto.getEtimFeatureCode(), Matchers.is(etimClassFeature.getFeature().getCode()));
    }

    @Test
    @DisplayName("Convert alphanumeric feature to text test")
    void convertAlphanumericFeatureToTextTest(@Random EtimClassFeature etimClassFeature,
                                              @Random ProductFeatureAlphanumericDto productFeatureAlphanumericDto,
                                              @Random EtimValue etimValue) {

        final EtimClassFeatureValue etimClassFeatureValue = new EtimClassFeatureValue();
        etimClassFeatureValue.setValue(etimValue);
        etimClassFeatureValue.setClassFeatureCode(etimClassFeature);
        etimClassFeature.setValues(List.of(etimClassFeatureValue));

        productFeatureAlphanumericDto.setEtimValueCode(etimValue.getCode());

        final KeyValueDto feature = alphanumericFeatureFactory.createFeatureText(etimClassFeature, productFeatureAlphanumericDto);

        assertThat(feature.key(), Matchers.is(etimClassFeature.getFeature().getDescription()));
        assertThat(feature.value(), Matchers.is(etimValue.getDescription()));
    }

    @Test
    @DisplayName("Convert alphanumeric feature to text - value not found test")
    void convertAlphanumericFeatureToTextValueNotFoundTest(@Random EtimClassFeature etimClassFeature,
                                                           @Random ProductFeatureAlphanumericDto productFeatureAlphanumericDto,
                                                           @Random EtimValue etimValue) {
        final EtimClassFeatureValue etimClassFeatureValue = new EtimClassFeatureValue();
        etimClassFeatureValue.setValue(etimValue);
        etimClassFeatureValue.setClassFeatureCode(etimClassFeature);
        etimClassFeature.setValues(List.of(etimClassFeatureValue));

        final KeyValueDto feature = alphanumericFeatureFactory.createFeatureText(etimClassFeature, productFeatureAlphanumericDto);

        assertThat(feature.key(), Matchers.is(etimClassFeature.getFeature().getDescription()));
        assertThat(feature.value(), Matchers.is(""));
    }
}