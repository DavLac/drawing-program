package io.davlac.drawingapp.context.consolescheduler.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.consoleshceduler.service.ConsoleSchedulerService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;
import io.davlac.drawingapp.context.inputcommand.service.InputCommandService;
import io.davlac.drawingapp.context.output.model.RawOutput;
import io.davlac.drawingapp.context.output.service.OutputService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.QUIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsoleSchedulerServiceTest {

    private static final int HEIGHT = 6;
    private static final int WIDTH = 3;

    @Mock
    private InputCommandService inputCommandService;

    @Mock
    private CommandTypeService commandTypeService;

    @Mock
    private OutputService outputService;

    @InjectMocks
    private ConsoleSchedulerService consoleSchedulerService;

    @Test
    void runScheduler_withCreateAndQuitAction_shouldRun2TimesAndRenderCanvas1Time() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        when(inputCommandService.toRawInputCommand(any(Scanner.class))).thenReturn(new String[]{});
        when(inputCommandService.toInputCommand(any()))
                .thenReturn(InputCommand.builder().actionCommand(CREATE_CANVAS).create())
                .thenReturn(InputCommand.builder().actionCommand(QUIT).create());
        when(commandTypeService.processInputCommand(any(InputCommand.class), any(Canvas.class)))
                .thenReturn(canvas);
        when(outputService.toRawOutput(canvas)).thenReturn(new RawOutput(new String[]{}));

        consoleSchedulerService.run();

        verify(inputCommandService, times(2)).toRawInputCommand(any(Scanner.class));
        verify(inputCommandService, times(2)).toInputCommand(any());
        verify(commandTypeService, times(1)).processInputCommand(any(InputCommand.class), any(Canvas.class));
        verify(outputService, times(1)).toRawOutput(canvas);
    }

    @Test
    void runScheduler_withThrowIllegalArgumentErrorAndQuitAction_shouldRun2TimesAndNoRenderCanvas() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        when(inputCommandService.toRawInputCommand(any(Scanner.class))).thenReturn(new String[]{});
        when(inputCommandService.toInputCommand(any()))
                .thenThrow(new IllegalArgumentException())
                .thenReturn(InputCommand.builder().actionCommand(QUIT).create());

        consoleSchedulerService.run();

        verify(inputCommandService, times(2)).toRawInputCommand(any(Scanner.class));
        verify(inputCommandService, times(2)).toInputCommand(any());
        verify(commandTypeService, times(0)).processInputCommand(any(InputCommand.class), any(Canvas.class));
        verify(outputService, times(0)).toRawOutput(canvas);
    }

    @Test
    void runScheduler_withThrowNumberFormatErrorAndQuitAction_shouldRun2TimesAndNoRenderCanvas() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        when(inputCommandService.toRawInputCommand(any(Scanner.class))).thenReturn(new String[]{});
        when(inputCommandService.toInputCommand(any()))
                .thenThrow(new NumberFormatException())
                .thenReturn(InputCommand.builder().actionCommand(QUIT).create());

        consoleSchedulerService.run();

        verify(inputCommandService, times(2)).toRawInputCommand(any(Scanner.class));
        verify(inputCommandService, times(2)).toInputCommand(any());
        verify(commandTypeService, times(0)).processInputCommand(any(InputCommand.class), any(Canvas.class));
        verify(outputService, times(0)).toRawOutput(canvas);
    }
}
