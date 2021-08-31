package io.davlac.drawingapp.integrationtest.config;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static io.davlac.drawingapp.config.SystemInputUtils.param;
import static io.davlac.drawingapp.integrationtest.config.Constants.CREATE_ACTION;
import static io.davlac.drawingapp.integrationtest.config.Constants.DRAW_RECTANGLE_ACTION;
import static io.davlac.drawingapp.integrationtest.config.Constants.QUIT_ACTION;

public class DrawRectangleTestParameters {
    public static Stream<Arguments> drawRectangleErrorParametersConfig() {
        return Stream.of(
                Arguments.of("Draw rectangle without canvas",
                        List.of(
                                param(DRAW_RECTANGLE_ACTION, "1", "1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR : Canvas content is empty.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle not enough param",
                        List.of(
                                param(CREATE_ACTION, "1", "1", "1"),
                                param(DRAW_RECTANGLE_ACTION, "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_RECTANGLE' - not enough arguments (4)\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle with negative param",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(DRAW_RECTANGLE_ACTION, "1", "-1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: argument validation failed when create a Canvas : '[must be greater than 0]'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle with alpha param",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(DRAW_RECTANGLE_ACTION, "1", "one", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: bad argument type For input string: \"one\"\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle with too long action",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(DRAW_RECTANGLE_ACTION + "long", "1", "1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR : Action should be 1 character 'Rlong'.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle with coordinate X outside canvas",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(DRAW_RECTANGLE_ACTION, "1", "2", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_RECTANGLE' - coordinates are outside the canvas : 'Coordinates{x=1, y=2}'.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle with coordinate Y outside canvas",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(DRAW_RECTANGLE_ACTION, "1", "1", "2", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_RECTANGLE' - coordinates are outside the canvas : 'Coordinates{x=2, y=1}'.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle with coordinates not left-top and bottom-right position by X",
                        List.of(
                                param(CREATE_ACTION, "2", "2"),
                                param(DRAW_RECTANGLE_ACTION, "2", "2", "1", "1"),
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
                                "ERROR: Action 'DRAW_RECTANGLE' - First coordinate 'Coordinates{x=2, y=2}' should be in the top left corner and the second should be in the bottom right corner 'Coordinates{x=1, y=1}'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle with coordinates not left-top and bottom-right position by Y",
                        List.of(
                                param(CREATE_ACTION, "3", "3"),
                                param(DRAW_RECTANGLE_ACTION, "2", "3", "3", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "-----\n" +
                                "|   |\n" +
                                "|   |\n" +
                                "|   |\n" +
                                "-----\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_RECTANGLE' - First coordinate 'Coordinates{x=2, y=3}' should be in the top left corner and the second should be in the bottom right corner 'Coordinates{x=3, y=1}'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
        );
    }

    public static Stream<Arguments> drawRectangleParametersConfig() {
        return Stream.of(
                Arguments.of("Draw rectangle same Coordinates",
                        List.of(
                                param(CREATE_ACTION, "2", "2"),
                                param(DRAW_RECTANGLE_ACTION, "1", "1", "1", "1"),
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
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle same X coordinates",
                        List.of(
                                param(CREATE_ACTION, "4", "2"),
                                param(DRAW_RECTANGLE_ACTION, "1", "1", "4", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "------\n" +
                                "|    |\n" +
                                "|    |\n" +
                                "------\n" +
                                "\n" +
                                "enter command: \n" +
                                "------\n" +
                                "|xxxx|\n" +
                                "|    |\n" +
                                "------\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle same Y coordinates",
                        List.of(
                                param(CREATE_ACTION, "4", "4"),
                                param(DRAW_RECTANGLE_ACTION, "2", "1", "2", "3"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "------\n" +
                                "|    |\n" +
                                "|    |\n" +
                                "|    |\n" +
                                "|    |\n" +
                                "------\n" +
                                "\n" +
                                "enter command: \n" +
                                "------\n" +
                                "| x  |\n" +
                                "| x  |\n" +
                                "| x  |\n" +
                                "|    |\n" +
                                "------\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle in 2*2 canvas",
                        List.of(
                                param(CREATE_ACTION, "2", "2"),
                                param(DRAW_RECTANGLE_ACTION, "1", "1", "2", "2"),
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
                                "|xx|\n" +
                                "|xx|\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle smaller than canvas",
                        List.of(
                                param(CREATE_ACTION, "4", "4"),
                                param(DRAW_RECTANGLE_ACTION, "2", "2", "3", "3"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "------\n" +
                                "|    |\n" +
                                "|    |\n" +
                                "|    |\n" +
                                "|    |\n" +
                                "------\n" +
                                "\n" +
                                "enter command: \n" +
                                "------\n" +
                                "|    |\n" +
                                "| xx |\n" +
                                "| xx |\n" +
                                "|    |\n" +
                                "------\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw 2 rectangles one on each other",
                        List.of(
                                param(CREATE_ACTION, "9", "9"),
                                param(DRAW_RECTANGLE_ACTION, "2", "2", "6", "6"),
                                param(DRAW_RECTANGLE_ACTION, "4", "4", "8", "8"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "-----------\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "-----------\n" +
                                "\n" +
                                "enter command: \n" +
                                "-----------\n" +
                                "|         |\n" +
                                "| xxxxx   |\n" +
                                "| x   x   |\n" +
                                "| x   x   |\n" +
                                "| x   x   |\n" +
                                "| xxxxx   |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "|         |\n" +
                                "-----------\n" +
                                "\n" +
                                "enter command: \n" +
                                "-----------\n" +
                                "|         |\n" +
                                "| xxxxx   |\n" +
                                "| x   x   |\n" +
                                "| x xxxxx |\n" +
                                "| x x x x |\n" +
                                "| xxxxx x |\n" +
                                "|   x   x |\n" +
                                "|   xxxxx |\n" +
                                "|         |\n" +
                                "-----------\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw rectangle spaces between params",
                        List.of(
                                param(CREATE_ACTION, "2", "2"),
                                param("     " + DRAW_RECTANGLE_ACTION + "   ", "    1 ", "  1  ", "   2  ", "  2   "),
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
                                "|xx|\n" +
                                "|xx|\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
                ,
                Arguments.of("Draw line with too much params, just take 4 first",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(DRAW_RECTANGLE_ACTION, "1", "1", "1", "1", "1", "1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "|x|\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
        );
    }
}
