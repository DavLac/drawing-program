package io.davlac.drawingapp.context.canvasbody.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

public final class CreateCanvasRequest {
    @Max(5000)
    @Positive
    private final Integer width;
    @Max(5000)
    @Positive
    private final Integer height;

    public CreateCanvasRequest(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }
}
