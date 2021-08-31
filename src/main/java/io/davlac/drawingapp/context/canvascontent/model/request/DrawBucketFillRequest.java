package io.davlac.drawingapp.context.canvascontent.model.request;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;

import javax.validation.constraints.NotNull;

public final class DrawBucketFillRequest extends DrawShapeRequest {
    @NotNull
    private final char color;

    public DrawBucketFillRequest(Coordinates firstPoint, char color, CanvasContent canvasContent) {
        super(firstPoint, canvasContent);
        this.color = color;
    }

    public char getColor() {
        return color;
    }
}
