package io.davlac.drawingapp.context.inputcommand.service.impl;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.service.ProcessCanvasService;
import io.davlac.drawingapp.context.canvas.service.impl.ProcessCanvasServiceImpl;
import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.service.ProcessCanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.process.ProcessCanvasContentServiceImpl;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;

public class CommandTypeServiceImpl implements CommandTypeService {

    private final ProcessCanvasService processCanvasService = new ProcessCanvasServiceImpl();
    private final ProcessCanvasContentService processCanvasContentService = new ProcessCanvasContentServiceImpl();

    @Override
    public Canvas processInputCommand(InputCommand inputCommand, Canvas canvas) {
        switch (inputCommand.getAction().getCommandType()) {
            case CANVAS_BODY:
                processCanvasService.checkActionIsValid(inputCommand.getAction());
                return processCanvasService.generateCanvas(inputCommand);
            case CANVAS_CONTENT:
                CanvasContent canvasContent = processCanvasContentService.processDrawContent(inputCommand, canvas.getCanvasContent());
                canvas.setCanvasContent(canvasContent);
                return canvas;
            default:
                throw new IllegalArgumentException(
                        String.format("ERROR : Command type '%s' is not implemented.", inputCommand.getAction().getCommandType()));
        }
    }

}
