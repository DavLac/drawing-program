package io.davlac.drawingapp.context.canvasbody.utils;

import java.util.Arrays;
import java.util.stream.IntStream;

import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.BLANK_CHAR;

public interface ArrayUtils {

    static char[][] createArrayWithBlanks(int width, int height) {
        char[][] array = new char[height][width];
        IntStream.range(0, height)
                .forEach(indexY -> IntStream.range(0, width)
                        .forEach(indexX -> array[indexY][indexX] = BLANK_CHAR.getChar()));
        return array;
    }

    static char[][] deepCopyArray(char[][] array) {
        if (array == null || array.length == 0) {
            return new char[0][0];
        }

        char[][] newArray = new char[array.length][array[0].length];
        IntStream.range(0, array.length)
                .forEach(i -> newArray[i] = Arrays.copyOf(array[i], array[i].length));

        return newArray;
    }

    static boolean coordinatesAreOutsideTheArray(char[][] array, int x, int y) {
        return array == null || y > array.length || x > array[0].length;
    }
}
