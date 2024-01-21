package com.malpro.model.util;

import org.springframework.util.StringUtils;

import com.malpro.model.exception.SplitNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by martin.fahian on 06.03.21.
 */
public class FeatureHelper {

    public static final String RANGE_CHARACTER_1 = "-";
    public static final String VALUE = "value";
    public static final String UPPER_BOUND = "upperBound";
    public static final String LOWER_BOUND = "lowerBound";
    public static final String UNIT = "unit";

    private FeatureHelper() {
    }

    public static boolean convertToBoolean(String value) {
        return "1".equalsIgnoreCase(value)
                || "yes".equalsIgnoreCase(value)
                || "true".equalsIgnoreCase(value)
                || "on".equalsIgnoreCase(value);
    }

    public static Map<String, String> splitSimpleValues(String inputValue) {
        String value = inputValue.trim();
        Map<String, String> splitValues = new HashMap<>();

        if (value.lastIndexOf(" ") > 0) {
            splitValues.put(VALUE, value.substring(0,value.lastIndexOf(" ")));
            splitValues.put(UNIT, value.substring(value.lastIndexOf(" ")+1, value.length()));
        } else {
            splitValues.put(VALUE, value);
        }

        return splitValues;
    }

    public static Map<String, String> splitRangeValues(String inputValue) {
        Map<String, String> splitValues = splitSimpleValues(inputValue);

        String actualValue = normalizeRangeChar(splitValues.get(VALUE));
        int rangeCharacterPosition = findSplitPosition(actualValue);
        splitValues.put(LOWER_BOUND, actualValue.substring(0,rangeCharacterPosition));
        splitValues.put(UPPER_BOUND, actualValue.substring(rangeCharacterPosition+1));

        return splitValues;
    }

    private static int findSplitPosition(String value) {
        int rangeCharacterCount = StringUtils.countOccurrencesOf(value, RANGE_CHARACTER_1);

        switch (rangeCharacterCount) {
            case 1:
                return value.indexOf(RANGE_CHARACTER_1);
            case 2:
                int firstOccurance = value.indexOf(RANGE_CHARACTER_1);
                return (firstOccurance == 0) ? value.indexOf(RANGE_CHARACTER_1, firstOccurance + 1) : firstOccurance;
            case 3:
                return value.indexOf(RANGE_CHARACTER_1, 1);
            default:
                throw new SplitNotFoundException("No or more then three range characters found");
        }
    }

    private static String normalizeRangeChar(String value) {
        return value.replace("–", RANGE_CHARACTER_1).replace(" ", "");
    }

}
