package io.davlac.drawingapp.context.canvas.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;

import java.util.List;

public interface CanvasGenericArgumentsService {
    Canvas create(List<String> arguments);
}
