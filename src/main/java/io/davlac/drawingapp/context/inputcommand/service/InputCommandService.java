package io.davlac.drawingapp.context.inputcommand.service;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;

import static io.davlac.drawingapp.utils.StringsUtils.splitWithoutEmptyElements;

public class InputCommandService {

    private static final String ARGUMENT_SEPARATOR = " ";

    private InputCommandService() {
    }

    public static InputCommand toInputCommand(Scanner userInput) {
        String[] rawInputCommand = toRawInputCommand(userInput);
        return fromRawInputCommand(rawInputCommand);
    }

    private static InputCommand fromRawInputCommand(String[] rawInputCommand) {
        String[] rawCommandArguments = ArrayUtils.remove(rawInputCommand, 0);

        return InputCommand.builder()
                .actionCommand(ActionCommand.getActionByCommand(rawInputCommand[0]))
                .arguments(List.of(rawCommandArguments))
                .create();
    }

    private static String[] toRawInputCommand(Scanner userInput) {
        String rawInputCommand = userInput.nextLine();
        checkRawCommand(rawInputCommand);
        rawInputCommand = rawInputCommand.trim();
        return splitWithoutEmptyElements(rawInputCommand, ARGUMENT_SEPARATOR);
    }

    private static void checkRawCommand(String rawCommand) {
        if (StringUtils.isBlank(rawCommand)) {
            throw new IllegalArgumentException("ERROR : Command is empty.");
        }
    }
}
