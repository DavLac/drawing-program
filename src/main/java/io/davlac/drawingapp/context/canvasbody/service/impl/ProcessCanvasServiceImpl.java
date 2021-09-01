package io.davlac.drawingapp.context.canvasbody.service.impl;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvasbody.service.CanvasService;
import io.davlac.drawingapp.context.canvasbody.service.ProcessCanvasService;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import org.springframework.stereotype.Service;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;
import static io.davlac.drawingapp.context.inputcommand.model.CommandType.CANVAS_BODY;

@Service
public class ProcessCanvasServiceImpl implements ProcessCanvasService {

    private final CanvasService canvasService;

    public ProcessCanvasServiceImpl(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @Override
    public boolean checkActionIsValid(ActionCommand actionCommand) {
        // replace this method by a factory if there are other actions on the Canvas (resize canvas...) like the Canvas content factory
        if (actionCommand != CREATE_CANVAS) {
            throw new IllegalArgumentException(
                    String.format("ERROR : Action command '%s' is not implemented for type = '%s'", actionCommand, CANVAS_BODY));
        }

        return true;
    }

    @Override
    public Canvas generateCanvas(InputCommand inputCommand) {
        canvasService.validArguments(inputCommand.getArguments());
        CreateCanvasRequest createCanvasRequest = canvasService.toCreateCanvasRequest(inputCommand.getArguments());
        canvasService.validateCreateCanvasRequest(createCanvasRequest);
        return canvasService.createCanvas(createCanvasRequest);
    }
}
