package io.davlac.drawingapp.context.inputcommand.model;

import java.util.Arrays;

import static io.davlac.drawingapp.context.inputcommand.model.CommandType.APPLICATION_OPERATION;
import static io.davlac.drawingapp.context.inputcommand.model.CommandType.CANVAS_BODY;
import static io.davlac.drawingapp.context.inputcommand.model.CommandType.CANVAS_CONTENT;

public enum ActionCommand {
    CREATE_CANVAS("C", CANVAS_BODY),
    DRAW_LINE("L", CANVAS_CONTENT),
    DRAW_RECTANGLE("R", CANVAS_CONTENT),
    DRAW_BUCKET_FILL("B", CANVAS_CONTENT),
    QUIT("Q", APPLICATION_OPERATION);

    private final String command;
    private final CommandType commandType;

    ActionCommand(String command, CommandType commandType) {
        this.command = command;
        this.commandType = commandType;
    }

    public String getCommand() {
        return command;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public static ActionCommand getActionByCommand(String command) {
        return Arrays.stream(ActionCommand.values())
                .filter(actionCommand -> actionCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("ERROR : Action not found with command = '%s'.", command)));
    }
}
