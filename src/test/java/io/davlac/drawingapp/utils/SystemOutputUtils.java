package io.davlac.drawingapp.utils;

public class SystemOutputUtils {

    private SystemOutputUtils() {
    }

    public static String formatExpectedResult(String expectedOutputResult) {
        return expectedOutputResult.replace("\n", "\r\n");
    }
}
