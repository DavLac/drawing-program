package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawBucketFillRequest;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.FloodFillService;
import io.davlac.drawingapp.context.canvascontent.service.impl.floodfill.BreadthFirstSearchFloodFillService;

import java.util.List;

import static io.davlac.drawingapp.context.canvascontent.utils.ValidatorUtils.checkArgumentLength;
import static io.davlac.drawingapp.context.canvascontent.utils.ValidatorUtils.validateObjectConstraints;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_BUCKET_FILL;
import static java.lang.Integer.parseInt;

public class DrawBucketFillCanvasContentService extends AbstractCanvasContentService implements CanvasContentService {

    private static final String BLANK_KEY_WORD_ARGUMENT = "blank";
    private static final char BLANK_CHAR = ' ';

    private final FloodFillService floodFillService = new BreadthFirstSearchFloodFillService();

    @Override
    public void validateArguments(List<String> arguments) {
        checkArgumentLength(arguments, DRAW_BUCKET_FILL);
    }

    @Override
    public DrawShapeRequest toDrawShapeRequest(List<String> arguments, final char[][] canvasContent) {
        char color = (BLANK_KEY_WORD_ARGUMENT.equals(arguments.get(2)))
                ? BLANK_CHAR
                : arguments.get(2).charAt(0);

        return new DrawBucketFillRequest(
                new Coordinates(parseInt(arguments.get(0)), parseInt(arguments.get(1))),
                color,
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
    public char[][] drawShape(DrawShapeRequest drawShapeRequest) {
        DrawBucketFillRequest drawBucketFillRequest = (DrawBucketFillRequest) drawShapeRequest;
        char[][] canvasContent = drawBucketFillRequest.getCanvasContent();
        canvasContent = floodFillService.floodFill(canvasContent, drawBucketFillRequest.getFirstPoint(), drawBucketFillRequest.getColor());
        return canvasContent;
    }
}
