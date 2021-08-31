package io.davlac.drawingapp.context.canvascontent.service.impl;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawRectangleRequest;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_RECTANGLE;
import static io.davlac.drawingapp.utils.ValidatorUtils.checkArgumentLength;
import static io.davlac.drawingapp.utils.ValidatorUtils.validateObjectConstraints;
import static java.lang.Integer.parseInt;

public class DrawRectangleCanvasContentService extends AbstractCanvasContentService implements CanvasContentService {

    @Override
    public void validateArguments(List<String> arguments) {
        checkArgumentLength(arguments, 4, DRAW_RECTANGLE);
    }

    @Override
    public DrawShapeRequest toDrawShapeRequest(List<String> arguments, CanvasContent canvasContent) {
        return new DrawRectangleRequest(
                new Coordinates(parseInt(arguments.get(0)), parseInt(arguments.get(1))),
                new Coordinates(parseInt(arguments.get(2)), parseInt(arguments.get(3))),
                canvasContent
        );
    }

    @Override
    public void validateDrawShapeRequest(DrawShapeRequest drawShapeRequest) {
        DrawRectangleRequest drawRectangleRequest = (DrawRectangleRequest) drawShapeRequest;
        validateObjectConstraints(drawRectangleRequest);
        checkIfCoordinatesAreInsideCanvas(drawRectangleRequest.getFirstPoint(), drawRectangleRequest.getCanvasContent(), DRAW_RECTANGLE);
        checkIfCoordinatesAreInsideCanvas(drawRectangleRequest.getLastPoint(), drawRectangleRequest.getCanvasContent(), DRAW_RECTANGLE);
        checkTopLeftAndBottomRightCoordinates(drawRectangleRequest.getFirstPoint(), drawRectangleRequest.getLastPoint());
    }

    @Override
    public CanvasContent drawShape(DrawShapeRequest drawShapeRequest) {
        DrawRectangleRequest drawRectangleRequest = (DrawRectangleRequest) drawShapeRequest;
        char[][] canvasContent = drawRectangleRequest.getCanvasContent().getContent();
        Coordinates topLeftCorner = drawRectangleRequest.getFirstPoint();
        Coordinates bottomRightCorner = drawRectangleRequest.getLastPoint();

        return drawRectangle(topLeftCorner, bottomRightCorner, canvasContent);
    }

    private static CanvasContent drawRectangle(Coordinates topLeftCorner, Coordinates bottomRightCorner, char[][] canvasContent) {
        Coordinates topRightCorner = new Coordinates(bottomRightCorner.getX(), topLeftCorner.getY());
        Coordinates bottomLeftCorner = new Coordinates(topLeftCorner.getX(), bottomRightCorner.getY());

        canvasContent = drawHorizontalLine(topLeftCorner, topRightCorner, canvasContent);
        canvasContent = drawHorizontalLine(bottomLeftCorner, bottomRightCorner, canvasContent);
        canvasContent = drawVerticalLine(topLeftCorner, bottomLeftCorner, canvasContent);
        canvasContent = drawVerticalLine(topRightCorner, bottomRightCorner, canvasContent);

        return new CanvasContent(canvasContent);
    }

    private static void checkTopLeftAndBottomRightCoordinates(Coordinates firstPoint, Coordinates lastPoint) {
        if (firstPoint.getX() > lastPoint.getX() || firstPoint.getY() > lastPoint.getY()) {
            throw new IllegalArgumentException(
                    String.format("ERROR: Action '%s' - First coordinate '%s' should be in the top left corner and " +
                            "the second should be in the bottom right corner '%s'", DRAW_RECTANGLE, firstPoint, lastPoint));
        }
    }
}
