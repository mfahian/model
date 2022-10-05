package com.malpro.model.util;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static com.malpro.model.util.FeatureHelper.LOWER_BOUND;
import static com.malpro.model.util.FeatureHelper.UNIT;
import static com.malpro.model.util.FeatureHelper.UPPER_BOUND;
import static com.malpro.model.util.FeatureHelper.VALUE;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fahian on 09.04.22.
 */
class FeatureHelperTest {

    //  Todo introduce sorting?
    @DisplayName("Split range test")
    @ParameterizedTest(name = "Range {0} is recognized as {1}")
    @CsvSource(delimiterString = "is recognized as", textBlock = """
            2-6 mm is recognized as 2-6 mm
            6-2 mm is recognized as 6-2 mm
            -5-5 mm is recognized as -5-5 mm
            5--5 mm is recognized as 5--5 mm
            -6--2 mm is recognized as -6--2 mm
            -2--6 mm is recognized as -2--6 mm
            20-60 mm is recognized as 20-60 mm
            60-20 mm is recognized as 60-20 mm
            -50-50 mm is recognized as -50-50 mm
            50--50 mm is recognized as 50--50 mm
            -60--20 mm is recognized as -60--20 mm
            -20--60 mm is recognized as -20--60 mm
            --50--50 mm is recognized as No or more then three range characters found
            5 mm is recognized as No or more then three range characters found
            5mm is recognized as No or more then three range characters found
            2-6 is recognized as 2-6
            6-2 is recognized as 6-2
            -5-5 is recognized as -5-5
            5--5 is recognized as 5--5
            -6--2 is recognized as -6--2
            -2--6 is recognized as -2--6
            20-60 is recognized as 20-60
            60-20 is recognized as 60-20
            -50-50 is recognized as -50-50
            50--50 is recognized as 50--50
            -60--20 is recognized as -60--20
            -20--60 is recognized as -20--60
            --50--50 is recognized as No or more then three range characters found
            5 is recognized as No or more then three range characters found
            """)
    void splitRangeTest(String inputRange, String expectedRange) {
        String sortedRange;
        try {
            Map<String, String> map =  FeatureHelper.splitRangeValues(inputRange);
            sortedRange = map.get(LOWER_BOUND) + "-" + map.get(UPPER_BOUND) ;
            if (map.containsKey(UNIT)) sortedRange = sortedRange + " " + map.get(UNIT);
        }
        catch (RuntimeException e) {
            sortedRange = e.getMessage();
        }
        assertThat(sortedRange, Matchers.is(expectedRange));
    }


    @DisplayName("Split simple value test")
    @ParameterizedTest(name = "Value {0} is recognized as {1}")
    @CsvSource(delimiterString = "is recognized as", textBlock = """
            5 mm is recognized as 5 mm
            5 is recognized as 5
            5mm is recognized as 5mm
            """)
    void splitSimpleValueTest(String inputValue, String expectedValue) {
        String concatValue;
        Map<String, String> map =  FeatureHelper.splitSimpleValues(inputValue);
        concatValue = map.get(VALUE);
        if (map.containsKey(UNIT)) concatValue = concatValue + " " + map.get(UNIT);
        assertThat(concatValue, Matchers.is(expectedValue));
    }

    @DisplayName("Convert text to boolean test")
    @ParameterizedTest(name = "Text {0} is considered as {1}")
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
    void convertTextToBooleanTest(String inputText, boolean booleanResult) {
       assertThat(FeatureHelper.convertToBoolean(inputText), Matchers.is(booleanResult));
    }
}