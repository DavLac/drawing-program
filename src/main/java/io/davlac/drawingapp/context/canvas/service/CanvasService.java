package io.davlac.drawingapp.context.canvas.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.model.CreateCanvasRequest;

public interface CanvasService {
    Canvas create(CreateCanvasRequest createCanvasRequest);
}
