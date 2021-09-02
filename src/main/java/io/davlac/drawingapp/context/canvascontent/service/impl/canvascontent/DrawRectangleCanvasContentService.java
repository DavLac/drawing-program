package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeTwoPointsRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import io.davlac.drawingapp.context.inputcommand.utils.InputCheckUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_RECTANGLE;
import static java.lang.Integer.parseInt;

@Service
public class DrawRectangleCanvasContentService extends AbstractCanvasContentService implements CanvasContentService {

    private final ValidatorService validatorService;

    public DrawRectangleCanvasContentService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public boolean validateArguments(List<String> arguments) {
        return InputCheckUtils.checkArgumentLength(arguments, DRAW_RECTANGLE);
    }

    @Override
    public DrawShapeRequest toDrawShapeRequest(List<String> arguments, final char[][] canvasContent) {
        return new DrawShapeTwoPointsRequest(
                new Coordinates(parseInt(arguments.get(0)), parseInt(arguments.get(1))),
                new Coordinates(parseInt(arguments.get(2)), parseInt(arguments.get(3))),
                canvasContent
        );
    }

    @Override
    public boolean validateDrawShapeRequest(DrawShapeRequest drawShapeRequest) {
        DrawShapeTwoPointsRequest drawRectangleRequest = (DrawShapeTwoPointsRequest) drawShapeRequest;
        validatorService.validateObjectConstraints(drawRectangleRequest);
        checkIfCoordinatesAreInsideCanvas(drawRectangleRequest.getFirstPoint(), drawRectangleRequest.getCanvasContent(), DRAW_RECTANGLE);
        checkIfCoordinatesAreInsideCanvas(drawRectangleRequest.getLastPoint(), drawRectangleRequest.getCanvasContent(), DRAW_RECTANGLE);
        checkTopLeftAndBottomRightCoordinates(drawRectangleRequest.getFirstPoint(), drawRectangleRequest.getLastPoint());
        return true;
    }

    @Override
    public char[][] drawShape(DrawShapeRequest drawShapeRequest) {
        DrawShapeTwoPointsRequest drawRectangleRequest = (DrawShapeTwoPointsRequest) drawShapeRequest;
        char[][] canvasContent = drawRectangleRequest.getCanvasContent();
        Coordinates topLeftCorner = drawRectangleRequest.getFirstPoint();
        Coordinates bottomRightCorner = drawRectangleRequest.getLastPoint();

        return drawRectangle(topLeftCorner, bottomRightCorner, canvasContent);
    }

    private static char[][] drawRectangle(Coordinates topLeftCorner, Coordinates bottomRightCorner, char[][] canvasContent) {
        Coordinates topRightCorner = new Coordinates(bottomRightCorner.getX(), topLeftCorner.getY());
        Coordinates bottomLeftCorner = new Coordinates(topLeftCorner.getX(), bottomRightCorner.getY());

        canvasContent = drawHorizontalLine(topLeftCorner, topRightCorner, canvasContent);
        canvasContent = drawHorizontalLine(bottomLeftCorner, bottomRightCorner, canvasContent);
        canvasContent = drawVerticalLine(topLeftCorner, bottomLeftCorner, canvasContent);
        canvasContent = drawVerticalLine(topRightCorner, bottomRightCorner, canvasContent);

        return canvasContent;
    }

    private static void checkTopLeftAndBottomRightCoordinates(Coordinates firstPoint, Coordinates lastPoint) {
        if (firstPoint.getX() > lastPoint.getX() || firstPoint.getY() > lastPoint.getY()) {
            throw new IllegalArgumentException(
                    String.format("ERROR: Action '%s' - First coordinate '%s' should be in the top left corner and " +
                            "the second should be in the bottom right corner '%s'", DRAW_RECTANGLE, firstPoint, lastPoint));
        }
    }
}
