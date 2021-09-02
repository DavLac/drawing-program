package io.davlac.drawingapp.context.canvascontent.service.canvas;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeTwoPointsRequest;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawLineCanvasContentService;
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
class DrawLineCanvasContentServiceTest {

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
    private static final char[][] CANVAS_CONTENT_CROSS = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '},
            {' ', 'x', 'x', 'x', 'x', 'x'},
            {' ', ' ', 'x', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '},
            {' ', ' ', 'x', ' ', ' ', ' '}
    };
    private static final String ARG1 = "1";
    private static final String ARG2 = "2";
    private static final String ARG3 = "3";
    private static final String ARG4 = "4";
    private static final String ARG_ALPHA = "a";

    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private DrawLineCanvasContentService drawLineCanvasContentService;

    @Test
    void validateArguments_withEnoughArgs_shouldReturnTrue() {
        List<String> args = List.of(ARG1, ARG2, ARG3, ARG4);
        assertTrue(drawLineCanvasContentService.validateArguments(args));
    }

    @Test
    void validateArguments_withNotEnoughArgs_shouldThrowError() {
        List<String> args = List.of(ARG1, ARG2, ARG3);
        try {
            drawLineCanvasContentService.validateArguments(args);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_LINE' - not enough arguments (4)", ex.getMessage());
        }
    }

    @Test
    void toDrawShapeRequest_withGoodArgs_shouldReturnRequest() {
        List<String> args = List.of(ARG1, ARG2, ARG3, ARG4);
        DrawShapeTwoPointsRequest drawShapeRequest = (DrawShapeTwoPointsRequest)
                drawLineCanvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT);
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
            drawLineCanvasContentService.toDrawShapeRequest(args, CANVAS_CONTENT);
        } catch (IllegalArgumentException ex) {
            assertEquals(String.format("For input string: \"%s\"", ARG_ALPHA), ex.getMessage());
        }
    }

    @Test
    void validateDrawShapeRequest_withCoordHorizontal_shouldReturnTrue() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(1, 2),
                new Coordinates(1, 1),
                CANVAS_CONTENT
        );
        assertTrue(drawLineCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest));
    }

    @Test
    void validateDrawShapeRequest_withCoordVertical_shouldReturnTrue() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(1, 1),
                new Coordinates(2, 1),
                CANVAS_CONTENT
        );
        assertTrue(drawLineCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest));
    }

    @Test
    void validateDrawShapeRequest_withNotAlignedCoord_shouldThrowError() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(1, 2),
                new Coordinates(2, 1),
                CANVAS_CONTENT
        );
        try {
            drawLineCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_LINE' - 'Coordinates{x=1, y=2}' and 'Coordinates{x=2, y=1}' are not aligned.", ex.getMessage());
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
            drawLineCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_LINE' - coordinates are outside the canvas : 'Coordinates{x=1, y=10}'.", ex.getMessage());
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
            drawLineCanvasContentService.validateDrawShapeRequest(drawShapeTwoPointsRequest);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'DRAW_LINE' - coordinates are outside the canvas : 'Coordinates{x=2, y=10}'.", ex.getMessage());
        }
    }

    @Test
    void drawShape_withVerticalCoord_shouldDrawVerticalLine() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(3, 2),
                new Coordinates(3, 6),
                CANVAS_CONTENT_CLEAR
        );

        char[][] contentResult = drawLineCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_VERTICAL_LINE, contentResult);
    }

    @Test
    void drawShape_withVerticalCoordReverse_shouldDrawVerticalLine() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(3, 6),
                new Coordinates(3, 2),
                CANVAS_CONTENT_CLEAR
        );

        char[][] contentResult = drawLineCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_VERTICAL_LINE, contentResult);
    }

    @Test
    void drawShape_withHorizontalCoord_shouldDrawHorizontalLine() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(2, 3),
                new Coordinates(6, 3),
                CANVAS_CONTENT_CLEAR
        );

        char[][] contentResult = drawLineCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_HORIZ_LINE, contentResult);
    }

    @Test
    void drawShape_withHorizontalCoordReverse_shouldDrawHorizontalLine() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(6, 3),
                new Coordinates(2, 3),
                CANVAS_CONTENT_CLEAR
        );

        char[][] contentResult = drawLineCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_HORIZ_LINE, contentResult);
    }

    @Test
    void drawShape_withSameCoord_shouldDrawPoint() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(3, 3),
                new Coordinates(3, 3),
                CANVAS_CONTENT_CLEAR
        );

        char[][] contentResult = drawLineCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_POINT, contentResult);
    }

    @Test
    void drawShape_withHorizAndVerticalLine_shouldCrossLine() {
        DrawShapeTwoPointsRequest drawShapeTwoPointsRequest = new DrawShapeTwoPointsRequest(
                new Coordinates(3, 6),
                new Coordinates(3, 2),
                CANVAS_CONTENT_HORIZ_LINE
        );

        char[][] contentResult = drawLineCanvasContentService.drawShape(drawShapeTwoPointsRequest);

        assertArrayEquals(CANVAS_CONTENT_CROSS, contentResult);
    }
}
