package io.davlac.drawingapp.context.canvascontent.factory;

import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.DrawLineCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;

public class CanvasContentFactory {

    private CanvasContentFactory() {
    }

    public static CanvasContentService getAction(ActionCommand actionCommand) {
        switch (actionCommand) {
            case DRAW_LINE:
                return new DrawLineCanvasContentService();
            default:
                throw new IllegalArgumentException(String.format("ERROR : Action command '%s' is not implemented", actionCommand));
        }
    }
}
