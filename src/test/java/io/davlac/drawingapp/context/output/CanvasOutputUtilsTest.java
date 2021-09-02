package io.davlac.drawingapp.context.output;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.output.model.RawOutput;
import io.davlac.drawingapp.context.output.service.CanvasOutputUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CanvasOutputUtilsTest {

    private static final char[][] CANVAS_CONTENT = {
            {'1', '2', '3'},
            {'a', 'b', 'c'}
    };
    private static final int WIDTH = CANVAS_CONTENT[0].length;
    private static final int HEIGHT = CANVAS_CONTENT.length;
    private static final String[] CANVAS_CONTENT_EXPECTED = {
            "-----",
            "|123|",
            "|abc|",
            "-----"
    };

    @Test
    void toRawOutput_withCanvas_shouldReturnRawOuputByRow() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        canvas.setContent(CANVAS_CONTENT);
        RawOutput rawOutput = CanvasOutputUtils.toRawOutput(canvas);

        IntStream.range(0, CANVAS_CONTENT_EXPECTED.length)
                .forEach(index -> assertEquals(CANVAS_CONTENT_EXPECTED[index], rawOutput.getContent()[index]));
    }

    @Test
    void toRawOutput_withEmptyCanvas_shouldReturnEmptyRawOuput() {
        Canvas canvas = Canvas.empty();
        RawOutput rawOutput = CanvasOutputUtils.toRawOutput(canvas);

        assertEquals(0, rawOutput.getContent().length);
    }
}
