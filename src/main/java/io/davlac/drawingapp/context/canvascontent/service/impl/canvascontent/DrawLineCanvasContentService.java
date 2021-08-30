package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.DrawLineRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;

import java.util.stream.IntStream;

import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.LINE_CHAR;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_LINE;
import static io.davlac.drawingapp.utils.ValidatorUtils.validate;

public class DrawLineCanvasContentService implements CanvasContentService {
    @Override
    public CanvasContent drawLine(DrawLineRequest drawLineRequest) {
        validate(drawLineRequest);
        checkIfCoordinatesAreInsideCanvas(drawLineRequest.getFirstPoint(), drawLineRequest.getCanvasContent());
        checkIfCoordinatesAreInsideCanvas(drawLineRequest.getLastPoint(), drawLineRequest.getCanvasContent());
        checkIfCoordinatesAreAligned(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint());

        return drawLineWithAlignedCoordinates(drawLineRequest);
    }

    private static CanvasContent drawLineWithAlignedCoordinates(DrawLineRequest drawLineRequest) {
        char[][] canvasContent = drawLineRequest.getCanvasContent().getContent();

        if (coordinatesAreHorizontallyAligned(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint())) {
            canvasContent = drawHorizontalLine(
                    drawLineRequest.getFirstPoint(),
                    drawLineRequest.getLastPoint().getX(),
                    canvasContent
            );
        } else {
            canvasContent = drawVerticalLine(
                    drawLineRequest.getFirstPoint(),
                    drawLineRequest.getLastPoint().getY(),
                    canvasContent
            );
        }

        return new CanvasContent(canvasContent);
    }

    private static char[][] drawHorizontalLine(Coordinates firstPoint, int lastPointX, char[][] canvasContent) {
        int higherPoint;
        int smallerPoint;

        if (firstPoint.getX() >= lastPointX) {
            higherPoint = firstPoint.getX();
            smallerPoint = lastPointX;
        } else {
            higherPoint = lastPointX;
            smallerPoint = firstPoint.getX();
        }

        IntStream.range(smallerPoint - 1, higherPoint)
                .forEach(index -> canvasContent[firstPoint.getY() - 1][index] = LINE_CHAR.getChar());

        return canvasContent.clone();
    }

    private static char[][] drawVerticalLine(Coordinates firstPoint, int lastPointY, char[][] canvasContent) {
        int higherPoint;
        int smallerPoint;

        if (firstPoint.getY() >= lastPointY) {
            higherPoint = firstPoint.getY();
            smallerPoint = lastPointY;
        } else {
            higherPoint = lastPointY;
            smallerPoint = firstPoint.getY();
        }

        IntStream.range(smallerPoint - 1, higherPoint)
                .forEach(index -> canvasContent[index][firstPoint.getX() - 1] = LINE_CHAR.getChar());

        return canvasContent.clone();
    }

    private static void checkIfCoordinatesAreInsideCanvas(Coordinates coordinates, CanvasContent canvasContent) {
        if (coordinates.getX() > canvasContent.getContent()[0].length ||
                coordinates.getY() > canvasContent.getContent().length) {
            throw new IllegalArgumentException(
                    String.format("ERROR: Action '%s' - coordinates are outside the canvas : '%s'.",
                            DRAW_LINE, coordinates));
        }
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
