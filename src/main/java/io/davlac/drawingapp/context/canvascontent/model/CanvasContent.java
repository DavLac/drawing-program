package io.davlac.drawingapp.context.canvascontent.model;

import java.util.stream.IntStream;

import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.EMPTY_CHAR;

public class CanvasContent {

    private char[][] content;

    public static CanvasContent initWithEmptySpaces(Integer width, Integer height) {
        char[][] content = new char[height][width];
        IntStream.range(0, height)
                .forEach(indexY -> IntStream.range(0, width)
                        .forEach(indexX -> content[indexY][indexX] = EMPTY_CHAR.getChar()));
        return new CanvasContent(content);
    }

    public CanvasContent(char[][] content) {
        this.content = content;
    }

    public char[][] getContent() {
        return content;
    }

    public void setContent(char[][] content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return this.content == null || this.content.length == 0;
    }
}
