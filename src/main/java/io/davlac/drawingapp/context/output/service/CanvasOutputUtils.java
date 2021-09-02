package io.davlac.drawingapp.context.output.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.output.model.RawOutput;

import java.util.stream.IntStream;

import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.LEFT_RIGHT_BORDER_CHAR;
import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.TOP_BOTTOM_BORDER_CHAR;

public interface CanvasOutputUtils {

    static RawOutput toRawOutput(Canvas canvas) {
        if (canvas == null || canvas.contentIsEmpty()) {
            return new RawOutput(new String[]{});
        }

        String[] rawOutput = new String[canvas.getHeight() + 2];
        StringBuilder strBuilder = new StringBuilder();

        IntStream.range(0, canvas.getHeight() + 2).forEach(indexY -> {
            strBuilder.setLength(0);
            if (indexY == 0 || indexY == canvas.getHeight() + 1) {
                generateBorderRow(canvas.getWidth() + 2, strBuilder);
            } else {
                generateCanvasContentRow(canvas.getContent()[indexY - 1], strBuilder);
            }
            rawOutput[indexY] = strBuilder.toString();
        });

        return new RawOutput(rawOutput);
    }

    private static void generateBorderRow(int gridWidth, StringBuilder strBuilder) {
        IntStream.range(0, gridWidth).forEach(indexX -> strBuilder.append(TOP_BOTTOM_BORDER_CHAR.getChar()));
    }

    private static void generateCanvasContentRow(char[] contentRow, StringBuilder strBuilder) {
        strBuilder.append(LEFT_RIGHT_BORDER_CHAR.getChar());
        IntStream.range(0, contentRow.length)
                .forEach(index -> strBuilder.append(contentRow[index]));
        strBuilder.append(LEFT_RIGHT_BORDER_CHAR.getChar());
    }
}
