package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;

import java.util.stream.IntStream;

import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.LINE_CHAR;

public abstract class AbstractCanvasContentService {

    protected static char[][] drawHorizontalLine(Coordinates firstPoint, Coordinates lastPoint, char[][] canvasContent) {
        int higherPoint;
        int smallerPoint;
        char[][] canvasContentModified = canvasContent.clone();

        if (firstPoint.getX() >= lastPoint.getX()) {
            higherPoint = firstPoint.getX();
            smallerPoint = lastPoint.getX();
        } else {
            higherPoint = lastPoint.getX();
            smallerPoint = firstPoint.getX();
        }

        IntStream.range(smallerPoint - 1, higherPoint)
                .forEach(index -> canvasContentModified[firstPoint.getY() - 1][index] = LINE_CHAR.getChar());

        return canvasContentModified;
    }

    protected static char[][] drawVerticalLine(Coordinates firstPoint, Coordinates lastPoint, char[][] canvasContent) {
        int higherPoint;
        int smallerPoint;
        char[][] canvasContentModified = canvasContent.clone();

        if (firstPoint.getY() >= lastPoint.getY()) {
            higherPoint = firstPoint.getY();
            smallerPoint = lastPoint.getY();
        } else {
            higherPoint = lastPoint.getY();
            smallerPoint = firstPoint.getY();
        }

        IntStream.range(smallerPoint - 1, higherPoint)
                .forEach(index -> canvasContentModified[index][firstPoint.getX() - 1] = LINE_CHAR.getChar());

        return canvasContentModified;
    }

    protected static void checkIfCoordinatesAreInsideCanvas(Coordinates coordinates,
                                                            CanvasContent canvasContent,
                                                            ActionCommand actionCommand) {
        if (coordinates.getX() > canvasContent.getContent()[0].length ||
                coordinates.getY() > canvasContent.getContent().length) {
            throw new IllegalArgumentException(
                    String.format("ERROR: Action '%s' - coordinates are outside the canvas : '%s'.", actionCommand, coordinates));
        }
    }
}
