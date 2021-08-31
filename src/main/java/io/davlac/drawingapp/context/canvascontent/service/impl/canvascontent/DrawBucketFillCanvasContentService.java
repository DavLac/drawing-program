package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawBucketFillRequest;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.FloodFillService;
import io.davlac.drawingapp.context.canvascontent.service.impl.floodfill.BreadthFirstSearchFloodFillService;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_BUCKET_FILL;
import static io.davlac.drawingapp.utils.ValidatorUtils.checkArgumentLength;
import static io.davlac.drawingapp.utils.ValidatorUtils.validateObjectConstraints;
import static java.lang.Integer.parseInt;

public class DrawBucketFillCanvasContentService extends AbstractCanvasContentService implements CanvasContentService {

    private static final FloodFillService floodFillService = new BreadthFirstSearchFloodFillService();

    @Override
    public void validateArguments(List<String> arguments) {
        checkArgumentLength(arguments, 3, DRAW_BUCKET_FILL);
    }

    @Override
    public DrawShapeRequest toDrawShapeRequest(List<String> arguments, CanvasContent canvasContent) {
        return new DrawBucketFillRequest(
                new Coordinates(parseInt(arguments.get(0)), parseInt(arguments.get(1))),
                arguments.get(2).charAt(0),
                canvasContent
        );
    }

    @Override
    public void validateDrawShapeRequest(DrawShapeRequest drawShapeRequest) {
        DrawBucketFillRequest drawBucketFillRequest = (DrawBucketFillRequest) drawShapeRequest;
        validateObjectConstraints(drawBucketFillRequest);
        checkIfCoordinatesAreInsideCanvas(drawBucketFillRequest.getFirstPoint(), drawBucketFillRequest.getCanvasContent(), DRAW_BUCKET_FILL);
    }

    @Override
    public CanvasContent drawShape(DrawShapeRequest drawShapeRequest) {
        DrawBucketFillRequest drawBucketFillRequest = (DrawBucketFillRequest) drawShapeRequest;
        char[][] canvasContent = drawBucketFillRequest.getCanvasContent().getContent();
        canvasContent = floodFillService.floodFill(canvasContent, drawBucketFillRequest.getFirstPoint(), drawBucketFillRequest.getColor());
        return new CanvasContent(canvasContent);
    }
}
