package io.davlac.drawingapp.context.canvascontent.factory;

import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.FloodFillService;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawBucketFillCanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawLineCanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawRectangleCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import org.springframework.stereotype.Component;

@Component
public class CanvasContentFactory {

    private final ValidatorService validatorService;
    private final FloodFillService floodFillService;

    public CanvasContentFactory(ValidatorService validatorService,
                                FloodFillService floodFillService) {
        this.validatorService = validatorService;
        this.floodFillService = floodFillService;
    }

    public CanvasContentService getAction(ActionCommand actionCommand) {
        switch (actionCommand) {
            case DRAW_LINE:
                return new DrawLineCanvasContentService(validatorService);
            case DRAW_RECTANGLE:
                return new DrawRectangleCanvasContentService(validatorService);
            case DRAW_BUCKET_FILL:
                return new DrawBucketFillCanvasContentService(floodFillService, validatorService);
            default:
                throw new IllegalArgumentException(String.format("ERROR : Action command '%s' is not implemented", actionCommand));
        }
    }
}
