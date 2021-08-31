package io.davlac.drawingapp.context.inputcommand.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public interface CommandTypeService {

    Canvas processInputCommand(InputCommand inputCommand, Canvas canvas);

}
