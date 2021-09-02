package io.davlac.drawingapp.context.canvasbody.service.impl;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvasbody.service.CanvasService;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import io.davlac.drawingapp.context.inputcommand.utils.InputCheckUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;

@Service
public class CreateCanvasService implements CanvasService {

    private final ValidatorService validatorService;

    public CreateCanvasService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public boolean validArguments(List<String> arguments) {
        return InputCheckUtils.checkArgumentLength(arguments, CREATE_CANVAS);
    }

    @Override
    public CreateCanvasRequest toCreateCanvasRequest(List<String> arguments) {
        return new CreateCanvasRequest(Integer.parseInt(arguments.get(0)), Integer.parseInt(arguments.get(1)));
    }

    @Override
    public boolean validateCreateCanvasRequest(CreateCanvasRequest createCanvasRequest) {
        return validatorService.validateObjectConstraints(createCanvasRequest);
    }

    @Override
    public Canvas createCanvas(CreateCanvasRequest createCanvasRequest) {
        return Canvas.create(createCanvasRequest.getWidth(), createCanvasRequest.getHeight());
    }
}
