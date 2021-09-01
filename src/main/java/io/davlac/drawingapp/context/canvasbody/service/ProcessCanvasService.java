package io.davlac.drawingapp.context.canvasbody.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public interface ProcessCanvasService {

    void checkActionIsValid(ActionCommand actionCommand);

    Canvas generateCanvas(InputCommand inputCommand);

}
