package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawLineRequest;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_LINE;
import static io.davlac.drawingapp.utils.ValidatorUtils.checkArgumentLength;
import static io.davlac.drawingapp.utils.ValidatorUtils.validateObjectConstraints;
import static java.lang.Integer.parseInt;

public class DrawLineCanvasContentService extends AbstractCanvasContentService implements CanvasContentService {

    @Override
    public void validateArguments(List<String> arguments) {
        checkArgumentLength(arguments, 4, DRAW_LINE);
    }

    @Override
    public DrawShapeRequest toDrawShapeRequest(List<String> arguments, CanvasContent canvasContent) {
        return new DrawLineRequest(
                new Coordinates(parseInt(arguments.get(0)), parseInt(arguments.get(1))),
                new Coordinates(parseInt(arguments.get(2)), parseInt(arguments.get(3))),
                canvasContent
        );
    }

    @Override
    public void validateDrawShapeRequest(DrawShapeRequest drawShapeRequest) {
        DrawLineRequest drawLineRequest = (DrawLineRequest) drawShapeRequest;
        validateObjectConstraints(drawLineRequest);
        checkIfCoordinatesAreInsideCanvas(drawLineRequest.getFirstPoint(), drawLineRequest.getCanvasContent(), DRAW_LINE);
        checkIfCoordinatesAreInsideCanvas(drawLineRequest.getLastPoint(), drawLineRequest.getCanvasContent(), DRAW_LINE);
        checkIfCoordinatesAreAligned(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint());
    }

    @Override
    public CanvasContent drawShape(DrawShapeRequest drawShapeRequest) {
        DrawLineRequest drawLineRequest = (DrawLineRequest) drawShapeRequest;
        char[][] canvasContent = drawLineRequest.getCanvasContent().getContent();

        if (coordinatesAreHorizontallyAligned(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint())) {
            canvasContent = drawHorizontalLine(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint(), canvasContent);
        } else {
            canvasContent = drawVerticalLine(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint(), canvasContent);
        }

        return new CanvasContent(canvasContent);
    }

    private static void checkIfCoordinatesAreAligned(Coordinates firstPoint, Coordinates lastPoint) {
        if (!coordinatesAreHorizontallyAligned(firstPoint, lastPoint) &&
                !coordinatesAreVerticallyAligned(firstPoint, lastPoint)) {
            throw new IllegalArgumentException(
                    String.format("ERROR: Action '%s' - '%s' and '%s' are not aligned.",
                            DRAW_LINE, firstPoint, lastPoint));
        }
    }

    private static boolean coordinatesAreHorizontallyAligned(Coordinates firstPoint, Coordinates lastPoint) {
        return firstPoint.getY().equals(lastPoint.getY());
    }

    private static boolean coordinatesAreVerticallyAligned(Coordinates firstPoint, Coordinates lastPoint) {
        return firstPoint.getX().equals(lastPoint.getX());
    }
}
