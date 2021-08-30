package io.davlac.drawingapp.context.inputcommand.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvas.service.CanvasService;
import io.davlac.drawingapp.context.canvas.service.impl.CreateCanvasService;
import io.davlac.drawingapp.context.canvascontent.factory.CanvasContentRawArgumentsFactory;
import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public class CommandTypeService {

    private static final CanvasService canvasGenericArgumentsService = new CreateCanvasService();

    private CommandTypeService() {
    }

    public static Canvas processInputCommand(InputCommand inputCommand, Canvas canvas) {
        switch (inputCommand.getAction().getCommandType()) {
            case CANVAS_BODY:
                return generateCanvas(inputCommand);
            case CANVAS_CONTENT:
                return drawContent(inputCommand, canvas);
            default:
                throw new IllegalArgumentException(
                        String.format("ERROR : Command type '%s' is not implemented.", inputCommand.getAction().getCommandType()));
        }
    }

    private static Canvas generateCanvas(InputCommand inputCommand) {
        canvasGenericArgumentsService.validateArguments(inputCommand.getArguments());
        CreateCanvasRequest createCanvasRequest = canvasGenericArgumentsService.toCreateCanvasRequest(inputCommand.getArguments());
        canvasGenericArgumentsService.validateCreateCanvasRequest(createCanvasRequest);
        return canvasGenericArgumentsService.createCanvas(createCanvasRequest);
    }

    private static Canvas drawContent(InputCommand inputCommand, Canvas canvas) {
        checkCanvasContentNotEmpty(canvas.getCanvasContent());

        CanvasContentService canvasContentService = CanvasContentRawArgumentsFactory.getAction(inputCommand.getAction());

        canvasContentService.validateArguments(inputCommand.getArguments());
        DrawShapeRequest drawShapeRequest = canvasContentService.toDrawShapeRequest(inputCommand.getArguments(), canvas.getCanvasContent());
        canvasContentService.validateDrawShapeRequest(drawShapeRequest);
        CanvasContent canvasContent = canvasContentService.drawShape(drawShapeRequest);

        canvas.setCanvasContent(canvasContent);
        return canvas;
    }

    private static void checkCanvasContentNotEmpty(CanvasContent canvasContent) {
        if (canvasContent.isEmpty()) {
            throw new IllegalArgumentException("ERROR : Canvas content is empty.");
        }
    }

}
