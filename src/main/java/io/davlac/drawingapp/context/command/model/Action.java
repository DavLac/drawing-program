package io.davlac.drawingapp.context.command.model;

import java.util.Arrays;

public enum Action {
    CREATE_CANVAS("C"),
    QUIT("Q");

    private final String command;

    Action(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Action getActionByCommand(String command) {
        return Arrays.stream(Action.values())
                .filter(action -> action.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("ERROR : Action not found with command = '%s'.", command)));
    }
}
