package io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent;

import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeRequest;
import io.davlac.drawingapp.context.canvascontent.model.request.DrawShapeTwoPointsRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import io.davlac.drawingapp.context.inputcommand.utils.InputCommandUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_LINE;
import static java.lang.Integer.parseInt;

@Service
public class DrawLineCanvasContentService extends AbstractCanvasContentService implements CanvasContentService {

    private final ValidatorService validatorService;

    public DrawLineCanvasContentService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }


    @Override
    public boolean validateArguments(List<String> arguments) {
        return InputCommandUtils.checkArgumentLength(arguments, DRAW_LINE);
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
        DrawShapeTwoPointsRequest drawLineRequest = (DrawShapeTwoPointsRequest) drawShapeRequest;
        validatorService.validateObjectConstraints(drawLineRequest);
        checkIfCoordinatesAreInsideCanvas(drawLineRequest.getFirstPoint(), drawLineRequest.getCanvasContent(), DRAW_LINE);
        checkIfCoordinatesAreInsideCanvas(drawLineRequest.getLastPoint(), drawLineRequest.getCanvasContent(), DRAW_LINE);
        checkIfCoordinatesAreAligned(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint());
        return true;
    }

    @Override
    public char[][] drawShape(DrawShapeRequest drawShapeRequest) {
        DrawShapeTwoPointsRequest drawLineRequest = (DrawShapeTwoPointsRequest) drawShapeRequest;
        char[][] canvasContent = drawLineRequest.getCanvasContent();

        if (coordinatesAreHorizontallyAligned(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint())) {
            canvasContent = drawHorizontalLine(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint(), canvasContent);
        } else {
            canvasContent = drawVerticalLine(drawLineRequest.getFirstPoint(), drawLineRequest.getLastPoint(), canvasContent);
        }

        return canvasContent;
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
