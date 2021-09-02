package io.davlac.drawingapp.context.canvascontent.service.canvas;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeTwoPointsRequest;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawRectangleCanvasContentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DrawRectangleCanvasContentServiceTest {

    private static final char[][] CANVAS_CONTENT = {
            {'1', '2', '3'},
            {'a', 'b', 'c'}
    };
    private static final char[][] CANVAS_CONTENT_CLEAR = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '}
    };
    private static final char[][] CANVAS_CONTENT_VERTICAL_LINE = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '}
    };
    private static final char[][] CANVAS_CONTENT_HORIZ_LINE = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'x', 'x', 'x', 'x', 'x'},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '}
    };
    private static final char[][] CANVAS_CONTENT_POINT = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '}
    };
    private static final char[][] CANVAS_CONTENT_RECTANGLE = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'x', 'x', 'x', ' ', ' '},
            {' ', 'x', ' ', 'x', ' ', ' '},
            {' ', 'x', 'x', 'x', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '}
    };
    private static final char[][] CANVAS_CONTENT_RECTANGLE_CROSS = {
            {' ', ' ', 'x', 'x', 'x', 'x'},
            {' ', 'x', 'x', 'x', ' ', 'x'},
            {' ', 'x', 'x', 'x', ' ', 'x'},
            {' ', 'x', 'x', 'x', ' ', 'x'},
            {' ', ' ', 'x', ' ', ' ', 'x'},
            {' ', ' ', 'x', 'x', 'x', 'x'}
    };
    private static final String ARG1 = "1";
    private static final String ARG2 = "2";
    private static final String ARG3 = "3";
    private static final String ARG4 = "4";
    private static final String ARG_ALPHA = "a";

    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private DrawRectangleCanvasContentService drawRectangleCanvasContentService;

    @Test
    void validateArguments_withEnoughArgs_shouldReturnTrue() {
        List<String> args = List.of(ARG1, ARG2, ARG3, ARG4);
        assertTrue(drawRectangleCanvasContentService.validateArguments(args));
    }

    @Test
    void validateArguments_withNotEnoughArgs_shouldThrowError() {
        List<String> args = List.of(ARG1, ARG2, ARG3);
        try {
            drawRectangleCanvasContentService.validateArguments(args);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_RECTANGLE' - not enough arguments (4)", ex.getMessage());
        }
    }

    @Test
    void toDrawShapeRequest_withGoodArgs_shouldReturnRequest() {
        List<String> args = List.of(ARG1, ARG2, ARG3, ARG4);
        DrawShapeTwoPointsRequest drawShapeRequest = (DrawShapeTwoPointsRequest)
                drawRectangleCanvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT);
        assertEquals(Integer.parseInt(ARG1), drawShapeRequest.getFirstPoint().getX());
        assertEquals(Integer.parseInt(ARG2), drawShapeRequest.getFirstPoint().getY());
        assertEquals(Integer.parseInt(ARG3), drawShapeRequest.getLastPoint().getX());
        assertEquals(Integer.parseInt(ARG4), drawShapeRequest.getLastPoint().getY());
        assertArrayEquals(CANVAS_CONTENT, drawShapeRequest.getCanvasContent());
    }

    @Test
    void toDrawShapeRequest_withAlphaArg_shouldThrowError() {
        List<String> args = List.of(ARG1, ARG2, ARG3, ARG_ALPHA);
        try {
            drawRectangleCanvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT);
        } catch (IllegalArgumentException ex) {
            assertEquals(String.format("For input string: \"%s\"", ARG_ALPHA), ex.getMessage());
        }
    }

    @Test
    void validateDrawShapeRequest_withCoordTopLeftAndBottomRight_shouldReturnTrue() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(1, 1),
                new Coordinates(2, 2),
                CANVAS_CONTENT
        );
        assertTrue(drawRectangleCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest));
    }

    @Test
    void validateDrawShapeRequest_withCoordNotTopLeftAndBottomRight_shouldThrowError() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(2, 2),
                new Coordinates(1, 1),
                CANVAS_CONTENT
        );
        try {
            drawRectangleCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_RECTANGLE' - First coordinate 'Coordinates{x=2, y=2}' " +
                    "should be in the top left corner and the second should be in the bottom right corner " +
                    "'Coordinates{x=1, y=1}'", ex.getMessage());
        }
    }

    @Test
    void validateDrawShapeRequest_withOutsideCoordX_shouldThrowError() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(1, 10),
                new Coordinates(2, 1),
                CANVAS_CONTENT
        );
        try {
            drawRectangleCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_RECTANGLE' - coordinates are outside the canvas : 'Coordinates{x=1, y=10}'.", ex.getMessage());
        }
    }

    @Test
    void validateDrawShapeRequest_withOutsideCoordY_shouldThrowError() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(1, 1),
                new Coordinates(2, 10),
                CANVAS_CONTENT
        );
        try {
            drawRectangleCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_RECTANGLE' - coordinates are outside the canvas : 'Coordinates{x=2, y=10}'.", ex.getMessage());
        }
    }

    @Test
    void drawShape_withVerticalCoord_shouldDrawVerticalLine() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(3, 2),
                new Coordinates(3, 6),
                CANVAS_CONTENT_CLEAR
        );

        char[][] contentResult = drawRectangleCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_VERTICAL_LINE, contentResult);
    }

    @Test
    void drawShape_withHorizontalCoord_shouldDrawHorizontalLine() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(2, 3),
                new Coordinates(6, 3),
                CANVAS_CONTENT_CLEAR
        );

        char[][] contentResult = drawRectangleCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_HORIZ_LINE, contentResult);
    }

    @Test
    void drawShape_withSameCoord_shouldDrawPoint() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(3, 3),
                new Coordinates(3, 3),
                CANVAS_CONTENT_CLEAR
        );

        char[][] contentResult = drawRectangleCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_POINT, contentResult);
    }

    @Test
    void drawShape_withRectangleCoord_shouldDrawRectangle() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(3, 1),
                new Coordinates(6, 6),
                CANVAS_CONTENT_RECTANGLE
        );

        char[][] contentResult = drawRectangleCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_RECTANGLE_CROSS, contentResult);
    }
}
