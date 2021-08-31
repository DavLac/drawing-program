package io.davlac.drawingapp.context.inputcommand.service.impl;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.InputCommandService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputCommandServiceImpl implements InputCommandService {

    private static final String ARGUMENT_SEPARATOR = " ";

    @Override
    public String[] toRawInputCommand(Scanner userInput) {
        String rawInputCommand = userInput.nextLine();
        checkRawCommand(rawInputCommand);
        rawInputCommand = rawInputCommand.trim();
        return splitWithoutEmptyElements(rawInputCommand, ARGUMENT_SEPARATOR);
    }

    @Override
    public InputCommand toInputCommand(String[] rawInputCommand) {
        String[] rawCommandArguments = ArrayUtils.remove(rawInputCommand, 0);
        String rawCommandAction = rawInputCommand[0];

        checkActionCommandLength(rawCommandAction);

        return InputCommand.builder()
                .actionCommand(ActionCommand.getActionByCommand(rawCommandAction.charAt(0)))
                .arguments(List.of(rawCommandArguments))
                .create();
    }

    private static void checkRawCommand(String rawCommand) {
        if (StringUtils.isBlank(rawCommand)) {
            throw new IllegalArgumentException("ERROR : Command is empty.");
        }
    }

    private static void checkActionCommandLength(String rawCommandAction) {
        if (rawCommandAction.length() > 1) {
            throw new IllegalArgumentException(String.format("ERROR : Action should be 1 character '%s'.", rawCommandAction));
        }
    }

    public static String[] splitWithoutEmptyElements(String str, String separator) {
        String[] strSplit = str.split(separator, -1);
        return Arrays.stream(strSplit)
                .filter(element -> !StringUtils.isEmpty(element))
                .toArray(String[]::new);
    }
}
