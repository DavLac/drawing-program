package io.davlac.drawingapp.context.canvasbody.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.model.CreateCanvasRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

public interface CanvasService {
    void validateArguments(List<String> arguments);

    CreateCanvasRequest toCreateCanvasRequest(List<String> arguments);

    void validateCreateCanvasRequest(CreateCanvasRequest createCanvasRequest);

    Canvas createCanvas(CreateCanvasRequest createCanvasRequest);
}
