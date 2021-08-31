package io.davlac.drawingapp.context.consoleshceduler.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;
import io.davlac.drawingapp.context.inputcommand.service.InputCommandService;
import io.davlac.drawingapp.context.inputcommand.service.impl.CommandTypeServiceImpl;
import io.davlac.drawingapp.context.inputcommand.service.impl.InputCommandServiceImpl;
import io.davlac.drawingapp.context.output.model.RawOutput;
import io.davlac.drawingapp.context.output.service.OutputService;
import io.davlac.drawingapp.context.output.service.impl.OutputServiceImpl;

import java.util.Scanner;

import static io.davlac.drawingapp.context.consoleshceduler.utils.ConsoleLogUtils.printBreakLine;

public class ConsoleSchedulerService {

    private final OutputService outputService = new OutputServiceImpl();
    private final CommandTypeService commandTypeService = new CommandTypeServiceImpl();
    private final InputCommandService inputCommandService = new InputCommandServiceImpl();

    public void run() {
        Scanner userInput = new Scanner(System.in);
        ActionCommand currentActionCommand = null;
        Canvas canvas = Canvas.empty();
        do {
            try {
                printAskInputCommand();
                String[] rawInputCommand = inputCommandService.toRawInputCommand(userInput);
                InputCommand inputCommand = inputCommandService.toInputCommand(rawInputCommand);

                if (inputCommand.getAction() != ActionCommand.QUIT) {
                    canvas = commandTypeService.processInputCommand(inputCommand, canvas);
                    RawOutput rawOutput = outputService.toRawOutput(canvas);
                    rawOutput.print();
                }

                currentActionCommand = inputCommand.getAction();
            } catch (NumberFormatException ex) {
                System.out.printf("ERROR: bad argument type %s", ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        } while (currentActionCommand != ActionCommand.QUIT);

        printBreakLine();
    }

    private static void printAskInputCommand() {
        printBreakLine();
        System.out.println("enter command: ");
    }
}
