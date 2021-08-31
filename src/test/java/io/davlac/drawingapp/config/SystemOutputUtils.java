package io.davlac.drawingapp.config;

public class SystemOutputUtils {

    private SystemOutputUtils() {
    }

    public static String formatExpectedResult(String expectedOutputResult) {
        return expectedOutputResult.replace("\n", "\r\n");
    }
}
