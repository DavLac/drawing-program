package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.service.impl.floodfill.BreadthFirstSearchFloodFillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class BreadthFirstSearchFloodFillServiceTest {

    private static final char[][] GRID_SMALL = {
            {' ', ' '},
            {' ', ' '}
    };

    private static final char[][] GRID_FULL = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '}
    };

    private static final char[][] GRID_WITH_RECTANGLE = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'x', 'x', 'x', 'x', ' '},
            {' ', 'x', ' ', ' ', 'x', ' '},
            {' ', 'x', ' ', ' ', 'x', ' '},
            {' ', 'x', 'x', 'x', 'x', ' '},
            {' ', ' ', ' ', ' ', ' ', ' '}
    };

    private static final char[][] GRID_WITH_RECTANGLE_FILLED = {
            {' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'o', 'o', 'o', 'o', ' '},
            {' ', 'o', ' ', ' ', 'o', ' '},
            {' ', 'o', ' ', ' ', 'o', ' '},
            {' ', 'o', 'o', 'o', 'o', ' '},
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

    private static final char[][] GRID_WITH_X_LINE = {
            {'x', ' ', ' ', ' ', 'x', 'x'},
            {'x', 'x', ' ', 'x', 'x', ' '},
            {' ', 'x', 'x', 'x', ' ', ' '},
            {' ', ' ', 'x', 'x', ' ', ' '},
            {' ', 'x', 'x', 'x', 'o', ' '},
            {'x', 'x', ' ', ' ', 'o', 'o'}
    };

    private static final char[][] GRID_WITH_X_LINE_FILLED = {
            {'o', ' ', ' ', ' ', 'o', 'o'},
            {'o', 'o', ' ', 'o', 'o', ' '},
            {' ', 'o', 'o', 'o', ' ', ' '},
            {' ', ' ', 'o', 'o', ' ', ' '},
            {' ', 'o', 'o', 'o', 'o', ' '},
            {'o', 'o', ' ', ' ', 'o', 'o'}
    };

    private static final int WIDTH = 1;
    private static final int HEIGHT = 2;

    private final FloodFillService floodFillService = new BreadthFirstSearchFloodFillService();

    @Test
    void floodFill_withSameColorGrid_shouldFillAllGrid() {
        char newColor = 'x';
        Coordinates coordinates = new Coordinates(WIDTH, HEIGHT);
        char[][] filledGrid = floodFillService.floodFill(GRID_FULL, coordinates, newColor);

        IntStream.range(0, GRID_FULL.length)
                .forEach(y -> IntStream.range(0, GRID_FULL[y].length)
                        .forEach(x -> assertEquals(newColor, filledGrid[y][x])));
    }

    @Test
    void floodFill_withNewColorSamePrevColor_shouldDoNothing() {
        char newColor = ' ';
        Coordinates coordinates = new Coordinates(WIDTH, HEIGHT);
        char[][] filledGrid = floodFillService.floodFill(GRID_FULL, coordinates, newColor);

        IntStream.range(0, GRID_FULL.length)
                .forEach(y -> IntStream.range(0, GRID_FULL[y].length)
                        .forEach(x -> assertEquals(newColor, filledGrid[y][x])));
    }

    @Test
    void floodFill_withRectangleGrids_shouldFillOnlyRectangle() {
        char newColor = 'o';
        Coordinates coordinates = new Coordinates(2, 2);
        char[][] filledGrid = floodFillService.floodFill(GRID_WITH_RECTANGLE, coordinates, newColor);

        assertArrayEquals(GRID_WITH_RECTANGLE_FILLED, filledGrid);
    }

    @Test
    void floodFill_withRectangleGridsFillOutside_shouldFillOnlyOutsideRectangle() {
        char newColor = 'o';
        Coordinates coordinates = new Coordinates(1, 1);
        char[][] filledGrid = floodFillService.floodFill(GRID_WITH_RECTANGLE, coordinates, newColor);

        assertArrayEquals(GRID_WITH_RECTANGLE_FILLED_OUTSIDE, filledGrid);
    }

    @Test
    void floodFill_withXShapeWithNewColor_shouldFillXShape() {
        char newColor = 'o';
        Coordinates coordinates = new Coordinates(1, 1);
        char[][] filledGrid = floodFillService.floodFill(GRID_WITH_X_LINE, coordinates, newColor);

        assertArrayEquals(GRID_WITH_X_LINE_FILLED, filledGrid);
    }

    @Test
    void floodFill_withEmptyGrid_shouldReturnEmptyGrid() {
        char newColor = 'o';
        Coordinates coordinates = new Coordinates(1, 1);
        char[][] filledGrid = floodFillService.floodFill(new char[0][0], coordinates, newColor);

        assertArrayEquals(new char[0][0], filledGrid);
    }

    @Test
    void floodFill_withNullGrid_shouldReturnNullGrid() {
        char newColor = 'o';
        Coordinates coordinates = new Coordinates(1, 1);
        char[][] filledGrid = floodFillService.floodFill(null, coordinates, newColor);

        assertNull(filledGrid);
    }

    @Test
    void floodFill_withNullCoordinates_shouldThrowError() {
        char newColor = 'o';

        try {
            floodFillService.floodFill(GRID_SMALL, null, newColor);
        } catch (InternalError ex) {
            assertEquals("ERROR : Coordinates is null", ex.getMessage());
        }
    }

    @Test
    void floodFill_withCoordinatesZero_shouldFillXShape() {
        char newColor = 'o';
        Coordinates coordinates = new Coordinates(0, 0);
        try {
            floodFillService.floodFill(GRID_SMALL, coordinates, newColor);
        } catch (InternalError ex) {
            assertEquals("ERROR : One of coordinates is 0", ex.getMessage());
        }
    }

    @Test
    void floodFill_withCoordinatesOutsideGrid_shouldFillXShape() {
        char newColor = 'o';
        Coordinates coordinates = new Coordinates(10, 10);
        try {
            floodFillService.floodFill(GRID_SMALL, coordinates, newColor);
        } catch (InternalError ex) {
            assertEquals("ERROR: coordinates are outside the grid : 'Coordinates{x=10, y=10}'.", ex.getMessage());
        }
    }
}
