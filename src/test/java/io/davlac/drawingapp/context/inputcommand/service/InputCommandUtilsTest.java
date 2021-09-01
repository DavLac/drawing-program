package io.davlac.drawingapp.context.inputcommand.service;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import io.davlac.drawingapp.context.inputcommand.model.InputCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.davlac.drawingapp.utils.Constants.DRAW_LINE_ACTION;
import static io.davlac.drawingapp.utils.Constants.QUIT_ACTION;
import static io.davlac.drawingapp.utils.SystemInputUtils.buildParams;
import static io.davlac.drawingapp.utils.SystemInputUtils.setUserInput;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class InputCommandUtilsTest {

    private static final String ARG1 = "arg";
    private static final String BLANKS = "   ";
    private static final String ARG1_WITH_SPACES_AROUND = BLANKS + ARG1 + BLANKS;
    private static final String ARG2 = "arg2";
    private static final String ARG2_WITH_SPACES_AROUND = BLANKS + ARG2 + BLANKS;
    private static final String BLANK_ARG = " ";

    @Test
    void toRawInputCommand_withOneArg_shouldReturnTheArg() {
        String[] result = InputCommandUtils.toRawInputCommand(setUserInput(ARG1));

        assertEquals(1, result.length);
        assertEquals(ARG1, result[0]);
    }

    @Test
    void toRawInputCommand_with2Args_shouldReturn2Args() {
        String[] result = InputCommandUtils.toRawInputCommand(setUserInput(buildParams(ARG1, ARG2)));

        assertEquals(2, result.length);
        assertEquals(ARG1, result[0]);
        assertEquals(ARG2, result[1]);
    }

    @Test
    void toRawInputCommand_withEmptyArg_shouldThrowError() {
        try {
            InputCommandUtils.toRawInputCommand(setUserInput(BLANK_ARG));
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Command is empty", ex.getMessage());
        }
    }

    @Test
    void toRawInputCommand_withSpaceAroundArgs_shouldReturnArgsTrimed() {
        String[] result = InputCommandUtils.toRawInputCommand(setUserInput(
                buildParams(ARG1_WITH_SPACES_AROUND, ARG2_WITH_SPACES_AROUND)));

        assertEquals(2, result.length);
        assertEquals(ARG1, result[0]);
        assertEquals(ARG2, result[1]);
    }

    @Test
    void toRawInputCommand_withSpaceBetweenArgs_shouldReturnArgsWithoutEmptyArg() {
        String[] result = InputCommandUtils.toRawInputCommand(setUserInput(ARG1 + BLANKS + ARG2));

        assertEquals(2, result.length);
        assertEquals(ARG1, result[0]);
        assertEquals(ARG2, result[1]);
    }

    @Test
    void toInputCommand_withGoodArgs_shouldReturnInputCommandWithArgs() {
        String[] rawInputCommand = {QUIT_ACTION, ARG1, ARG2};
        InputCommand inputCommand = InputCommandUtils.toInputCommand(rawInputCommand);

        assertEquals(ActionCommand.QUIT, inputCommand.getAction());
        assertEquals(2, inputCommand.getArguments().size());
        assertEquals(ARG1, inputCommand.getArguments().get(0));
        assertEquals(ARG2, inputCommand.getArguments().get(1));
    }

    @Test
    void toInputCommand_withActionAndNoArgs_shouldReturnInputCommandWithNoArgs() {
        String[] rawInputCommand = {DRAW_LINE_ACTION};
        InputCommand inputCommand = InputCommandUtils.toInputCommand(rawInputCommand);

        assertEquals(ActionCommand.DRAW_LINE, inputCommand.getAction());
        assertEquals(0, inputCommand.getArguments().size());
    }

    @Test
    void toInputCommand_withActionTooLong_shouldReturnAnError() {
        try {
            String[] rawInputCommand = {ARG1};
            InputCommandUtils.toInputCommand(rawInputCommand);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Action should be 1 character '" + ARG1 + "'", ex.getMessage());
        }
    }

    @Test
    void toInputCommand_withUnknownAction_shouldReturnAnError() {
        try {
            String[] rawInputCommand = {"D"};
            InputCommandUtils.toInputCommand(rawInputCommand);
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR : Action not found with command = 'D'", ex.getMessage());
        }
    }
}
