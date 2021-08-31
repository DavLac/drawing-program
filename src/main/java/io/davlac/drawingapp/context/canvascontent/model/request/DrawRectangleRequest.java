package io.davlac.drawingapp.context.canvascontent.model.request;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;

public final class DrawRectangleRequest extends DrawShapeTwoPointsRequest {
    public DrawRectangleRequest(Coordinates firstPoint, Coordinates lastPoint, CanvasContent canvasContent) {
        super(firstPoint, lastPoint, canvasContent);
    }
}
