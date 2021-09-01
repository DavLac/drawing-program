package io.davlac.drawingapp.context.inputcommand.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ActionCommandTest {

    @Test
    void getActionByCommand_withStreamOnAllValues_shouldReturnAssociatedAction() {
        Arrays.stream(ActionCommand.values()).forEach(action -> {
            ActionCommand actionCommandFound = ActionCommand.getActionByCommand(action.getCommand());
            assertEquals(action, actionCommandFound);
        });
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
