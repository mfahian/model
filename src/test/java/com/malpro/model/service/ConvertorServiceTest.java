package com.malpro.model.service;

import com.malpro.model.dto.ProductFeatureAlphanumericDto;
import com.malpro.model.dto.ProductFeatureLogicalDto;
import com.malpro.model.dto.ProductFeatureNumericDto;
import com.malpro.model.dto.ProductFeatureRangeDto;
import com.malpro.model.dto.ReferenceFeatureSystem;
import com.malpro.model.model.EtimClass;
import com.malpro.model.model.EtimClassFeature;
import com.malpro.model.model.EtimClassFeatureValue;
import com.malpro.model.model.EtimFeature;
import com.malpro.model.model.EtimFeatureType;
import com.malpro.model.model.EtimUnit;
import com.malpro.model.model.EtimValue;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fahian on 07.07.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
public class ConvertorServiceTest {

    private static final String CLASS_CODE = "EC000000";
    private static final String UNIT_CODE = "EU000000";
    private static final String VALUE_CODE = "EV000000";
    private static final ReferenceFeatureSystem ETIM_VERSION = ReferenceFeatureSystem.ETIM_8_0;
    private static final String UNIT_ABBREV = "m";
    private static final String NUMERIC_FEATURE = "Numeric feature";
    private static final String NUMERIC_FEATURE_CODE = "NF0000000";
    private static final String LOGICAL_FEATURE = "Logical feature";
    private static final String LOGICAL_FEATURE_CODE = "LF0000000";
    private static final String RANGE_FEATURE = "Range feature";
    private static final String RANGE_FEATURE_CODE = "RF0000000";
    private static final String ALPHANUMERIC_FEATURE = "Alphanumeric feature";
    private static final String ALPHANUMERIC_FEATURE_CODE = "AF0000000";
    private static final String ETIM_VALUE = "Etim value 1";
    static EtimClass etimClass;

    @InjectMocks
    private ConvertorService convertorService;

    @BeforeAll
    public static void initTest() {
        // Etim unit definition
        final var etimUnit = new EtimUnit();
        etimUnit.setCode(UNIT_CODE);
        etimUnit.setAbbreviation(UNIT_ABBREV);
        etimUnit.setDescription("meter");

        // Etim values definition
        final var etimValue1 = new EtimValue();
        etimValue1.setCode(VALUE_CODE);
        etimValue1.setDescription(ETIM_VALUE);

        // Logical etim feature definition
        final var logicalEtimFeature = new EtimFeature();

        logicalEtimFeature.setCode(LOGICAL_FEATURE_CODE);
        logicalEtimFeature.setType(EtimFeatureType.LOGICAL);
        logicalEtimFeature.setDescription(LOGICAL_FEATURE);

        final var logicalEtimClassFeature = new EtimClassFeature();
        logicalEtimClassFeature.setEtimClass(etimClass);
        logicalEtimClassFeature.setFeature(logicalEtimFeature);

        // Numeric etim feature definition
        final var numericEtimFeature = new EtimFeature();
        numericEtimFeature.setCode(NUMERIC_FEATURE_CODE);
        numericEtimFeature.setType(EtimFeatureType.NUMERIC);
        numericEtimFeature.setDescription(NUMERIC_FEATURE);

        final var numericEtimClassFeature = new EtimClassFeature();
        numericEtimClassFeature.setEtimClass(etimClass);
        numericEtimClassFeature.setFeature(numericEtimFeature);
        numericEtimClassFeature.setUnitOfMeasure(etimUnit);

        // Range etim feature definition
        final var rangeEtimFeature = new EtimFeature();
        rangeEtimFeature.setCode(RANGE_FEATURE_CODE);
        rangeEtimFeature.setType(EtimFeatureType.RANGE);
        rangeEtimFeature.setDescription(RANGE_FEATURE);

        final var rangeEtimClassFeature = new EtimClassFeature();
        rangeEtimClassFeature.setEtimClass(etimClass);
        rangeEtimClassFeature.setFeature(rangeEtimFeature);
        rangeEtimClassFeature.setUnitOfMeasure(etimUnit);

        // Alphanumeric etim feature definition
        final var alphanumericEtimFeature = new EtimFeature();
        alphanumericEtimFeature.setCode(ALPHANUMERIC_FEATURE_CODE);
        alphanumericEtimFeature.setType(EtimFeatureType.ALPHANUMERIC);
        alphanumericEtimFeature.setDescription(ALPHANUMERIC_FEATURE);

        final var alphanumericEtimClassFeature = new EtimClassFeature();
        alphanumericEtimClassFeature.setEtimClass(etimClass);
        alphanumericEtimClassFeature.setFeature(alphanumericEtimFeature);

        final var etimClassFeatureValue1 = new EtimClassFeatureValue();
        etimClassFeatureValue1.setValue(etimValue1);
        etimClassFeatureValue1.setClassFeatureCode(alphanumericEtimClassFeature);

        final var etimClassFeatureValue2 = new EtimClassFeatureValue();
        etimClassFeatureValue2.setValue(etimValue1);
        etimClassFeatureValue2.setClassFeatureCode(alphanumericEtimClassFeature);

        alphanumericEtimClassFeature.setValues(List.of(etimClassFeatureValue1, etimClassFeatureValue2));

        // Etim class definition
        etimClass = new EtimClass();
        etimClass.setCode(CLASS_CODE);
        etimClass.setFeatures(List.of(logicalEtimClassFeature,
                numericEtimClassFeature,
                rangeEtimClassFeature,
                alphanumericEtimClassFeature));

    }

