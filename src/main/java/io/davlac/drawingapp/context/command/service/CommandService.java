package io.davlac.drawingapp.context.command.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class CommandService {

    private CommandService() {
    }

    public static String handleRawInputCommand(Scanner scanner) {
        String rawInputCommand = scanner.nextLine();
        checkRawCommand(rawInputCommand);
        return rawInputCommand.trim();
    }

    private static void checkRawCommand(String rawCommand) {
        if (StringUtils.isBlank(rawCommand)) {
            throw new IllegalArgumentException("ERROR : Command is empty.");
        }
    }
}
