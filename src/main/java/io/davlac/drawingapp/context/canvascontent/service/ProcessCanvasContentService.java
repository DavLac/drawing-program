package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.inputcommand.model.InputCommand;

public interface ProcessCanvasContentService {

    char[][] processDrawContent(InputCommand inputCommand, char[][] canvasContent);

}