    @Test
    @DisplayName("Convert text to code - no features provided test")
    void convertDataNoFeaturesProvidedTest() {

        final var productFeaturesDto = convertorService.convertFeaturesFromText(CLASS_CODE,
                ETIM_VERSION,
                etimClass.getFeatures(),
                new HashMap<>()
        );

        assertThat(productFeaturesDto.getProductFeature().size(), Matchers.is(0));
        assertThat(productFeaturesDto.getEtimClass(), Matchers.is(CLASS_CODE));
        assertThat(productFeaturesDto.getReferenceFeatureSystem(), Matchers.is(ETIM_VERSION));
    }

    @Test
    @DisplayName("Convert text to code - not existent feature test")
    void convertDataNotExistentProvidedTest() {

        final var inputFeatures = new HashMap<String, String>();
        inputFeatures.put("Test feature", "10 " + UNIT_ABBREV);

        final var productFeaturesDto = convertorService.convertFeaturesFromText(CLASS_CODE,
                ETIM_VERSION,
                etimClass.getFeatures(),
                inputFeatures
        );

        assertThat(productFeaturesDto.getProductFeature().size(), Matchers.is(0));
        assertThat(productFeaturesDto.getEtimClass(), Matchers.is(CLASS_CODE));
        assertThat(productFeaturesDto.getReferenceFeatureSystem(), Matchers.is(ETIM_VERSION));
    }

    @ParameterizedTest
    @DisplayName("Convert text to code - numeric feature unit test")
    @ValueSource(strings = {"10 " + UNIT_ABBREV, "10", "10 kg"})
    void convertDataNumericFeatureUnitTest(String value) {
        final var inputFeatures = new HashMap<String, String>();
        inputFeatures.put(NUMERIC_FEATURE, value);

        final var productFeaturesDto = convertorService.convertFeaturesFromText(CLASS_CODE,
                ETIM_VERSION,
                etimClass.getFeatures(),
                inputFeatures
        );

        final var productFeatureNumericDto = (ProductFeatureNumericDto) productFeaturesDto.getProductFeature().stream().findFirst().orElse(new ProductFeatureNumericDto());

        assertThat(productFeaturesDto.getProductFeature().size(), Matchers.is(1));
        assertThat(productFeatureNumericDto.getEtimUnitCode(), Matchers.is(UNIT_CODE));
        assertThat(productFeaturesDto.getEtimClass(), Matchers.is(CLASS_CODE));
        assertThat(productFeaturesDto.getReferenceFeatureSystem(), Matchers.is(ETIM_VERSION));
    }

    @ParameterizedTest
    @DisplayName("Convert text to code - logical feature test")
    @CsvSource({"true, TRUE", "false, FALSE"})
    void convertDataLogicalFeatureTrueTest(String value, Boolean result) {
        final var inputFeatures = new HashMap<String, String>();
        inputFeatures.put(LOGICAL_FEATURE, value);

        final var productFeaturesDto = convertorService.convertFeaturesFromText(CLASS_CODE,
                ETIM_VERSION,
                etimClass.getFeatures(),
                inputFeatures
        );

        final var productFeatureLogicalDto = (ProductFeatureLogicalDto) productFeaturesDto.getProductFeature().stream().findFirst().orElse(new ProductFeatureLogicalDto());

        assertThat(productFeaturesDto.getProductFeature().size(), Matchers.is(1));
        assertThat(productFeatureLogicalDto.getBooleanValue(), Matchers.is(result));
        assertThat(productFeaturesDto.getEtimClass(), Matchers.is(CLASS_CODE));
        assertThat(productFeaturesDto.getReferenceFeatureSystem(), Matchers.is(ETIM_VERSION));
    }

