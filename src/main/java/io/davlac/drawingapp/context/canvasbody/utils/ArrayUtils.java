package io.davlac.drawingapp.context.canvasbody.utils;

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
}
