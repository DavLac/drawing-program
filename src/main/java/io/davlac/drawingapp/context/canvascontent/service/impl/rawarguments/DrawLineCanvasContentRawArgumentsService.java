package io.davlac.drawingapp.context.canvascontent.service.impl.rawarguments;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.canvascontent.model.Coordinates;
import io.davlac.drawingapp.context.canvascontent.model.DrawLineRequest;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentRawArgumentsService;
import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawLineCanvasContentService;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_LINE;
import static io.davlac.drawingapp.utils.ValidatorUtils.checkArgumentLength;
import static io.davlac.drawingapp.utils.ValidatorUtils.checkIfStringsAreTheSameTypeThanObjectFields;
import static java.lang.Integer.parseInt;

public class DrawLineCanvasContentRawArgumentsService implements CanvasContentRawArgumentsService {

    private static final CanvasContentService canvasContentService = new DrawLineCanvasContentService();

    @Override
    public CanvasContent draw(List<String> arguments, CanvasContent canvasContent) {
        checkArgumentLength(arguments, 4, DRAW_LINE);
        checkIfStringsAreTheSameTypeThanObjectFields(Coordinates.class, List.of(arguments.get(0), arguments.get(1)));
        checkIfStringsAreTheSameTypeThanObjectFields(Coordinates.class, List.of(arguments.get(2), arguments.get(3)));

        return canvasContentService.drawLine(toDrawLineRequest(arguments, canvasContent));
    }

    private static DrawLineRequest toDrawLineRequest(List<String> arguments, CanvasContent canvasContent) {
        return DrawLineRequest.builder()
                .firstPoint(parseInt(arguments.get(0)), parseInt(arguments.get(1)))
                .lastPoint(parseInt(arguments.get(2)), parseInt(arguments.get(3)))
                .canvasContent(canvasContent)
                .create();
    }
}
