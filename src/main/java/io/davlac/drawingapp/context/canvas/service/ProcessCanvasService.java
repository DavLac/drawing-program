package io.davlac.drawingapp.context.canvas.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvas.service.impl.CreateCanvasService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;

public class ProcessCanvasService {

    private static final CanvasService canvasGenericArgumentsService = new CreateCanvasService();

    public Canvas generateCanvas(InputCommand inputCommand) {
        if (inputCommand.getAction() != CREATE_CANVAS) {
            throw new IllegalArgumentException(String.format("ERROR : Action command '%s' is not implemented", inputCommand.getAction()));
        }

        canvasGenericArgumentsService.validateArguments(inputCommand.getArguments());
        CreateCanvasRequest createCanvasRequest = canvasGenericArgumentsService.toCreateCanvasRequest(inputCommand.getArguments());
        canvasGenericArgumentsService.validateCreateCanvasRequest(createCanvasRequest);
        return canvasGenericArgumentsService.createCanvas(createCanvasRequest);
    }
}
