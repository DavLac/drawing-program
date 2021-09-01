package io.davlac.drawingapp.context.output.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.output.model.RawOutput;

public interface OutputService {

    RawOutput toRawOutput(Canvas canvas);

}
