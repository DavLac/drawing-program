package io.davlac.drawingapp;

import io.davlac.drawingapp.context.canvascontent.utils.ValidatorUtils;
import io.davlac.drawingapp.context.consoleshceduler.service.ConsoleSchedulerService;

public class DrawingApplication {

    /**
     * The drawing program create canvas and generate different shapes on it
     */
    public static void main(String[] args) {
        eagerClassLoading();
        System.out.println("### Welcome on Drawing app ###");

        ConsoleSchedulerService consoleSchedulerService = new ConsoleSchedulerService();
        consoleSchedulerService.run();

        System.out.println("### End of Drawing app ###");
    }

    private static void eagerClassLoading() {
        ValidatorUtils.init();
    }
}
