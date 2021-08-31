package io.davlac.drawingapp.context.canvas.service.impl;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvas.service.CanvasService;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;
import static io.davlac.drawingapp.context.canvascontent.utils.ValidatorUtils.checkArgumentLength;
import static io.davlac.drawingapp.context.canvascontent.utils.ValidatorUtils.validateObjectConstraints;

public class CreateCanvasService implements CanvasService {

    @Override
    public void validateArguments(List<String> arguments) {
        checkArgumentLength(arguments, CREATE_CANVAS);
    }

    @Override
    public CreateCanvasRequest toCreateCanvasRequest(List<String> arguments) {
        return new CreateCanvasRequest(Integer.parseInt(arguments.get(0)), Integer.parseInt(arguments.get(1)));
    }

    @Override
    public void validateCreateCanvasRequest(CreateCanvasRequest createCanvasRequest) {
        validateObjectConstraints(createCanvasRequest);
    }

    @Override
    public Canvas createCanvas(CreateCanvasRequest createCanvasRequest) {
        return Canvas.create(createCanvasRequest.getWidth(), createCanvasRequest.getHeight());
    }
}
