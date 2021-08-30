package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.DrawLineRequest;

public interface CanvasContentService {
    CanvasContent drawLine(DrawLineRequest drawLineRequest);
}
