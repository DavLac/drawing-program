package io.davlac.drawingapp.config;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SystemInputUtils {

    public static final String SPACE = " ";

    private SystemInputUtils() {
    }

    public static String setUserInput(List<String> inputs) {
        StringBuilder sg = new StringBuilder();
        IntStream.range(0, inputs.size()).forEach(index -> {
            if (index != 0) {
                sg.append(System.getProperty("line.separator"));
            }
            sg.append(inputs.get(index));
        });

        System.setIn(new ByteArrayInputStream(sg.toString().getBytes()));
        return sg.toString();
    }

    public static String param(String action, String... arguments) {
        StringBuilder sg = new StringBuilder();
        sg.append(action);
        Arrays.stream(arguments).forEach(arg -> sg.append(SPACE).append(arg));
        return sg.toString();
    }
}
