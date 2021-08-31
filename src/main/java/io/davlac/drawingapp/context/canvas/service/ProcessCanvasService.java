package io.davlac.drawingapp.context.canvas.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public interface ProcessCanvasService {

    void checkActionIsValid(ActionCommand actionCommand);

    Canvas generateCanvas(InputCommand inputCommand);

}
