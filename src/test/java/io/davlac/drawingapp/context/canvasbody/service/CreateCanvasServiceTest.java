package io.davlac.drawingapp.context.canvasbody.service;

import io.davlac.drawingapp.context.canvasbody.model.Canvas;
import io.davlac.drawingapp.context.canvasbody.model.CreateCanvasRequest;
import io.davlac.drawingapp.context.canvasbody.service.impl.CreateCanvasService;
import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCanvasServiceTest {

    private static final char[][] CANVAS_CONTENT = {
            {' '},
            {' '}
    };
    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;
    private static final String HEIGHT_STR = String.valueOf(HEIGHT);
    private static final String WIDTH_STR = String.valueOf(WIDTH);

    @Mock
    private ValidatorService javaxValidatorService;

    @InjectMocks
    private CreateCanvasService createCanvasService;

    @Test
    void validArguments_withCreateArgs_shouldReturnTrue() {
        List<String> args = List.of(WIDTH_STR, HEIGHT_STR);
        assertTrue(createCanvasService.validArguments(args));
    }

    @Test
    void validArguments_with3Args_shouldReturnTrue() {
        List<String> args = List.of(WIDTH_STR, HEIGHT_STR, HEIGHT_STR);
        assertTrue(createCanvasService.validArguments(args));
    }

    @Test
    void validArguments_withEmpty_shouldThrowError() {
        try {
            createCanvasService.validArguments(List.of());
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'CREATE_CANVAS' - not enough arguments (2)", ex.getMessage());
        }
    }

    @Test
    void validArguments_withNull_shouldThrowError() {
        try {
            createCanvasService.validArguments(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: Action 'CREATE_CANVAS' - not enough arguments (2)", ex.getMessage());
        }
    }

    @Test
    void toCreateCanvasRequest_with2Args_shouldCreateRequest() {
        List<String> args = List.of(WIDTH_STR, HEIGHT_STR);
        CreateCanvasRequest createCanvasRequest = createCanvasService.toCreateCanvasRequest(args);
        assertEquals(WIDTH, createCanvasRequest.getWidth());
        assertEquals(HEIGHT, createCanvasRequest.getHeight());
    }

    @Test
    void toCreateCanvasRequest_with3Args_shouldCreateRequest() {
        List<String> args = List.of(WIDTH_STR, HEIGHT_STR, HEIGHT_STR);
        CreateCanvasRequest createCanvasRequest = createCanvasService.toCreateCanvasRequest(args);
        assertEquals(WIDTH, createCanvasRequest.getWidth());
        assertEquals(HEIGHT, createCanvasRequest.getHeight());
    }

    @Test
    void validateCreateCanvasRequest_withValidRequest_shouldValidateRequest() {
        CreateCanvasRequest createCanvasRequest = new CreateCanvasRequest(WIDTH, HEIGHT);
        when(javaxValidatorService.validateObjectConstraints(createCanvasRequest)).thenReturn(true);
        assertTrue(createCanvasService.validateCreateCanvasRequest(createCanvasRequest));
    }

    @Test
    void validateCreateCanvasRequest_withNotValidRequest_shouldThrowError() {
        CreateCanvasRequest createCanvasRequest = new CreateCanvasRequest(WIDTH, HEIGHT);
        when(javaxValidatorService.validateObjectConstraints(createCanvasRequest)).thenThrow(new IllegalArgumentException("error"));

        try {
            createCanvasService.validateCreateCanvasRequest(createCanvasRequest);
        } catch (IllegalArgumentException ex) {
            assertEquals("error", ex.getMessage());
        }
    }

    @Test
    void createCanvas_withGoodArgs_shouldCreateCanvas() {
        CreateCanvasRequest createCanvasRequest = new CreateCanvasRequest(WIDTH, HEIGHT);
        Canvas canvas = createCanvasService.createCanvas(createCanvasRequest);
        assertEquals(WIDTH, canvas.getWidth());
        assertEquals(HEIGHT, canvas.getHeight());
        assertArrayEquals(CANVAS_CONTENT, canvas.getContent());
    }
}
