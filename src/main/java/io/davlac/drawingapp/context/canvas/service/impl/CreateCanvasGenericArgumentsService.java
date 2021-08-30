package io.davlac.drawingapp.context.canvas.service.impl;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.canvas.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvas.service.CanvasGenericArgumentsService;
import io.davlac.drawingapp.context.canvas.service.CanvasService;

import java.util.List;

import static io.davlac.drawingapp.utils.ValidatorUtils.checkIfStringsAreTheSameTypeThanObjectFields;

public class CreateCanvasGenericArgumentsService implements CanvasGenericArgumentsService {

    private static final CanvasService createCanvasService = new CreateCanvasService();

    @Override
    public Canvas create(List<String> arguments) {
        CreateCanvasRequest createCanvasRequest = toCreateCanvasRequest(arguments);
        return createCanvasService.create(createCanvasRequest);
    }

    private static CreateCanvasRequest toCreateCanvasRequest(List<String> arguments) {
        checkIfStringsAreTheSameTypeThanObjectFields(CreateCanvasRequest.class, arguments);
        return new CreateCanvasRequest(Integer.parseInt(arguments.get(0)), Integer.parseInt(arguments.get(1)));
    }
}
