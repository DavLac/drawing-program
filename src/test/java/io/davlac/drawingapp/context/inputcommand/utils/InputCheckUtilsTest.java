package io.davlac.drawingapp.context.inputcommand.utils;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class InputCheckUtilsTest {

    @Test
    void checkArgumentLength_withEachActionsGoodArgs_shouldReturnTrue() {
        Arrays.stream(ActionCommand.values()).forEach(action -> {
            List<String> args = new ArrayList<>();
            IntStream.range(0, action.getMinArgumentSize()).forEach(i -> args.add(String.valueOf(i)));
            assertTrue(InputCheckUtils.checkArgumentLength(args, action));
        });
    }

    @Test
    void checkArgumentLength_withEachActionsTooMuchArgs_shouldReturnTrue() {
        Arrays.stream(ActionCommand.values()).forEach(action -> {
            List<String> args = new ArrayList<>();
            IntStream.range(0, action.getMinArgumentSize() + 1).forEach(i -> args.add(String.valueOf(i)));
            assertTrue(InputCheckUtils.checkArgumentLength(args, action));
        });
    }

    @Test
    void checkArgumentLength_withEachActionsWithArgsNotEnoughArgs_shouldThrowError() {
        Arrays.stream(ActionCommand.values())
                .filter(action -> action.getMinArgumentSize() != 0)
                .forEach(action -> {
                    try {
                        assertFalse(InputCheckUtils.checkArgumentLength(List.of(), action));
                    } catch (IllegalArgumentException ex) {
                        assertEquals(String.format("ERROR: Action '%s' - not enough arguments (%d)",
                                action, action.getMinArgumentSize()), ex.getMessage());
                    }
                });
    }
}
