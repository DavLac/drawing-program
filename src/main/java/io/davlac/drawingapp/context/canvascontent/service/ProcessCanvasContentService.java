package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.factory.CanvasContentRawArgumentsFactory;
import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public class ProcessCanvasContentService {

    public CanvasContent drawContent(InputCommand inputCommand, CanvasContent canvasContent) {
        checkCanvasContentNotEmpty(canvasContent);

        CanvasContentService canvasContentService = CanvasContentRawArgumentsFactory.getAction(inputCommand.getAction());

        canvasContentService.validateArguments(inputCommand.getArguments());
        DrawShapeRequest drawShapeRequest = canvasContentService.toDrawShapeRequest(inputCommand.getArguments(), canvasContent);
        canvasContentService.validateDrawShapeRequest(drawShapeRequest);
        return canvasContentService.drawShape(drawShapeRequest);
    }

    private static void checkCanvasContentNotEmpty(CanvasContent canvasContent) {
        if (canvasContent.isEmpty()) {
            throw new IllegalArgumentException("ERROR : Canvas content is empty.");
        }
    }
}
