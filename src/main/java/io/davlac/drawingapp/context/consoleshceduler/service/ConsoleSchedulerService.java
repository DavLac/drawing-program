package io.davlac.drawingapp.context.consoleshceduler.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;
import io.davlac.drawingapp.context.inputcommand.service.InputCommandService;
import io.davlac.drawingapp.context.output.model.RawOutput;
import io.davlac.drawingapp.context.output.service.OutputService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static io.davlac.drawingapp.context.consoleshceduler.utils.ConsoleLogUtils.printBreakLine;

@Service
public class ConsoleSchedulerService {

    private final OutputService outputService;
    private final CommandTypeService commandTypeService;
    private final InputCommandService inputCommandService;

    public ConsoleSchedulerService(OutputService outputService,
                                   CommandTypeService commandTypeService,
                                   InputCommandService inputCommandService) {
        this.outputService = outputService;
        this.commandTypeService = commandTypeService;
        this.inputCommandService = inputCommandService;
    }

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
