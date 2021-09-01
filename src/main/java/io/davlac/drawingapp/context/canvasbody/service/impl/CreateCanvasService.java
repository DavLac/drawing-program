package io.davlac.drawingapp.context.canvasbody.service.impl;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvasbody.service.CanvasService;
import io.davlac.drawingapp.context.canvascontent.utils.ValidatorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

import static io.davlac.drawingapp.context.canvascontent.utils.ValidatorService.checkArgumentLength;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;

@Service
@Validated
public class CreateCanvasService implements CanvasService {

    private final ValidatorService validatorService;

    public CreateCanvasService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public void validateArguments(List<String> arguments) {
        checkArgumentLength(arguments, CREATE_CANVAS);
    }

    @Override
    public CreateCanvasRequest toCreateCanvasRequest(List<String> arguments) {
        return new CreateCanvasRequest(Integer.parseInt(arguments.get(0)), Integer.parseInt(arguments.get(1)));
    }

    @Override
    public void validateCreateCanvasRequest(@Valid CreateCanvasRequest createCanvasRequest) {
        validatorService.validateObjectConstraints(createCanvasRequest);
    }

    @Override
    public Canvas createCanvas(CreateCanvasRequest createCanvasRequest) {
        return Canvas.create(createCanvasRequest.getWidth(), createCanvasRequest.getHeight());
    }
}
