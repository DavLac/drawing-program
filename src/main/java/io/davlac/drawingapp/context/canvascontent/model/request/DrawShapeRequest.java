package io.davlac.drawingapp.context.canvascontent.model.request;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class DrawShapeRequest {
    @NotNull
    protected final @Valid Coordinates firstPoint;
    @NotNull
    protected final CanvasContent canvasContent;

    protected DrawShapeRequest(Coordinates firstPoint, CanvasContent canvasContent) {
        this.firstPoint = firstPoint;
        this.canvasContent = canvasContent;
    }

    public Coordinates getFirstPoint() {
        return firstPoint;
    }

    public CanvasContent getCanvasContent() {
        return canvasContent;
    }
}
