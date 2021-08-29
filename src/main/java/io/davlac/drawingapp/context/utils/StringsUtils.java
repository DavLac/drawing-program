package io.davlac.drawingapp.context.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringsUtils {

    private StringsUtils() {
    }

    public static String[] splitWithoutEmptyElements(String str, String separator) {
        return Arrays.stream(str.split(separator, -1))
                .filter(element -> !StringUtils.isEmpty(element))
                .toArray(String[]::new);
    }
}
