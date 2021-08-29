package io.davlac.drawingapp;

import io.davlac.drawingapp.context.command.model.Action;
import io.davlac.drawingapp.context.command.model.InputCommand;

import java.util.Scanner;

import static io.davlac.drawingapp.context.command.service.CommandService.handleRawInputCommand;
import static io.davlac.drawingapp.context.utils.LogUtils.printBreakLine;

public class DrawingApplication {

    /**
     * The drawing program create canvas and generate different shapes on it
     */
    public static void main(String[] args) {
        System.out.println("### Welcome on Drawing app ###");

        Scanner scanner = new Scanner(System.in);
        processInputCommands(scanner);

        printBreakLine();
        System.out.println("### End of Drawing app ###");
    }

    public static void processInputCommands(Scanner scanner) {
        Action currentAction = null;
        do {
            printBreakLine();
            System.out.println("enter command: ");
            InputCommand inputCommand;
            try {
                String rawInputCommand = handleRawInputCommand(scanner);
                inputCommand = new InputCommand(rawInputCommand);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            System.out.println(inputCommand);

            currentAction = inputCommand.getAction();
        } while (currentAction != Action.QUIT);
    }
}
