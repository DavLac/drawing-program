package io.davlac.drawingapp.context.canvasbody.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.model.CreateCanvasRequest;

import java.util.List;

public interface CanvasService {
    boolean validArguments(List<String> arguments);

    CreateCanvasRequest toCreateCanvasRequest(List<String> arguments);

    boolean validateCreateCanvasRequest(CreateCanvasRequest createCanvasRequest);

    Canvas createCanvas(CreateCanvasRequest createCanvasRequest);
}
