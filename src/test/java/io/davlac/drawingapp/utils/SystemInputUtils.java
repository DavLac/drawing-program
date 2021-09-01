package io.davlac.drawingapp.utils;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SystemInputUtils {

    public static final String SPACE = " ";

    private SystemInputUtils() {
    }

    public static void setUserInput(List<String> inputs) {
        StringBuilder sg = new StringBuilder();
        IntStream.range(0, inputs.size()).forEach(index -> {
            if (index != 0) {
                sg.append(System.getProperty("line.separator"));
            }
            sg.append(inputs.get(index));
        });

        System.setIn(new ByteArrayInputStream(sg.toString().getBytes()));
    }

    public static Scanner setUserInput(String input) {
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    public static String params(String action, String... arguments) {
        StringBuilder sg = new StringBuilder();
        sg.append(action);
        Arrays.stream(arguments).forEach(arg -> sg.append(SPACE).append(arg));
        return sg.toString();
    }

    public static String buildParams(String... arguments) {
        StringBuilder sg = new StringBuilder();
        Arrays.stream(arguments).forEach(arg -> sg.append(SPACE).append(arg));
        return sg.toString();
    }
}
