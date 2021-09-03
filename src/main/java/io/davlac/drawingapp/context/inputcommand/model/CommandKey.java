package io.davlac.drawingapp.context.inputcommand.model;

import java.util.Map;

public class CommandKey {

    private CommandKey() {
    }

    public enum Key {
        BLANK("${blank}");

        private final String value;

        Key(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static final Map<String, String> COMMAND_KEY = Map.of(
            Key.BLANK.getValue(), " "
    );
}
