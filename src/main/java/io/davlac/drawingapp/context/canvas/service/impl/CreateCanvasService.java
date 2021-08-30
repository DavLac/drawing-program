package io.davlac.drawingapp.context.canvas.service.impl;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvas.service.CanvasService;

import static io.davlac.drawingapp.utils.ValidatorUtils.validate;

public class CreateCanvasService implements CanvasService {

    @Override
    public Canvas create(CreateCanvasRequest createCanvasRequest) {
        validate(createCanvasRequest);
        return Canvas.create(createCanvasRequest.getWidth(), createCanvasRequest.getHeight());
    }
}
