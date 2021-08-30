package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.DrawLineRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_LINE;
import static io.davlac.drawingapp.utils.ValidatorUtils.validate;

public class DrawLineCanvasContentService implements CanvasContentService {
    @Override
    public CanvasContent drawLine(DrawLineRequest drawLineRequest) {
        validate(drawLineRequest);
        checkIfCoordinatesAreAligned(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint());
        return generateLine(drawLineRequest);
    }

    private static CanvasContent generateLine(DrawLineRequest drawLineRequest) {
        return drawLineRequest.getCanvasContent();
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
        return firstPoint.getX().equals(lastPoint.getX());
    }

    private static boolean coordinatesAreVerticallyAligned(Coordinates firstPoint, Coordinates lastPoint) {
        return firstPoint.getY().equals(lastPoint.getY());
    }
}
