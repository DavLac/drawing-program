package io.davlac.drawingapp.context.canvas.model;

import io.davlac.drawingapp.context.canvascontent.model.CanvasContent;

public class Canvas {
    private final Integer width;
    private final Integer height;
    private CanvasContent canvasContent;

    private Canvas(Integer width, Integer height, CanvasContent canvasContent) {
        this.width = width;
        this.height = height;
        this.canvasContent = canvasContent;
    }

    public static Canvas create(Integer width, Integer height) {
        return new Canvas(width, height, CanvasContent.initWithEmptySpaces(width, height));
    }

    public static Canvas empty() {
        return new Canvas(0, 0, CanvasContent.initWithEmptySpaces(0, 0));
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public CanvasContent getCanvasContent() {
        return canvasContent;
    }

    public void setCanvasContent(CanvasContent canvasContent) {
        this.canvasContent = canvasContent;
    }
}
