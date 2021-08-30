package io.davlac.drawingapp.context.inputcommand.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.service.CanvasGenericArgumentsService;
import io.davlac.drawingapp.context.canvas.service.impl.CreateCanvasGenericArgumentsService;
import io.davlac.drawingapp.context.canvascontent.factory.CanvasContentFactory;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public class CommandTypeService {

    private static final CanvasGenericArgumentsService canvasGenericArgumentsService = new CreateCanvasGenericArgumentsService();

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
        return canvasGenericArgumentsService.create(inputCommand.getArguments());
    }

    private static Canvas drawContent(InputCommand inputCommand, Canvas canvas) {
        CanvasContentService canvasContentService = CanvasContentFactory.getAction(inputCommand.getAction());
        String[][] content = canvasContentService.draw();
        canvas.getCanvasContent().setContent(content);
        return canvas;
    }
}
