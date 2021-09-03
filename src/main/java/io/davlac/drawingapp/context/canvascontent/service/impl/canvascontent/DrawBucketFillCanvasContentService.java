package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawBucketFillRequest;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.FloodFillService;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import io.davlac.drawingapp.context.inputcommand.utils.InputCommandUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_BUCKET_FILL;
import static java.lang.Integer.parseInt;

@Service
public class DrawBucketFillCanvasContentService extends AbstractCanvasContentService implements CanvasContentService {

    private final FloodFillService floodFillService;
    private final ValidatorService validatorService;

    public DrawBucketFillCanvasContentService(FloodFillService floodFillService,
                                              ValidatorService validatorService) {
        this.floodFillService = floodFillService;
        this.validatorService = validatorService;
    }

    @Override
    public boolean validateArguments(List<String> arguments) {
        return InputCommandUtils.checkArgumentLength(arguments, DRAW_BUCKET_FILL);
    }

    @Override
    public DrawShapeRequest toDrawShapeRequest(List<String> arguments, final char[][] canvasContent) {
        return new DrawBucketFillRequest(
                new Coordinates(parseInt(arguments.get(0)), parseInt(arguments.get(1))),
                arguments.get(2).charAt(0),
                canvasContent
        );
    }

    @Override
    public boolean validateDrawShapeRequest(DrawShapeRequest drawShapeRequest) {
        DrawBucketFillRequest drawBucketFillRequest = (DrawBucketFillRequest) drawShapeRequest;
        validatorService.validateObjectConstraints(drawBucketFillRequest);
        checkIfCoordinatesAreInsideCanvas(drawBucketFillRequest.getFirstPoint(), drawBucketFillRequest.getCanvasContent(), DRAW_BUCKET_FILL);
        return true;
    }

    @Override
    public char[][] drawShape(DrawShapeRequest drawShapeRequest) {
        DrawBucketFillRequest drawBucketFillRequest = (DrawBucketFillRequest) drawShapeRequest;
        char[][] canvasContent = drawBucketFillRequest.getCanvasContent();
        return floodFillService.floodFill(canvasContent, drawBucketFillRequest.getFirstPoint(), drawBucketFillRequest.getColor());
    }
}
