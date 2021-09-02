package io.davlac.drawingapp.context.canvascontent.service.canvas;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawBucketFillRequest;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeTwoPointsRequest;
import io.davlac.drawingapp.context.canvascontent.service.FloodFillService;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawBucketFillCanvasContentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrawBucketFillCanvasContentServiceTest {

    private static final char[][] CANVAS_CONTENT = {
            {'1', '2', '3'},
            {'a', 'b', 'c'}
    };

    private static final char[][] GRID_WITH_RECTANGLE = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'x', 'x', 'x', 'x', ' '},
            {' ', 'x', ' ', ' ', 'x', ' '},
            {' ', 'x', ' ', ' ', 'x', ' '},
            {' ', 'x', 'x', 'x', 'x', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '}
    };

    private static final char[][] GRID_WITH_RECTANGLE_FILLED_OUTSIDE = {
            {'o', 'o', 'o', 'o', 'o', 'o'},
            {'o', 'x', 'x', 'x', 'x', 'o'},
            {'o', 'x', ' ', ' ', 'x', 'o'},
            {'o', 'x', ' ', ' ', 'x', 'o'},
            {'o', 'x', 'x', 'x', 'x', 'o'},
            {'o', 'o', 'o', 'o', 'o', 'o'}
    };

    private static final String ARG1 = "1";
    private static final String ARG2 = "2";
    private static final String ARG3 = "3";
    private static final String BLANK_KEY_WORD = "blank";
    private static final String ARG_ALPHA = "a";
    private static final char BLANK = ' ';
    private static final char COLOR = 'o';

    @Mock
    private ValidatorService validatorService;

    @Mock
    private FloodFillService floodFillService;

    @InjectMocks
    private DrawBucketFillCanvasContentService drawBucketFillCanvasContentService;

    @Test
    void validateArguments_withEnoughArgs_shouldReturnTrue() {
        List<String> args = List.of(ARG1, ARG2, ARG3);
        assertTrue(drawBucketFillCanvasContentService.validateArguments(args));
    }

    @Test
    void validateArguments_withNotEnoughArgs_shouldThrowError() {
        List<String> args = List.of(ARG1, ARG2);
        try {
            drawBucketFillCanvasContentService.validateArguments(args);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_BUCKET_FILL' - not enough arguments (3)", ex.getMessage());
        }
    }

    @Test
    void toDrawShapeRequest_withGoodArgs_shouldReturnRequest() {
        List<String> args = List.of(ARG1, ARG2, ARG3);
        DrawBucketFillRequest drawShapeRequest = (DrawBucketFillRequest)
                drawBucketFillCanvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT);
        assertEquals(Integer.parseInt(ARG1), drawShapeRequest.getFirstPoint().getX());
        assertEquals(Integer.parseInt(ARG2), drawShapeRequest.getFirstPoint().getY());
        assertEquals(ARG3.charAt(0), drawShapeRequest.getColor());
        assertArrayEquals(CANVAS_CONTENT, drawShapeRequest.getCanvasContent());
    }

    @Test
    void toDrawShapeRequest_withBlankKeyWorkArg_shouldReturnRequestWithColorSpace() {
        List<String> args = List.of(ARG1, ARG2, BLANK_KEY_WORD);
        DrawBucketFillRequest drawShapeRequest = (DrawBucketFillRequest)
                drawBucketFillCanvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT);
        assertEquals(BLANK, drawShapeRequest.getColor());
    }

    @Test
    void toDrawShapeRequest_withWordArgColor_shouldReturnFirstWordLetterColor() {
        List<String> args = List.of(ARG1, ARG2, "hello");
        DrawBucketFillRequest drawShapeRequest = (DrawBucketFillRequest)
                drawBucketFillCanvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT);
        assertEquals('h', drawShapeRequest.getColor());
    }

    @Test
    void toDrawShapeRequest_withAlphaArg_shouldThrowError() {
        List<String> args = List.of(ARG_ALPHA, ARG2, ARG3);
        try {
            drawBucketFillCanvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT);
        } catch (IllegalArgumentException ex) {
            assertEquals(String.format("For input string: \"%s\"", ARG_ALPHA), ex.getMessage());
        }
    }

    @Test
    void validateDrawShapeRequest_withGoodRequest_shouldReturnTrue() {
        DrawBucketFillRequest drawBucketFillRequest = new DrawBucketFillRequest(
                new Coordinates(1, 1),
                BLANK,
                CANVAS_CONTENT
        );
        assertTrue(drawBucketFillCanvasContentService.validateDrawShapeRequest(drawBucketFillRequest));
    }

    @Test
    void validateDrawShapeRequest_withOutsideCoord_shouldThrowError() {
        DrawBucketFillRequest drawBucketFillRequest = new DrawBucketFillRequest(
                new Coordinates(1, 10),
                BLANK,
                CANVAS_CONTENT
        );
        try {
            drawBucketFillCanvasContentService.validateDrawShapeRequest(drawBucketFillRequest);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_BUCKET_FILL' - coordinates are outside the canvas : 'Coordinates{x=1, y=10}'.", ex.getMessage());
        }
    }

    @Test
    void drawShape_withClearGrid_shouldFillColor() {
        Coordinates coordinates = new Coordinates(2, 2);
        DrawBucketFillRequest drawBucketFillRequest = new DrawBucketFillRequest(
                coordinates,
                COLOR,
                GRID_WITH_RECTANGLE
        );

        when(floodFillService.floodFill(GRID_WITH_RECTANGLE, coordinates, COLOR)).thenReturn(GRID_WITH_RECTANGLE_FILLED_OUTSIDE);

        char[][] contentResult = drawBucketFillCanvasContentService.drawShape(drawBucketFillRequest);
        assertArrayEquals(GRID_WITH_RECTANGLE_FILLED_OUTSIDE, contentResult);
    }
}
