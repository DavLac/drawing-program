package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public interface ProcessCanvasContentService {

    CanvasContent processDrawContent(InputCommand inputCommand, CanvasContent canvasContent);
}
