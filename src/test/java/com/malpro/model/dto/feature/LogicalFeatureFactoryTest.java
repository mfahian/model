package com.malpro.model.dto.feature;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malpro.model.dto.KeyValueDto;
import com.malpro.model.dto.ProductFeatureLogicalDto;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.model.EtimFeature;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;

/**
 * Created by fahian on 26.06.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class LogicalFeatureFactoryTest {

    @InjectMocks
    private LogicalFeatureFactory logicalFeatureFactory;

    @DisplayName("Create boolean feature test")
    @ParameterizedTest(name = "Feature with value {0} is created with {1}")
    @CsvSource(
            delimiterString = "is considered as",
            textBlock = """
                    1 is considered as true
                    yes is considered as true
                    true is considered as true
                    on is considered as true
                    0 is considered as false
                    no is considered as false
                    false is considered as false
                    off is considered as false
                    axasdfa is considered as false
                    """)
    void createBooleanFeatureTest(String inputValue, boolean result) {
        final EtimFeature etimFeature = new EtimFeature();
        etimFeature.setCode("EF00001");
        final EtimClassFeature etimClassFeature = new EtimClassFeature();
        etimClassFeature.setFeature(etimFeature);

        final ProductFeatureLogicalDto productFeatureLogicalDto = logicalFeatureFactory.createFeatureCode(etimClassFeature, inputValue);

        assertThat(productFeatureLogicalDto.getBooleanValue(), Matchers.is(result));
        assertThat(productFeatureLogicalDto.getEtimFeatureCode(), Matchers.is(etimClassFeature.getFeature().getCode()));
    }

    @Test
    @DisplayName("Convert logical feature to text test")
    void convertLogicalFeatureToTextTest(@Random EtimClassFeature etimClassFeature,
                                         @Random ProductFeatureLogicalDto productFeatureLogicalDto) {

        final KeyValueDto feature = logicalFeatureFactory.createFeatureText(etimClassFeature, productFeatureLogicalDto);

        assertThat(feature.key(), Matchers.is(etimClassFeature.getFeature().getDescription()));
        assertThat(feature.value(), Matchers.is(productFeatureLogicalDto.getBooleanValue().toString()));
    }

    @Test
    @DisplayName("Convert logical feature to text - value is null test")
    void convertLogicalFeatureToTextValueIsNullTest(@Random EtimClassFeature etimClassFeature,
                                                    @Random ProductFeatureLogicalDto productFeatureLogicalDto) {
        productFeatureLogicalDto.setBooleanValue(null);

        final KeyValueDto feature = logicalFeatureFactory.createFeatureText(etimClassFeature, productFeatureLogicalDto);

        assertThat(feature.key(), Matchers.is(etimClassFeature.getFeature().getDescription()));
        assertThat(feature.value(), Matchers.is(""));
    }
}