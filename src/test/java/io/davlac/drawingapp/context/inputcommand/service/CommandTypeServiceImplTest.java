package io.davlac.drawingapp.context.inputcommand.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.service.ProcessCanvasService;
import io.davlac.drawingapp.context.canvascontent.service.ProcessCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.impl.CommandTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.davlac.drawingapp.context.canvasbody.utils.ArrayUtils.fillArrayWithSpaces;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_BUCKET_FILL;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.QUIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandTypeServiceImplTest {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 6;

    @Mock
    private ProcessCanvasService processCanvasService;

    @Mock
    private ProcessCanvasContentService processCanvasContentService;

    @InjectMocks
    private CommandTypeServiceImpl commandTypeService;

    @Test
    void processInputCommand_withCanvasTypeAction_shouldReturnCanvas() {
        doNothing().when(processCanvasService).checkActionIsValid(CREATE_CANVAS);
        when(processCanvasService.generateCanvas(any(InputCommand.class))).thenReturn(Canvas.create(WIDTH, HEIGHT));

        InputCommand inputCommand = InputCommand.builder().actionCommand(CREATE_CANVAS).create();
        Canvas canvas = Canvas.empty();

        Canvas canvasResult = commandTypeService.processInputCommand(inputCommand, canvas);

        assertEquals(WIDTH, canvasResult.getWidth());
        assertEquals(HEIGHT, canvasResult.getHeight());
        assertNotNull(canvasResult.getContent());
    }

    @Test
    void processInputCommand_withCanvasContentAction_shouldReturnCanvasWithSameSizeContent() {
        char[][] canvasContent = fillArrayWithSpaces(WIDTH, HEIGHT);
        when(processCanvasContentService.processDrawContent(any(InputCommand.class), any()))
                .thenReturn(canvasContent);

        InputCommand inputCommand = InputCommand.builder().actionCommand(DRAW_BUCKET_FILL).create();
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);

        Canvas canvasResult = commandTypeService.processInputCommand(inputCommand, canvas);

        assertEquals(WIDTH, canvasResult.getWidth());
        assertEquals(HEIGHT, canvasResult.getHeight());
    }

    @Test
    void processInputCommand_withCanvasContentActionReturnContentDifferentSize_shouldThrowAnError() {
        char[][] canvasContent = fillArrayWithSpaces(WIDTH, HEIGHT + 1);
        when(processCanvasContentService.processDrawContent(any(InputCommand.class), any()))
                .thenReturn(canvasContent);

        InputCommand inputCommand = InputCommand.builder().actionCommand(DRAW_BUCKET_FILL).create();
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);

        try {
            commandTypeService.processInputCommand(inputCommand, canvas);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Cannot modify canvas content because the size are not the same", ex.getMessage());
        }
    }

    @Test
    void processInputCommand_withAppOperationType_shouldThrowAnError() {
        InputCommand inputCommand = InputCommand.builder().actionCommand(QUIT).create();
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);

        try {
            commandTypeService.processInputCommand(inputCommand, canvas);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Command type 'APPLICATION_OPERATION' is not implemented.", ex.getMessage());
        }
    }
}
