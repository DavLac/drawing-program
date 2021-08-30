package io.davlac.drawingapp.context.canvascontent.factory;

import io.davlac.drawingapp.context.canvascontent.service.CanvasContentRawArgumentsService;
import io.davlac.drawingapp.context.canvascontent.service.impl.rawarguments.DrawLineCanvasContentRawArgumentsService;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;

public class CanvasContentRawArgumentsFactory {

    private CanvasContentRawArgumentsFactory() {
    }

    public static CanvasContentRawArgumentsService getAction(ActionCommand actionCommand) {
        switch (actionCommand) {
            case DRAW_LINE:
                return new DrawLineCanvasContentRawArgumentsService();
            default:
                throw new IllegalArgumentException(String.format("ERROR : Action command '%s' is not implemented", actionCommand));
        }
    }
}
