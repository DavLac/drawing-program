package io.davlac.drawingapp.context.canvascontent.factory;

import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.DrawLineCanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.DrawRectangleCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;

public class CanvasContentRawArgumentsFactory {

    private CanvasContentRawArgumentsFactory() {
    }

    public static CanvasContentService getAction(ActionCommand actionCommand) {
        switch (actionCommand) {
            case DRAW_LINE:
                return new DrawLineCanvasContentService();
            case DRAW_RECTANGLE:
                return new DrawRectangleCanvasContentService();
            default:
                throw new IllegalArgumentException(String.format("ERROR : Action command '%s' is not implemented", actionCommand));
        }
    }
}