    @ParameterizedTest
    @DisplayName("Convert text to code - range feature with unit test")
    @CsvSource({"5,10,m", "5,10,", "5,10,kg"})
    void convertDataRangeFeatureWithUnitTest(String lowerBound, String upperBound, String unit) {
        final var inputFeatures = new HashMap<String, String>();
        inputFeatures.put(RANGE_FEATURE, lowerBound + "-" + upperBound + " " + unit);

        final var productFeaturesDto = convertorService.convertFeaturesFromText(CLASS_CODE,
                ETIM_VERSION,
                etimClass.getFeatures(),
                inputFeatures
        );

        final var productFeatureRangeDto = (ProductFeatureRangeDto) productFeaturesDto.getProductFeature().stream().findFirst().orElse(new ProductFeatureRangeDto());

        assertThat(productFeaturesDto.getProductFeature().size(), Matchers.is(1));
        assertThat(productFeatureRangeDto.getLowerBound(), Matchers.is(lowerBound));
        assertThat(productFeatureRangeDto.getUpperBound(), Matchers.is(upperBound));
        assertThat(productFeaturesDto.getEtimClass(), Matchers.is(CLASS_CODE));
        assertThat(productFeatureRangeDto.getEtimUnitCode(), Matchers.is(UNIT_CODE));
        assertThat(productFeaturesDto.getReferenceFeatureSystem(), Matchers.is(ETIM_VERSION));
    }

    @Test
    @DisplayName("Convert text to code - alphanumeric feature test")
    void convertDataAlphanumericFeatureTest() {
        final var inputFeatures = new HashMap<String, String>();
        inputFeatures.put(ALPHANUMERIC_FEATURE, ETIM_VALUE);

        final var productFeaturesDto = convertorService.convertFeaturesFromText(CLASS_CODE,
                ETIM_VERSION,
                etimClass.getFeatures(),
                inputFeatures
        );

        final var productFeatureAlphanumericDto = (ProductFeatureAlphanumericDto) productFeaturesDto.getProductFeature().stream().findFirst().orElse(new ProductFeatureAlphanumericDto());

        assertThat(productFeaturesDto.getProductFeature().size(), Matchers.is(1));
        assertThat(productFeaturesDto.getEtimClass(), Matchers.is(CLASS_CODE));
        assertThat(productFeatureAlphanumericDto.getEtimValueCode(), Matchers.is(VALUE_CODE));
        assertThat(productFeaturesDto.getReferenceFeatureSystem(), Matchers.is(ETIM_VERSION));
    }

    @Test
    @DisplayName("Convert text to code - alphanumeric feature non existent value test")
    void convertDataAlphanumericFeatureNonExistentValueTest() {
        final var inputFeatures = new HashMap<String, String>();
        inputFeatures.put(ALPHANUMERIC_FEATURE, "Test value");

        final var productFeaturesDto = convertorService.convertFeaturesFromText(CLASS_CODE,
                ETIM_VERSION,
                etimClass.getFeatures(),
                inputFeatures
        );

        final var productFeatureAlphanumericDto = (ProductFeatureAlphanumericDto) productFeaturesDto.getProductFeature().stream().findFirst().orElse(new ProductFeatureAlphanumericDto());

        assertThat(productFeaturesDto.getProductFeature().size(), Matchers.is(1));
        assertThat(productFeaturesDto.getEtimClass(), Matchers.is(CLASS_CODE));
        assertThat(productFeatureAlphanumericDto.getEtimValueCode(), Matchers.nullValue());
        assertThat(productFeaturesDto.getReferenceFeatureSystem(), Matchers.is(ETIM_VERSION));
    }

    @ParameterizedTest
    @DisplayName("Convert code to text - logical feature test")
    @ValueSource(booleans = {true, false})
    void convertCodeToTextLogicalFeatureTest(boolean featureValue) {

        final var productFeatureLogicalDto = new ProductFeatureLogicalDto();
        productFeatureLogicalDto.setBooleanValue(featureValue);
        productFeatureLogicalDto.setEtimFeatureCode(LOGICAL_FEATURE_CODE);

        final var productFeaturesTextDto = convertorService.convertFeaturesFromCode(CLASS_CODE,
                ETIM_VERSION,
                etimClass.getFeatures(),
                Set.of(productFeatureLogicalDto));

        assertThat(productFeaturesTextDto.getEtimClass(), Matchers.is(CLASS_CODE));
        assertThat(productFeaturesTextDto.getReferenceFeatureSystem(), Matchers.is(ETIM_VERSION));
        assertThat(productFeaturesTextDto.getFeaturesMap().size(), Matchers.is(1));
        assertThat(productFeaturesTextDto.getFeaturesMap().containsKey(LOGICAL_FEATURE), Matchers.is(true));
        assertThat(productFeaturesTextDto.getFeaturesMap().get(LOGICAL_FEATURE), Matchers.is(Boolean.toString(featureValue)));

    }
}
