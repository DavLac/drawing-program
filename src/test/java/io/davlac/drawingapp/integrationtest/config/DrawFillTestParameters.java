package io.davlac.drawingapp.integrationtest.config;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static io.davlac.drawingapp.config.SystemInputUtils.param;
import static io.davlac.drawingapp.integrationtest.config.Constants.CREATE_ACTION;
import static io.davlac.drawingapp.integrationtest.config.Constants.DRAW_RECTANGLE_ACTION;
import static io.davlac.drawingapp.integrationtest.config.Constants.FILL_ACTION;
import static io.davlac.drawingapp.integrationtest.config.Constants.QUIT_ACTION;

public class DrawFillTestParameters {
    public static Stream<Arguments> drawFillErrorParametersConfig() {
        return Stream.of(
                Arguments.of("Fill without canvas",
                        List.of(
                                param(FILL_ACTION, "1", "1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR : Canvas content is empty.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill not enough param",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(FILL_ACTION, "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_BUCKET_FILL' - not enough arguments (3)\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill with negative param",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(FILL_ACTION, "1", "-1", "o"),
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
                Arguments.of("Fill all negative param",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(FILL_ACTION, "-1", "-1", "o"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: argument validation failed when create a Canvas : '[must be greater than 0, must be greater than 0]'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill with alpha param on coordinate",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(FILL_ACTION, "1", "one", "o"),
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
                Arguments.of("Fill with too long action",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(FILL_ACTION + "long", "1", "1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR : Action should be 1 character 'Blong'.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill with coordinate outside canvas",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(FILL_ACTION, "1", "2", "o"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'DRAW_BUCKET_FILL' - coordinates are outside the canvas : 'Coordinates{x=1, y=2}'.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
        );
    }

    public static Stream<Arguments> drawFillParametersConfig() {
        return Stream.of(
                Arguments.of("Fill action",
                        List.of(
                                param(CREATE_ACTION, "2", "2"),
                                param(FILL_ACTION, "1", "1", "£"),
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
                                "|££|\n" +
                                "|££|\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill same color than requested",
                        List.of(
                                param(CREATE_ACTION, "2", "2"),
                                param(FILL_ACTION, "1", "1", "$"),
                                param(FILL_ACTION, "2", "2", "$"),
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
                                "|$$|\n" +
                                "|$$|\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "----\n" +
                                "|$$|\n" +
                                "|$$|\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill with blank char",
                        List.of(
                                param(CREATE_ACTION, "2", "2"),
                                param(DRAW_RECTANGLE_ACTION, "1", "1", "2", "2"),
                                param(FILL_ACTION, "1", "1", "blank"),
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
                                "----\n" +
                                "|  |\n" +
                                "|  |\n" +
                                "----\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill a rectangle",
                        List.of(
                                param(CREATE_ACTION, "5", "5"),
                                param(DRAW_RECTANGLE_ACTION, "2", "2", "4", "4"),
                                param(FILL_ACTION, "2", "2", "R"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "-------\n" +
                                "|     |\n" +
                                "|     |\n" +
                                "|     |\n" +
                                "|     |\n" +
                                "|     |\n" +
                                "-------\n" +
                                "\n" +
                                "enter command: \n" +
                                "-------\n" +
                                "|     |\n" +
                                "| xxx |\n" +
                                "| x x |\n" +
                                "| xxx |\n" +
                                "|     |\n" +
                                "-------\n" +
                                "\n" +
                                "enter command: \n" +
                                "-------\n" +
                                "|     |\n" +
                                "| RRR |\n" +
                                "| R R |\n" +
                                "| RRR |\n" +
                                "|     |\n" +
                                "-------\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill with color param too long - take first string char",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(FILL_ACTION, "1", "1", "long"),
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
                                "|l|\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw line with too much params, just take 3 first",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                param(FILL_ACTION, "1", "1", "a", "1", "1", "1", "1", "1"),
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
                                "|a|\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
        );
    }
}
