package io.davlac.drawingapp.integrationtest.config;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static io.davlac.drawingapp.utils.Constants.CREATE_ACTION;
import static io.davlac.drawingapp.utils.Constants.DRAW_LINE_ACTION;
import static io.davlac.drawingapp.utils.Constants.QUIT_ACTION;
import static io.davlac.drawingapp.utils.Constants.UNDO_ACTION;
import static io.davlac.drawingapp.utils.SystemInputUtils.params;

public class UndoTestParameters {
    public static Stream<Arguments> undoParametersConfig() {
        return Stream.of(
                Arguments.of("Draw line same Coordinates",
                        List.of(
                                params(CREATE_ACTION, "2", "2"),
                                params(DRAW_LINE_ACTION, "1", "1", "1", "1"),
                                UNDO_ACTION,
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "----\n" +
                                "|  |\n" +
                                "|  |\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "----\n" +
                                "|x |\n" +
                                "|  |\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "----\n" +
                                "|  |\n" +
                                "|  |\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")

                );
    }
}
