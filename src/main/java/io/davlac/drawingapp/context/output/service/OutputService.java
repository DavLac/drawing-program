package io.davlac.drawingapp.context.output.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.output.model.RawOutput;

public interface OutputService {

    RawOutput toRawOutput(Canvas canvas);

}
