package io.davlac.drawingapp.context.inputcommand.service.impl;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.service.ProcessCanvasService;
import io.davlac.drawingapp.context.canvascontent.service.ProcessCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import io.davlac.drawingapp.context.inputcommand.service.CommandTypeService;
import org.springframework.stereotype.Component;

@Component
public class CommandTypeServiceImpl implements CommandTypeService {

    private final ProcessCanvasService processCanvasService;
    private final ProcessCanvasContentService processCanvasContentService;

    public CommandTypeServiceImpl(ProcessCanvasService processCanvasService,
                                  ProcessCanvasContentService processCanvasContentService) {
        this.processCanvasService = processCanvasService;
        this.processCanvasContentService = processCanvasContentService;
    }

    @Override
    public Canvas processInputCommand(InputCommand inputCommand, Canvas canvas) {
        switch (inputCommand.getAction().getCommandType()) {
            case CANVAS_BODY:
                processCanvasService.checkActionIsValid(inputCommand.getAction());
                return processCanvasService.generateCanvas(inputCommand);
            case CANVAS_CONTENT:
                char[][] canvasContent = processCanvasContentService.processDrawContent(inputCommand, canvas.getContent());
                canvas.setContent(canvasContent);
                return canvas;
            default:
                throw new IllegalArgumentException(
                        String.format("ERROR : Command type '%s' is not implemented.", inputCommand.getAction().getCommandType()));
        }
    }

}
