package io.davlac.drawingapp.context.canvascontent.model;

import javax.validation.constraints.Positive;

public final class Coordinates {
    @Positive
    private final Integer x;
    @Positive
    private final Integer y;

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
