package io.davlac.drawingapp.context.canvascontent.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class DrawLineRequest {
    @NotNull
    private final @Valid Coordinates firstPoint;
    @NotNull
    private final @Valid Coordinates lastPoint;
    @NotNull
    private final CanvasContent canvasContent;

    public DrawLineRequest(DrawLineRequest.Builder builder) {
        this.firstPoint = builder.firstPoint;
        this.lastPoint = builder.lastPoint;
        this.canvasContent = builder.canvasContent;
    }

    public static DrawLineRequest.Builder builder() {
        return new DrawLineRequest.Builder();
    }

    public static class Builder {

        private Coordinates firstPoint;
        private Coordinates lastPoint;
        private CanvasContent canvasContent;

        public DrawLineRequest.Builder firstPoint(Integer x, Integer y) {
            this.firstPoint = new Coordinates(x, y);
            return this;
        }

        public DrawLineRequest.Builder lastPoint(Integer x, Integer y) {
            this.lastPoint = new Coordinates(x, y);
            return this;
        }

        public DrawLineRequest.Builder canvasContent(CanvasContent canvasContent) {
            this.canvasContent = canvasContent;
            return this;
        }

        public DrawLineRequest create() {
            return new DrawLineRequest(this);
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
