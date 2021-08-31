package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;

import java.util.List;

public interface CanvasContentService {
    void validateArguments(List<String> arguments);

    DrawShapeRequest toDrawShapeRequest(List<String> arguments, CanvasContent canvasContent);

    void validateDrawShapeRequest(DrawShapeRequest drawShapeRequest);

    CanvasContent drawShape(DrawShapeRequest drawShapeRequest);
}
