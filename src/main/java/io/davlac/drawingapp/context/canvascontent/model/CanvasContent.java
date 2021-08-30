package io.davlac.drawingapp.context.canvascontent.model;

import java.util.stream.IntStream;

public class CanvasContent {
    private String[][] content;

    public static CanvasContent initWithEmptySpaces(Integer width, Integer height) {
        String[][] content = new String[height][width];
        IntStream.range(0, height)
                .forEach(indexY -> IntStream.range(0, width)
                        .forEach(indexX -> content[indexY][indexX] = " "));
        return new CanvasContent(content);
    }

    public CanvasContent(String[][] content) {
        this.content = content;
    }

    public String[][] getContent() {
        return content;
    }

    public void setContent(String[][] content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return this.content == null || this.content.length == 0;
    }
}
