package io.davlac.drawingapp.context.canvasbody.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

import static io.davlac.drawingapp.utils.SystemOutputUtils.formatExpectedResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CanvasTest {

    private static final int WIDTH = 3;
    private static final int HEIGHT = 2;
    public static final char BLANK = ' ';
    private static final char[][] CANVAS_CONTENT = {
            {'1', '2', '3'},
            {'a', 'b', 'c'}
    };
    private static final char[][] CANVAS_CONTENT_2 = {
            {'1', '2'}
    };

    @Test
    void create_withPositiveParams_shouldReturnCanvasWithBlankContent() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        assertEquals(WIDTH, canvas.getContent()[0].length);
        assertEquals(HEIGHT, canvas.getContent().length);

        IntStream.range(0, canvas.getContent().length).forEach(row -> {
            IntStream.range(0, canvas.getContent()[0].length).forEach(col -> {
                assertEquals(BLANK, canvas.getContent()[row][col]);
            });
        });
    }

    @Test
    void getHeight_withContent_shouldReturnHeight() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        assertEquals(HEIGHT, canvas.getHeight());
    }

    @Test
    void getWidth_withContent_shouldReturnWidth() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        assertEquals(WIDTH, canvas.getWidth());
    }

    @Test
    void getHeight_withEmptyContent_shouldReturnZero() {
        Canvas canvas = Canvas.empty();
        assertEquals(0, canvas.getHeight());
    }

    @Test
    void getWidth_withEmptyContent_shouldReturnZero() {
        Canvas canvas = Canvas.empty();
        assertEquals(0, canvas.getWidth());
    }

    @Test
    void contentIsEmpty_withContent_shouldReturnFalse() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        assertFalse(canvas.contentIsEmpty());
    }

    @Test
    void contentIsEmpty_withEmptyContent_shouldReturnTrue() {
        Canvas canvas = Canvas.empty();
        assertTrue(canvas.contentIsEmpty());
    }

    @Test
    void empty_shouldReturnEmptyContent() {
        Canvas canvas = Canvas.empty();
        assertEquals(0, canvas.getContent().length);
    }

    @Test
    void setContent_withSameSizeContent_shouldSetCanvasContent() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        canvas.setContent(CANVAS_CONTENT);

        IntStream.range(0, canvas.getHeight()).forEach(row -> {
            IntStream.range(0, canvas.getWidth()).forEach(col -> {
                assertEquals(CANVAS_CONTENT[row][col], canvas.getContent()[row][col]);
            });
        });
    }

    @Test
    void setContent_withEmptyContent_shouldThrowError() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        try{
            canvas.setContent(new char[0][0]);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Cannot modify canvas content because the size are not the same", ex.getMessage());
        }
    }

    @Test
    void setContent_withNullContent_shouldThrowError() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        try{
            canvas.setContent(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Cannot modify canvas content because the size are not the same", ex.getMessage());
        }
    }

    @Test
    void setContent_withDifferentSizeContent_shouldThrowError() {
        Canvas canvas = Canvas.create(WIDTH, HEIGHT);
        try{
            canvas.setContent(CANVAS_CONTENT_2);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Cannot modify canvas content because the size are not the same", ex.getMessage());
        }
    }
}
