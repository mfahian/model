package com.malpro.model.dto;

import com.malpro.model.dto.feature.RangeFeatureFactory;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.model.EtimFeature;
import com.malpro.model.model.EtimUnit;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fahian on 27.06.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class RangeFeatureFactoryTest {

    @InjectMocks
    private RangeFeatureFactory rangeFeatureFactory;

    @DisplayName("Create range feature test")
    @ParameterizedTest(name = "Range feature {0} has lowerbound {1} and upperbound {2}")
    @CsvSource(
            delimiterString = "|",
            textBlock = """
                2-6 mm | 2 | 6
                6-2 mm | 6 | 2
                -5-5 mm | -5 | 5
                5--5 mm | 5 | -5
                -6--2 mm | -6 | -2
                -2--6 mm | -2 | -6
                20-60 mm | 20 | 60
                60-20 mm | 60 | 20
                -50-50 mm | -50 | 50
                50--50 mm | 50 | -50
                -60--20 mm | -60 | -20
                -20--60 mm | -20 | -60
                2-6 | 2 | 6
                6-2 | 6 | 2
                -5-5 | -5 | 5
                5--5 | 5 | -5
                -6--2 | -6 | -2
                -2--6 | -2 | -6
                20-60 | 20 | 60
                60-20 | 60 | 20
                -50-50 | -50 | 50
                50--50 | 50 | -50
                -60--20 | -60 | -20
                -20--60 | -20 | -60
                    """)
    void createRangeFeatureTest(String inputValue, String lowerBound, String upperBound) {

        final EtimFeature etimFeature = new EtimFeature();
        etimFeature.setCode("EF00001");
        final EtimUnit etimUnit = new EtimUnit();
        etimUnit.setCode("EU00001");
        final EtimClassFeature etimClassFeature = new EtimClassFeature();
        etimClassFeature.setFeature(etimFeature);
        etimClassFeature.setUnitOfMeasure(etimUnit);


        final ProductFeatureRangeDto productFeatureRangeDto = rangeFeatureFactory.createFeatureCode(etimClassFeature, inputValue);

        assertThat(productFeatureRangeDto.getEtimFeatureCode(), Matchers.is(etimClassFeature.getFeature().getCode()));
        MatcherAssert.assertThat(productFeatureRangeDto.getLowerBound(), Matchers.is(lowerBound));
        MatcherAssert.assertThat(productFeatureRangeDto.getUpperBound(), Matchers.is(upperBound));
        assertThat(productFeatureRangeDto.getEtimUnitCode(), Matchers.is(etimClassFeature.getUnitOfMeasure().getCode()));
    }

    @DisplayName("Create range feature with error test")
    @ParameterizedTest(name = "Range feature {0} fails as {1}")
    @CsvSource(
            delimiterString = " is expected to fail with ",
            textBlock = """
                    5 is expected to fail with No or more then three range characters found
                    --50--50 mm is expected to fail with No or more then three range characters found
                    5 mm is expected to fail with No or more then three range characters found
                    5mm is expected to fail with No or more then three range characters found
                    """)
    void createRangeFeatureWithErrorTest(String inputValue, String errorMessage) {
        final EtimClassFeature etimClassFeature = new EtimClassFeature();

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> rangeFeatureFactory.createFeatureCode(etimClassFeature, inputValue));

        assertThat(thrown.getMessage(), Matchers.is(errorMessage));
    }

    @Test
    @DisplayName("Convert range feature to text test")
    void convertRangeFeatureToTextTest(@Random EtimClassFeature etimClassFeature,
                                       @Random ProductFeatureRangeDto productFeatureRangeDto) {

        final KeyValue feature = rangeFeatureFactory.createFeatureText(etimClassFeature, productFeatureRangeDto);

        assertThat(feature.key(), Matchers.is(etimClassFeature.getFeature().getDescription()));
        assertThat(feature.value(), Matchers.is(productFeatureRangeDto.getLowerBound() + " - " + productFeatureRangeDto.getUpperBound() + " " + etimClassFeature.getUnitOfMeasure().getAbbreviation()));
    }
}