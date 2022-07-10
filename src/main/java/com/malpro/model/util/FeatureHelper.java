package com.malpro.model.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by martin.fahian on 06.03.21.
 */
public class FeatureHelper {

    public static final String RANGE_CHARACTER = "-";
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
            String[] split = value.split(" ");  //Todo Problem when range value has more than one space
            splitValues.put(VALUE, split[0]);
            splitValues.put(UNIT, split[1]);
        } else {
            splitValues.put(VALUE, value);
        }

        return splitValues;
    }

    public static Map<String, String> splitRangeValues(String inputValue) {
        Map<String, String> splitValues = splitSimpleValues(inputValue);

        String actualValue = splitValues.get(VALUE);
        int rangeCharacterPosition = findSplitPosition(actualValue);
        splitValues.put(LOWER_BOUND, actualValue.substring(0,rangeCharacterPosition));
        splitValues.put(UPPER_BOUND, actualValue.substring(rangeCharacterPosition+1));

        return splitValues;
    }

    private static int findSplitPosition(String value) {

        int rangeCharacterCount = StringUtils.countOccurrencesOf(value, RANGE_CHARACTER);

        switch (rangeCharacterCount) {
            case 1:
                return value.indexOf(RANGE_CHARACTER);
            case 2:
                int firstOccurance = value.indexOf(RANGE_CHARACTER);
                return (firstOccurance == 0) ? value.indexOf(RANGE_CHARACTER, firstOccurance + 1) : firstOccurance;
            case 3:
                return value.indexOf(RANGE_CHARACTER, 1);
            default:
                throw new RuntimeException("No or more then three range characters found");
        }
    }

}
