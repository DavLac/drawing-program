package io.davlac.drawingapp.context.consoleshceduler.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;
import io.davlac.drawingapp.context.inputcommand.service.InputCommandUtils;
import io.davlac.drawingapp.context.output.model.RawOutput;
import io.davlac.drawingapp.context.output.service.OutputUtils;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static io.davlac.drawingapp.context.consoleshceduler.utils.ConsoleLogUtils.printBreakLine;

@Service
public class ConsoleSchedulerService {

    private final CommandTypeService commandTypeService;

    public ConsoleSchedulerService(CommandTypeService commandTypeService) {
        this.commandTypeService = commandTypeService;
    }

    public void run() {
        System.out.println("### Welcome on Drawing app ###");

        Scanner userInput = new Scanner(System.in);
        ActionCommand currentActionCommand = null;
        Canvas canvas = Canvas.empty();
        do {
            try {
                printAskInputCommand();
                String[] rawInputCommand = InputCommandUtils.toRawInputCommand(userInput);
                InputCommand inputCommand = InputCommandUtils.toInputCommand(rawInputCommand);

                if (inputCommand.getAction() != ActionCommand.QUIT) {
                    canvas = commandTypeService.processInputCommand(inputCommand, canvas);
                    RawOutput rawOutput = OutputUtils.toRawOutput(canvas);
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
        System.out.println("### End of Drawing app ###");
    }

    private static void printAskInputCommand() {
        printBreakLine();
        System.out.println("enter command: ");
    }
}
