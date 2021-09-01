package io.davlac.drawingapp.context.canvasbody.model;

import java.util.stream.IntStream;

import static io.davlac.drawingapp.context.canvasbody.utils.ArrayUtils.fillArrayWithSpaces;

public class Canvas {
    // canvas content is final to avoid to change the size of it
    // we can only create new canvas, not modify it
    private final char[][] content;

    private Canvas(char[][] content) {
        this.content = content;
    }

    public static Canvas create(int width, int height) {
        return new Canvas(fillArrayWithSpaces(width, height));
    }

    public static Canvas empty() {
        return new Canvas(new char[0][0]);
    }

    public int getWidth() {
        if (content == null) {
            return 0;
        }

        return content[0].length;
    }

    public int getHeight() {
        if (content == null) {
            return 0;
        }

        return content.length;
    }

    public char[][] getContent() {
        return content;
    }

    public void setContent(char[][] content) {
        if (this.getWidth() != content[0].length ||
                this.getHeight() != content.length) {
            throw new IllegalArgumentException("ERROR : Cannot modify canvas content because the size are not the same");
        }

        IntStream.range(0, this.getHeight()).forEach(i -> this.content[i] = content[i]);
    }
}
