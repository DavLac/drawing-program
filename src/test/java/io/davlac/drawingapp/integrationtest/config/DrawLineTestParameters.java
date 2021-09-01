package io.davlac.drawingapp.integrationtest.config;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static io.davlac.drawingapp.utils.Constants.CREATE_ACTION;
import static io.davlac.drawingapp.utils.Constants.DRAW_LINE_ACTION;
import static io.davlac.drawingapp.utils.Constants.QUIT_ACTION;
import static io.davlac.drawingapp.utils.SystemInputUtils.params;

public class DrawLineTestParameters {
    public static Stream<Arguments> drawLineErrorParametersConfig() {
        return Stream.of(
                Arguments.of("Draw line without canvas",
                        List.of(
                                params(DRAW_LINE_ACTION, "1", "1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR : Canvas content is empty.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw line not enough param",
                        List.of(
                                params(CREATE_ACTION, "1", "1", "1"),
                                params(DRAW_LINE_ACTION, "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_LINE' - not enough arguments (4)\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw line with negative param",
                        List.of(
                                params(CREATE_ACTION, "1", "1"),
                                params(DRAW_LINE_ACTION, "1", "-1", "1", "1"),
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
                Arguments.of("Draw line with alpha param",
                        List.of(
                                params(CREATE_ACTION, "1", "1"),
                                params(DRAW_LINE_ACTION, "1", "one", "1", "1"),
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
                Arguments.of("Draw line with too long action",
                        List.of(
                                params(CREATE_ACTION, "1", "1"),
                                params(DRAW_LINE_ACTION + "long", "1", "1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR : Action should be 1 character 'Llong'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw line with coordinate X outside canvas",
                        List.of(
                                params(CREATE_ACTION, "1", "1"),
                                params(DRAW_LINE_ACTION, "1", "2", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_LINE' - coordinates are outside the canvas : 'Coordinates{x=1, y=2}'.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw line with coordinate Y outside canvas",
                        List.of(
                                params(CREATE_ACTION, "1", "1"),
                                params(DRAW_LINE_ACTION, "1", "1", "2", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_LINE' - coordinates are outside the canvas : 'Coordinates{x=2, y=1}'.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw line with coordinates not aligned",
                        List.of(
                                params(CREATE_ACTION, "2", "2"),
                                params(DRAW_LINE_ACTION, "1", "1", "2", "2"),
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
                                "ERROR: Action 'DRAW_LINE' - 'Coordinates{x=1, y=1}' and 'Coordinates{x=2, y=2}' are not aligned.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
        );
    }

    public static Stream<Arguments> drawLineParametersConfig() {
        return Stream.of(
                Arguments.of("Draw line same Coordinates",
                        List.of(
                                params(CREATE_ACTION, "2", "2"),
                                params(DRAW_LINE_ACTION, "1", "1", "1", "1"),
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
                Arguments.of("Draw horizontal line",
                        List.of(
                                params(CREATE_ACTION, "4", "2"),
                                params(DRAW_LINE_ACTION, "1", "1", "4", "1"),
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
                Arguments.of("Draw horizontal line switch coordinates",
                        List.of(
                                params(CREATE_ACTION, "4", "2"),
                                params(DRAW_LINE_ACTION, "4", "1", "1", "1"),
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
                Arguments.of("Draw vertical line",
                        List.of(
                                params(CREATE_ACTION, "4", "4"),
                                params(DRAW_LINE_ACTION, "2", "1", "2", "3"),
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
                Arguments.of("Draw vertical line switch coordinates",
                        List.of(
                                params(CREATE_ACTION, "4", "4"),
                                params(DRAW_LINE_ACTION, "2", "3", "2", "1"),
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
                Arguments.of("Draw 2 lines one on each other",
                        List.of(
                                params(CREATE_ACTION, "4", "4"),
                                params(DRAW_LINE_ACTION, "2", "1", "2", "3"),
                                params(DRAW_LINE_ACTION, "1", "2", "4", "2"),
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
                                "------\n" +
                                "| x  |\n" +
                                "|xxxx|\n" +
                                "| x  |\n" +
                                "|    |\n" +
                                "------\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw line spaces between params",
                        List.of(
                                params(CREATE_ACTION, "4", "4"),
                                params("     " + DRAW_LINE_ACTION + "   ", "    2 ", "  1  ", "   2  ", "  3   "),
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
                                "### End of Drawing app ###\n")
                ,
                Arguments.of("Draw line with too much params, just take 4 first",
                        List.of(
                                params(CREATE_ACTION, "1", "1"),
                                params(DRAW_LINE_ACTION, "1", "1", "1", "1", "1", "1", "1", "1"),
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
