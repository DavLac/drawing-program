package io.davlac.drawingapp;

import io.davlac.drawingapp.context.consoleshceduler.service.ConsoleSchedulerService;
import io.davlac.drawingapp.utils.ValidatorUtils;

import static io.davlac.drawingapp.utils.ConsoleLogUtils.printBreakLine;

public class DrawingApplication {

    /**
     * The drawing program create canvas and generate different shapes on it
     */
    public static void main(String[] args) {
        eagerClassLoading();
        System.out.println("### Welcome on Drawing app ###");

        ConsoleSchedulerService.run();

        printBreakLine();
        System.out.println("### End of Drawing app ###");
    }

    private static void eagerClassLoading() {
        ValidatorUtils.init();
    }
}
