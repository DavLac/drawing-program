package io.davlac.drawingapp.context.inputcommand.model;

import java.util.Arrays;

import static io.davlac.drawingapp.context.inputcommand.model.CommandType.APPLICATION_OPERATION;
import static io.davlac.drawingapp.context.inputcommand.model.CommandType.CANVAS_BODY;
import static io.davlac.drawingapp.context.inputcommand.model.CommandType.CANVAS_CONTENT;

public enum ActionCommand {
    CREATE_CANVAS('C', CANVAS_BODY, 2),
    DRAW_LINE('L', CANVAS_CONTENT, 4),
    DRAW_RECTANGLE('R', CANVAS_CONTENT, 4),
    DRAW_BUCKET_FILL('B', CANVAS_CONTENT, 3),
    QUIT('Q', APPLICATION_OPERATION, 0);

    private final Character command;
    private final CommandType commandType;
    private final int minArgumentSize;

    ActionCommand(char command, CommandType commandType, int minArgumentSize) {
        this.command = command;
        this.commandType = commandType;
        this.minArgumentSize = minArgumentSize;
    }

    public char getCommand() {
        return command;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public int getMinArgumentSize() {
        return minArgumentSize;
    }

    public static ActionCommand getActionByCommand(char command) {
        return Arrays.stream(ActionCommand.values())
                .filter(actionCommand -> actionCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("ERROR : Action not found with command = '%s'.", command)));
    }
}
