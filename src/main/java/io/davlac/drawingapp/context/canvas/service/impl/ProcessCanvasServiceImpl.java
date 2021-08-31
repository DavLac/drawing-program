package io.davlac.drawingapp.context.canvas.service.impl;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvas.service.CanvasService;
import io.davlac.drawingapp.context.canvas.service.ProcessCanvasService;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;

public class ProcessCanvasServiceImpl implements ProcessCanvasService {

    private final CanvasService canvasGenericArgumentsService = new CreateCanvasService();

    @Override
    public void checkActionIsValid(ActionCommand actionCommand) {
        // replace this method by a factory if there are other actions on the Canvas (resize canvas...) like CanvasContentFactory
        if (actionCommand != CREATE_CANVAS) {
            throw new IllegalArgumentException(String.format("ERROR : Action command '%s' is not implemented", actionCommand));
        }
    }

    @Override
    public Canvas generateCanvas(InputCommand inputCommand) {
        canvasGenericArgumentsService.validateArguments(inputCommand.getArguments());
        CreateCanvasRequest createCanvasRequest = canvasGenericArgumentsService.toCreateCanvasRequest(inputCommand.getArguments());
        canvasGenericArgumentsService.validateCreateCanvasRequest(createCanvasRequest);
        return canvasGenericArgumentsService.createCanvas(createCanvasRequest);
    }
}
