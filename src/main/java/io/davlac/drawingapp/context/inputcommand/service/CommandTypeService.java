package io.davlac.drawingapp.context.inputcommand.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.service.ProcessCanvasService;
import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.service.ProcessCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public class CommandTypeService {

    private static final ProcessCanvasService processCanvasService = new ProcessCanvasService();
    private static final ProcessCanvasContentService processCanvasContentService = new ProcessCanvasContentService();

    private CommandTypeService() {
    }

    public static Canvas processInputCommand(InputCommand inputCommand, Canvas canvas) {
        switch (inputCommand.getAction().getCommandType()) {
            case CANVAS_BODY:
                return processCanvasService.generateCanvas(inputCommand);
            case CANVAS_CONTENT:
                CanvasContent canvasContent = processCanvasContentService.drawContent(inputCommand, canvas.getCanvasContent());
                canvas.setCanvasContent(canvasContent);
                return canvas;
            default:
                throw new IllegalArgumentException(
                        String.format("ERROR : Command type '%s' is not implemented.", inputCommand.getAction().getCommandType()));
        }
    }

}
