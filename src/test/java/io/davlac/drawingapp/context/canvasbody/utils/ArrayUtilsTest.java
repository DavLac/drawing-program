package io.davlac.drawingapp.context.canvasbody.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ArrayUtilsTest {

    private static final char[][] ARRAY_2x2 = {
            {'1', '2'},
            {'a', 'b'}
    };
    private static final int WIDTH = 3;
    private static final int HEIGHT = 2;
    public static final char BLANK = ' ';

    @Test
    void createArrayWithBlanks_withPositiveParams_shouldReturnArrayWithBlankContent() {
        char[][] array = ArrayUtils.createArrayWithBlanks(WIDTH, HEIGHT);

        IntStream.range(0, array.length).forEach(row -> {
            IntStream.range(0, array[0].length).forEach(col -> {
                assertEquals(BLANK, array[row][col]);
            });
        });
    }

    @Test
    void createArrayWithBlanks_withHeightAndWidthZero_shouldReturnEmptyArray() {
        char[][] array = ArrayUtils.createArrayWithBlanks(0, 0);
        assertEquals(0, array.length);
    }

    @Test
    void createArrayWithBlanks_withHeightZero_shouldReturnEmptyArray() {
        char[][] array = ArrayUtils.createArrayWithBlanks(WIDTH, 0);
        assertEquals(0, array.length);
    }

    @Test
    void createArrayWithBlanks_withWidthZero_shouldReturnArrayHeightSize() {
        char[][] array = ArrayUtils.createArrayWithBlanks(0, HEIGHT);
        assertEquals(HEIGHT, array.length);
        assertEquals(0, array[0].length);
        assertEquals(0, array[1].length);
    }

    @Test
    void deepCopyArray_withOneArray_shouldReturnACopyOfTheArray() {
        char[][] copy = ArrayUtils.deepCopyArray(ARRAY_2x2);
        assertArrayEquals(ARRAY_2x2, copy);
        copy[0][0] = 'X';
        assertNotEquals(ARRAY_2x2[0][0], copy[0][0]);
    }

    @Test
    void deepCopyArray_withEmptyArray_shouldReturnEmptyArray() {
        char[][] copy = ArrayUtils.deepCopyArray(new char[0][0]);
        assertArrayEquals(new char[0][0], copy);
    }

    @Test
    void deepCopyArray_withNullArray_shouldReturnEmptyArray() {
        char[][] copy = ArrayUtils.deepCopyArray(null);
        assertArrayEquals(new char[0][0], copy);
    }

    @Test
    void coordinatesAreOutsideTheGrid_withXoutside_shouldReturnTrue() {
        assertTrue(ArrayUtils.coordinatesAreOutsideTheArray(ARRAY_2x2, 3, 2));
    }

    @Test
    void coordinatesAreOutsideTheGrid_withYoutside_shouldReturnTrue() {
        assertTrue(ArrayUtils.coordinatesAreOutsideTheArray(ARRAY_2x2, 2, 3));
    }

    @Test
    void coordinatesAreOutsideTheGrid_withCoordInside_shouldReturnFalse() {
        assertFalse(ArrayUtils.coordinatesAreOutsideTheArray(ARRAY_2x2, 2, 2));
    }

    @Test
    void coordinatesAreOutsideTheGrid_withCoordZero_shouldReturnFalse() {
        assertFalse(ArrayUtils.coordinatesAreOutsideTheArray(ARRAY_2x2, 0, 0));
    }

    @Test
    void coordinatesAreOutsideTheGrid_withNullArray_shouldReturnTrue() {
        assertTrue(ArrayUtils.coordinatesAreOutsideTheArray(null, 1, 1));
    }

    @Test
    void coordinatesAreOutsideTheGrid_withEmptyArray_shouldReturnFalse() {
        assertTrue(ArrayUtils.coordinatesAreOutsideTheArray(new char[0][0], 2, 2));
    }
}
