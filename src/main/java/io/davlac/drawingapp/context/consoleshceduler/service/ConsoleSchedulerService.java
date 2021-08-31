package io.davlac.drawingapp.context.consoleshceduler.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;
import io.davlac.drawingapp.context.inputcommand.service.InputCommandService;
import io.davlac.drawingapp.context.output.service.OutputService;

import java.util.Scanner;

import static io.davlac.drawingapp.context.consoleshceduler.utils.ConsoleLogUtils.printBreakLine;

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
                    OutputService.printCanvas(canvas);
                }

                currentActionCommand = inputCommand.getAction();
            } catch (NumberFormatException ex) {
                System.err.printf("ERROR: bad argument type %s", ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.err.println(ex.getMessage());
            }
        } while (currentActionCommand != ActionCommand.QUIT);

        printBreakLine();
    }

    private static void printAskInputCommand() {
        printBreakLine();
        System.out.println("enter command: ");
    }
}
