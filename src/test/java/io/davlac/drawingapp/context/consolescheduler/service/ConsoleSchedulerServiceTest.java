package io.davlac.drawingapp.context.consolescheduler.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.consoleshceduler.service.ConsoleSchedulerService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.util.List;

import static io.davlac.drawingapp.utils.Constants.CREATE_ACTION;
import static io.davlac.drawingapp.utils.Constants.QUIT_ACTION;
import static io.davlac.drawingapp.utils.SystemInputUtils.params;
import static io.davlac.drawingapp.utils.SystemInputUtils.setUserInput;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsoleSchedulerServiceTest {

    private InputStream sysInBackup;

    @BeforeEach
    public void setUp() {
        sysInBackup = System.in;
    }

    @AfterEach
    public void tearDown() {
        System.setIn(sysInBackup);
    }

    private static final int HEIGHT = 6;
    private static final int WIDTH = 3;

    @Mock
    private CommandTypeService commandTypeService;

    @InjectMocks
    private ConsoleSchedulerService consoleSchedulerService;

    @Test
    void runScheduler_withCreateAndQuitAction_shouldRun2TimesAndRenderCanvas1Time() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        setUserInput(List.of(
                params(CREATE_ACTION, "20", "4"),
                QUIT_ACTION)
        );
        when(commandTypeService.processInputCommand(any(InputCommand.class), any(Canvas.class)))
                .thenReturn(canvas);

        consoleSchedulerService.run();

        verify(commandTypeService, times(1)).processInputCommand(any(InputCommand.class), any(Canvas.class));
    }

    @Test
    void runScheduler_withThrowIllegalArgumentErrorAndQuitAction_shouldRun2TimesAndNoRenderCanvas() {
        setUserInput(List.of(
                " ",
                QUIT_ACTION)
        );

        consoleSchedulerService.run();

        verify(commandTypeService, times(0)).processInputCommand(any(InputCommand.class), any(Canvas.class));
    }

    @Test
    void runScheduler_withThrowNumberFormatErrorAndQuitAction_shouldRun2TimesAndNoRenderCanvas() {
        setUserInput(List.of(
                params(CREATE_ACTION, "a", "1"),
                QUIT_ACTION)
        );

        when(commandTypeService.processInputCommand(any(InputCommand.class), any(Canvas.class)))
                .thenThrow(new NumberFormatException());

        consoleSchedulerService.run();

        verify(commandTypeService, times(1)).processInputCommand(any(InputCommand.class), any(Canvas.class));
    }
}
