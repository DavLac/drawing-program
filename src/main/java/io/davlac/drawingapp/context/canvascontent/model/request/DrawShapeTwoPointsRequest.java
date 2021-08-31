package io.davlac.drawingapp.context.canvascontent.model.request;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class DrawShapeTwoPointsRequest extends DrawShapeRequest {
    @NotNull
    protected final @Valid Coordinates lastPoint;

    protected DrawShapeTwoPointsRequest(Coordinates firstPoint, Coordinates lastPoint, CanvasContent canvasContent) {
        super(firstPoint, canvasContent);
        this.lastPoint = lastPoint;
    }

    public Coordinates getLastPoint() {
        return lastPoint;
    }
}
