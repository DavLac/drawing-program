package io.davlac.drawingapp.context.inputcommand.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.CREATE_CANVAS;
import static io.davlac.drawingapp.context.inputcommand.model.ActionCommand.QUIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ActionCommandTest {

    @Test
    void getActionByCommand_withCreateCharAction_shouldReturnCreateAction() {
        ActionCommand actionCommand = ActionCommand.getActionByCommand('C');
        assertEquals(CREATE_CANVAS, actionCommand);
    }

    @Test
    void getActionByCommand_withQuitCharAction_shouldReturnQuitAction() {
        ActionCommand actionCommand = ActionCommand.getActionByCommand('Q');
        assertEquals(QUIT, actionCommand);
    }

    @Test
    void getActionByCommand_withUnknownAction_shouldReturnAnError() {
        try {
            ActionCommand.getActionByCommand('$');
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Action not found with command = '$'", ex.getMessage());
        }
    }
}
