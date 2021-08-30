package io.davlac.drawingapp.context.canvascontent.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class DrawRectangleRequest implements DrawShapeRequest {
    @NotNull
    private final @Valid Coordinates firstPoint;
    @NotNull
    private final @Valid Coordinates lastPoint;
    @NotNull
    private final CanvasContent canvasContent;

    public DrawRectangleRequest(DrawRectangleRequest.Builder builder) {
        this.firstPoint = builder.firstPoint;
        this.lastPoint = builder.lastPoint;
        this.canvasContent = builder.canvasContent;
    }

    public static DrawRectangleRequest.Builder builder() {
        return new DrawRectangleRequest.Builder();
    }

    public static class Builder {

        private Coordinates firstPoint;
        private Coordinates lastPoint;
        private CanvasContent canvasContent;

        public DrawRectangleRequest.Builder firstPoint(Integer x, Integer y) {
            this.firstPoint = new Coordinates(x, y);
            return this;
        }

        public DrawRectangleRequest.Builder lastPoint(Integer x, Integer y) {
            this.lastPoint = new Coordinates(x, y);
            return this;
        }

        public DrawRectangleRequest.Builder canvasContent(CanvasContent canvasContent) {
            this.canvasContent = canvasContent;
            return this;
        }

        public DrawRectangleRequest create() {
            return new DrawRectangleRequest(this);
        }
    }

    public Coordinates getFirstPoint() {
        return firstPoint;
    }

    public Coordinates getLastPoint() {
        return lastPoint;
    }

    public CanvasContent getCanvasContent() {
        return canvasContent;
    }
}
