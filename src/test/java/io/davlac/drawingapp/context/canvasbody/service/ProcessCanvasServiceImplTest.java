package io.davlac.drawingapp.context.canvasbody.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvasbody.service.impl.ProcessCanvasServiceImpl;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.QUIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessCanvasServiceImplTest {

    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;

    @Mock
    private CanvasService canvasService;

    @InjectMocks
    private ProcessCanvasServiceImpl processCanvasService;

    @Test
    void checkActionIsValid_withCreateAction_shouldReturnTrue() {
        assertTrue(processCanvasService.checkActionIsValid(CREATE_CANVAS));
    }

    @Test
    void checkActionIsValid_withNoteCreateAction_shouldThrowError() {
        try {
            processCanvasService.checkActionIsValid(QUIT);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Action command 'QUIT' is not implemented for type = 'CANVAS_BODY'", ex.getMessage());
        }
    }

    @Test
    void generateCanvas_withArguments_shouldCreateCanvas() {
        List<String> args = List.of(String.valueOf(WIDTH), String.valueOf(HEIGHT));
        CreateCanvasRequest createCanvasRequest = new CreateCanvasRequest(WIDTH, HEIGHT);
        InputCommand inputCommand = InputCommand.builder().actionCommand(CREATE_CANVAS).arguments(args).create();

        when(canvasService.validArguments(args)).thenReturn(true);
        when(canvasService.toCreateCanvasRequest(args)).thenReturn(createCanvasRequest);
        when(canvasService.validateCreateCanvasRequest(createCanvasRequest)).thenReturn(true);
        when(canvasService.createCanvas(createCanvasRequest)).thenReturn(Canvas.create(WIDTH, HEIGHT));

        Canvas canvas = processCanvasService.generateCanvas(inputCommand);
        assertEquals(WIDTH, canvas.getWidth());
        assertEquals(HEIGHT, canvas.getHeight());
        assertNotNull(canvas.getContent());
    }
}
