package io.davlac.drawingapp.context.canvas.model;

import javax.validation.constraints.Positive;

public final class CreateCanvasRequest {
    @Positive
    private final Integer width;
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
