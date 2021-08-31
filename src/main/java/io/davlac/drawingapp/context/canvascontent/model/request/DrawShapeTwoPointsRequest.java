package io.davlac.drawingapp.context.canvascontent.model.request;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class DrawShapeTwoPointsRequest extends DrawShapeRequest {
    @NotNull
    private final @Valid Coordinates lastPoint;

    public DrawShapeTwoPointsRequest(Coordinates firstPoint, Coordinates lastPoint, CanvasContent canvasContent) {
        super(firstPoint, canvasContent);
        this.lastPoint = lastPoint;
    }

    public Coordinates getLastPoint() {
        return lastPoint;
    }
}
