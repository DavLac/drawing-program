package io.davlac.drawingapp.context.canvas.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.model.CreateCanvasRequest;

import java.util.List;

public interface CanvasService {
    void validateArguments(List<String> arguments);

    CreateCanvasRequest toCreateCanvasRequest(List<String> arguments);

    void validateCreateCanvasRequest(CreateCanvasRequest createCanvasRequest);

    Canvas createCanvas(CreateCanvasRequest createCanvasRequest);
}
