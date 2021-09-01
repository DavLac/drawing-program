package io.davlac.drawingapp.context.canvascontent.model.request;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class DrawShapeRequest {
    @NotNull
    protected final @Valid Coordinates firstPoint;
    @NotNull
    protected final char[][] canvasContent;

    protected DrawShapeRequest(Coordinates firstPoint, char[][] canvasContent) {
        this.firstPoint = firstPoint;
        this.canvasContent = canvasContent;
    }

    public Coordinates getFirstPoint() {
        return firstPoint;
    }

    public char[][] getCanvasContent() {
        return canvasContent;
    }
}
