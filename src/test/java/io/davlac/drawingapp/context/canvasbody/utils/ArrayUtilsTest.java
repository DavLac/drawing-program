package io.davlac.drawingapp.context.canvasbody.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ArrayUtilsTest {

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
}
