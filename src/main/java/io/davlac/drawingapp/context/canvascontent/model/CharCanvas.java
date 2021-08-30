package io.davlac.drawingapp.context.canvascontent.model;

public enum CharCanvas {
    EMPTY_CHAR(' '),
    TOP_BOTTOM_BORDER_CHAR('-'),
    LEFT_RIGHT_BORDER_CHAR('|'),
    LINE_CHAR('x');

    private final char character;

    CharCanvas(char character) {
        this.character = character;
    }

    public char getChar() {
        return character;
    }
}
