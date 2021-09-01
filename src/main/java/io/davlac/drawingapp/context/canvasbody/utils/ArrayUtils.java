package io.davlac.drawingapp.context.canvasbody.utils;

import java.util.stream.IntStream;

import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.BLANK_CHAR;

public class ArrayUtils {

    private ArrayUtils() {
    }

    public static char[][] fillArrayWithSpaces(Integer width, Integer height) {
        char[][] array = new char[height][width];
        IntStream.range(0, height)
                .forEach(indexY -> IntStream.range(0, width)
                        .forEach(indexX -> array[indexY][indexX] = BLANK_CHAR.getChar()));
        return array;
    }
}
