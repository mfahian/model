package com.malpro.model.dto;

import com.malpro.model.dto.feature.NumericFeatureFactory;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.util.FeatureHelper;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static com.malpro.model.util.FeatureHelper.VALUE;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fahian on 26.06.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class NumericFeatureFactoryTest {
    @InjectMocks
    private NumericFeatureFactory numericFeatureFactory;

    @Test
    @DisplayName("Numeric feature test")
    void numericFeatureTest(@Random EtimClassFeature etimClassFeature) {

        String value = "19 mm";
        Map<String, String> splitValues = FeatureHelper.splitSimpleValues(value);

        final ProductFeatureNumericDto productFeatureNumericDto = numericFeatureFactory.createFeatureCode(etimClassFeature, value);

        assertThat(productFeatureNumericDto.getEtimFeatureCode(), Matchers.is(etimClassFeature.getFeature().getCode()));
        MatcherAssert.assertThat(productFeatureNumericDto.getNumericValue(), Matchers.is(splitValues.get(VALUE)));
        assertThat(productFeatureNumericDto.getEtimUnitCode(), Matchers.is(etimClassFeature.getUnitOfMeasure().getCode()));
    }

    @Test
    @DisplayName("Numeric feature - without unit test")
    void numericFeatureWithoutUnitTest(@Random EtimClassFeature etimClassFeature) {
        String value = "19";
        Map<String, String> splitValues = FeatureHelper.splitSimpleValues(value);

        final ProductFeatureNumericDto productFeatureNumericDto = numericFeatureFactory.createFeatureCode(etimClassFeature, value);

        assertThat(productFeatureNumericDto.getEtimFeatureCode(), Matchers.is(etimClassFeature.getFeature().getCode()));
        MatcherAssert.assertThat(productFeatureNumericDto.getNumericValue(), Matchers.is(splitValues.get(VALUE)));
        assertThat(productFeatureNumericDto.getEtimUnitCode(), Matchers.is(etimClassFeature.getUnitOfMeasure().getCode()));
    }

    @Test
    @DisplayName("Convert numeric feature to text test")
    void convertNumericFeatureToTextTest(@Random EtimClassFeature etimClassFeature,
                                         @Random ProductFeatureNumericDto productFeatureNumericDto) {

        final KeyValue feature = numericFeatureFactory.createFeatureText(etimClassFeature, productFeatureNumericDto);

        assertThat(feature.key(), Matchers.is(etimClassFeature.getFeature().getDescription()));
        assertThat(feature.value(), Matchers.is(productFeatureNumericDto.getNumericValue() + " " + etimClassFeature.getUnitOfMeasure().getAbbreviation()));
    }
}