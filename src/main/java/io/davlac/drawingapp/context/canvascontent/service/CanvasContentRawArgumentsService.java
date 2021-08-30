package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;

import java.util.List;

public interface CanvasContentRawArgumentsService {
    CanvasContent draw(List<String> arguments, CanvasContent canvasContent);
}
