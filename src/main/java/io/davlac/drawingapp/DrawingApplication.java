package io.davlac.drawingapp;

import io.davlac.drawingapp.config.AppConfig;
import io.davlac.drawingapp.context.canvascontent.utils.ValidatorUtils;
import io.davlac.drawingapp.context.consoleshceduler.service.ConsoleSchedulerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DrawingApplication {

    /**
     * The drawing program create canvas and generate different shapes on it
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        eagerClassLoading();

        System.out.println("### Welcome on Drawing app ###");

        ConsoleSchedulerService consoleSchedulerService = context.getBean(ConsoleSchedulerService.class);
        consoleSchedulerService.run();

        System.out.println("### End of Drawing app ###");
    }

    private static void eagerClassLoading() {
        ValidatorUtils.init();
    }
}
