package io.davlac.drawingapp.context.output.service;

import io.davlac.drawingapp.context.canvas.model.Canvas;
import io.davlac.drawingapp.context.output.model.RawOutput;

import java.util.stream.IntStream;

import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.LEFT_RIGHT_BORDER_CHAR;
import static io.davlac.drawingapp.context.canvascontent.model.CharCanvas.TOP_BOTTOM_BORDER_CHAR;

public class OutputService {

    private static final StringBuilder strBuilder = new StringBuilder();

    private OutputService() {
    }

    public static void printCanvas(Canvas canvas) {
        RawOutput rawOutput = OutputService.generateRawOutput(canvas);
        rawOutput.print();
    }

    public static RawOutput generateRawOutput(Canvas canvas) {
        String[] rawOutput = new String[canvas.getHeight() + 2];
        IntStream.range(0, canvas.getHeight() + 2).forEach(indexY -> {
            strBuilder.setLength(0);
            if (indexY == 0 || indexY == canvas.getHeight() + 1) {
                generateBorderRow(canvas.getWidth() + 2);
            } else {
                generateCanvasContentRow(canvas.getCanvasContent().getContent()[indexY - 1]);
            }
            rawOutput[indexY] = strBuilder.toString();
        });

        return new RawOutput(rawOutput);
    }

    private static void generateBorderRow(int length) {
        IntStream.range(0, length).forEach(indexX -> strBuilder.append(TOP_BOTTOM_BORDER_CHAR.getChar()));
    }

    private static void generateCanvasContentRow(char[] contentRow) {
        strBuilder.append(LEFT_RIGHT_BORDER_CHAR.getChar());
        IntStream.range(0, contentRow.length)
                .forEach(index -> strBuilder.append(contentRow[index]));
        strBuilder.append(LEFT_RIGHT_BORDER_CHAR.getChar());
    }
}
