package io.davlac.drawingapp.context.canvascontent.service.impl.process;

import io.davlac.drawingapp.context.canvascontent.factory.CanvasContentFactory;
import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.ProcessCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public class ProcessCanvasContentServiceImpl implements ProcessCanvasContentService {

    @Override
    public CanvasContent processDrawContent(InputCommand inputCommand, CanvasContent canvasContent) {
        checkCanvasContentNotEmpty(canvasContent);

        CanvasContentService canvasContentService = CanvasContentFactory.getAction(inputCommand.getAction());

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
