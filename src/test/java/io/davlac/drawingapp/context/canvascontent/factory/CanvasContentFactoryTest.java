package io.davlac.drawingapp.context.canvascontent.factory;

import io.davlac.drawingapp.context.canvascontent.service.CanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawBucketFillCanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawLineCanvasContentService;
import io.davlac.drawingapp.context.canvascontent.service.impl.canvascontent.DrawRectangleCanvasContentService;
import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.CommandType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_BUCKET_FILL;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_LINE;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.DRAW_RECTANGLE;
import static io.davlac.drawingapp.context.inputcommand.model.CommandType.CANVAS_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CanvasContentFactoryTest {

    @InjectMocks
    private CanvasContentFactory canvasContentFactory;

    @Test
    void getAction_withDrawLine_shouldReturnDrawLineAction() {
        CanvasContentService canvasContentService = canvasContentFactory.getAction(DRAW_LINE);
        assertTrue(canvasContentService instanceof DrawLineCanvasContentService);
    }

    @Test
    void getAction_withDrawRectangle_shouldReturnDrawRectangleAction() {
        CanvasContentService canvasContentService = canvasContentFactory.getAction(DRAW_RECTANGLE);
        assertTrue(canvasContentService instanceof DrawRectangleCanvasContentService);
    }

    @Test
    void getAction_withDrawFill_shouldReturnDrawFillAction() {
        CanvasContentService canvasContentService = canvasContentFactory.getAction(DRAW_BUCKET_FILL);
        assertTrue(canvasContentService instanceof DrawBucketFillCanvasContentService);
    }

    @Test
    void getAction_withNotCanvasContent_shouldReturnThrowError() {
        CanvasContentService canvasContentService = canvasContentFactory.getAction(DRAW_BUCKET_FILL);
        assertTrue(canvasContentService instanceof DrawBucketFillCanvasContentService);

        Arrays.stream(ActionCommand.values())
                .filter(action -> action.getCommandType() != CommandType.CANVAS_CONTENT)
                .forEach(action -> {
                    try {
                        canvasContentFactory.getAction(action);
                    } catch (IllegalArgumentException ex) {
                        assertEquals(String.format("ERROR : Action command '%s' is not implemented for type = '%s'",
                                action, CANVAS_CONTENT), ex.getMessage());
                    }
                });
    }
}
