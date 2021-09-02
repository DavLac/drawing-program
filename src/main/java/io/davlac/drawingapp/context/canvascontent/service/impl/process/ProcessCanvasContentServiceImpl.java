package io.davlac.drawingapp.context.canvascontent.service.impl.process;

import io.davlac.drawingapp.context.canvascontent.factory.CanvasContentFactory;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.ProcessCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import org.springframework.stereotype.Service;

@Service
public class ProcessCanvasContentServiceImpl implements ProcessCanvasContentService {

    private final CanvasContentFactory canvasContentFactory;

    public ProcessCanvasContentServiceImpl(CanvasContentFactory canvasContentFactory) {
        this.canvasContentFactory = canvasContentFactory;
    }

    @Override
    public char[][] processDrawContent(InputCommand inputCommand, final char[][] canvasContent) {
        checkInputCommandNotNull(inputCommand);
        checkCanvasContentNotEmpty(canvasContent);

        CanvasContentService canvasContentService = canvasContentFactory.getAction(inputCommand.getAction());

        canvasContentService.validateArguments(inputCommand.getArguments());
        DrawShapeRequest drawShapeRequest = canvasContentService.toDrawShapeRequest(inputCommand.getArguments(), canvasContent);
        canvasContentService.validateDrawShapeRequest(drawShapeRequest);
        return canvasContentService.drawShape(drawShapeRequest);
    }

    private static void checkInputCommandNotNull(InputCommand inputCommand) {
        if (inputCommand == null) {
            throw new IllegalArgumentException("ERROR : InputCommand is null.");
        }
    }

    private static void checkCanvasContentNotEmpty(final char[][] canvasContent) {
        if (canvasContent == null || canvasContent.length == 0) {
            throw new IllegalArgumentException("ERROR : Canvas content is empty.");
        }
    }
}
