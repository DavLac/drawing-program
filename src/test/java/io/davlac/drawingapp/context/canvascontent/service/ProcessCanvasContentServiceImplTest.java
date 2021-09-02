package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.factory.CanvasContentFactory;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeTwoPointsRequest;
import io.davlac.drawingapp.context.canvascontent.service.impl.process.ProcessCanvasContentServiceImpl;
import io.davlac.drawingapp.context.canvascontent.service.impl.validator.JavaxValidatorService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_LINE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessCanvasContentServiceImplTest {

    private static final char[][] CANVAS_CONTENT = {
            {'1', '2', '3'},
            {'a', 'b', 'c'}
    };
    private static final char[][] CANVAS_CONTENT_2 = {
            {'4', '5', '6'},
            {'d', 'e', 'f'}
    };
    private static final String ARG1 = "1";
    private static final String ARG2 = "2";
    private static final String ARG3 = "3";
    private static final String ARG4 = "4";

    @Mock
    private CanvasContentFactory canvasContentFactory;

    @Mock
    private JavaxValidatorService javaxValidatorService;

    @Mock
    private CanvasContentService canvasContentService;

    @InjectMocks
    private ProcessCanvasContentServiceImpl processCanvasContentService;

    @Test
    void processDrawContent_withDrawLine_shouldDrawShape() {
        List<String> args = List.of(ARG1, ARG2, ARG3, ARG4);
        InputCommand inputCommand = InputCommand.builder()
                .actionCommand(DRAW_LINE)
                .arguments(args)
                .create();
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(Integer.valueOf(ARG1), Integer.valueOf(ARG2)),
                new Coordinates(Integer.valueOf(ARG3), Integer.valueOf(ARG4)),
                CANVAS_CONTENT
        );

        when(canvasContentFactory.getAction(DRAW_LINE)).thenReturn(canvasContentService);
        when(canvasContentService.validateArguments(args)).thenReturn(true);
        when(canvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT)).thenReturn(drawShapeTwoPointsRequest);
        when(canvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest)).thenReturn(true);
        when(canvasContentService.drawShape(drawShapeTwoPointsRequest)).thenReturn(CANVAS_CONTENT_2);

        char[][] canvasContent = processCanvasContentService.processDrawContent(inputCommand, CANVAS_CONTENT);

        assertArrayEquals(CANVAS_CONTENT_2, canvasContent);
    }

    @Test
    void processDrawContent_withCanvasEmpty_shouldThrowError() {
        List<String> args = List.of(ARG1, ARG2, ARG3, ARG4);
        InputCommand inputCommand = InputCommand.builder()
                .actionCommand(DRAW_LINE)
                .arguments(args)
                .create();

        try {
            processCanvasContentService.processDrawContent(inputCommand, new char[0][0]);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Canvas content is empty.", ex.getMessage());
        }
    }

    @Test
    void processDrawContent_withCanvasNull_shouldThrowError() {
        List<String> args = List.of(ARG1, ARG2, ARG3, ARG4);
        InputCommand inputCommand = InputCommand.builder()
                .actionCommand(DRAW_LINE)
                .arguments(args)
                .create();

        try {
            processCanvasContentService.processDrawContent(inputCommand, null);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Canvas content is empty.", ex.getMessage());
        }
    }

    @Test
    void processDrawContent_withInputCommandNull_shouldThrowError() {
        try {
            processCanvasContentService.processDrawContent(null, CANVAS_CONTENT);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : InputCommand is null.", ex.getMessage());
        }
    }
}
