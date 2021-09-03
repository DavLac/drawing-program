package io.davlac.drawingapp.context.inputcommand.utils;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public interface InputCommandUtils {

    String ARGUMENT_SEPARATOR = " ";

    static String[] toRawInputCommand(Scanner userInput) {
        String rawInput = userInput.nextLine();
        checkRawCommand(rawInput);
        rawInput = rawInput.trim();
        String[] rawInputSplit = rawInput.split(ARGUMENT_SEPARATOR, -1);
        return removeEmptyElements(rawInputSplit);
    }

    static InputCommand toInputCommand(@NotNull String[] rawInputCommand) {
        String[] rawCommandArguments = ArrayUtils.remove(rawInputCommand, 0);
        String rawCommandAction = rawInputCommand[0];

        checkActionCommandLength(rawCommandAction);

        return InputCommand.builder()
                .actionCommand(ActionCommand.getActionByCommand(rawCommandAction.charAt(0)))
                .arguments(List.of(rawCommandArguments))
                .create();
    }

    static boolean checkArgumentLength(List<String> arguments, ActionCommand actionCommand) {
        if (arguments == null || arguments.size() < actionCommand.getMinArgumentSize()) {
            throw new IllegalArgumentException(String.format("ERROR: Action '%s' - not enough arguments (%d)",
                    actionCommand, actionCommand.getMinArgumentSize()));
        }
        return true;
    }

    private static void checkRawCommand(String rawCommand) {
        if (StringUtils.isBlank(rawCommand)) {
            throw new IllegalArgumentException("ERROR : Command is empty");
        }
    }

    private static void checkActionCommandLength(String rawCommandAction) {
        if (rawCommandAction.length() > 1) {
            throw new IllegalArgumentException(String.format("ERROR : Action should be 1 character '%s'", rawCommandAction));
        }
    }

    private static String[] removeEmptyElements(String[] str) {
        return Arrays.stream(str)
                .filter(element -> !StringUtils.isEmpty(element))
                .toArray(String[]::new);
    }
}
