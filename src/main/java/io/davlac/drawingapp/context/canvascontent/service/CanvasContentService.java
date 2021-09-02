package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;

import java.util.List;

public interface CanvasContentService {
    boolean validateArguments(List<String> arguments);

    DrawShapeRequest toDrawShapeRequest(List<String> arguments, final char[][] canvasContent);

    boolean validateDrawShapeRequest(DrawShapeRequest drawShapeRequest);

    char[][] drawShape(DrawShapeRequest drawShapeRequest);
}
