package io.davlac.drawingapp.context.consoleshceduler.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;
import io.davlac.drawingapp.context.inputcommand.service.InputCommandService;

import java.util.Scanner;

import static io.davlac.drawingapp.context.output.service.OutputService.printCanvas;
import static io.davlac.drawingapp.utils.ConsoleLogUtils.printBreakLine;

public class ConsoleSchedulerService {

    private ConsoleSchedulerService() {
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        ActionCommand currentActionCommand = null;
        Canvas canvas = Canvas.empty();
        do {
            try {
                printAskInputCommand();
                InputCommand inputCommand = InputCommandService.toInputCommand(scanner);

                if (inputCommand.getAction() != ActionCommand.QUIT) {
                    canvas = CommandTypeService.processInputCommand(inputCommand, canvas);
                    printCanvas(canvas);
                }

                currentActionCommand = inputCommand.getAction();
            } catch (IllegalArgumentException ex) {
                System.err.println(ex.getMessage());
            }
        } while (currentActionCommand != ActionCommand.QUIT);
    }

    private static void printAskInputCommand() {
        printBreakLine();
        System.out.println("enter command: ");
    }
}
