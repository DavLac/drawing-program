package io.davlac.drawingapp.integrationtest.config;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static io.davlac.drawingapp.context.inputcommand.model.CommandKey.Key.BLANK;
import static io.davlac.drawingapp.utils.Constants.CREATE_ACTION;
import static io.davlac.drawingapp.utils.Constants.DRAW_RECTANGLE_ACTION;
import static io.davlac.drawingapp.utils.Constants.FILL_ACTION;
import static io.davlac.drawingapp.utils.Constants.QUIT_ACTION;
import static io.davlac.drawingapp.utils.SystemInputUtils.params;

public class DrawFillTestParameters {
    public static Stream<Arguments> drawFillErrorParametersConfig() {
        return Stream.of(
                Arguments.of("Fill without canvas",
                        List.of(
                                params(FILL_ACTION, "1", "1", "1", "1"),
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
                                params(CREATE_ACTION, "1", "1"),
                                params(FILL_ACTION, "1"),
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
                                params(CREATE_ACTION, "1", "1"),
                                params(FILL_ACTION, "1", "-1", "o"),
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
                                params(CREATE_ACTION, "1", "1"),
                                params(FILL_ACTION, "-1", "-1", "o"),
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
                                params(CREATE_ACTION, "1", "1"),
                                params(FILL_ACTION, "1", "one", "o"),
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
                                params(CREATE_ACTION, "1", "1"),
                                params(FILL_ACTION + "long", "1", "1", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR : Action should be 1 character 'Blong'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Fill with coordinate outside canvas",
                        List.of(
                                params(CREATE_ACTION, "1", "1"),
                                params(FILL_ACTION, "1", "2", "o"),
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
                                params(CREATE_ACTION, "2", "2"),
                                params(FILL_ACTION, "1", "1", "£"),
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
                                params(CREATE_ACTION, "2", "2"),
                                params(FILL_ACTION, "1", "1", "$"),
                                params(FILL_ACTION, "2", "2", "$"),
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
                                params(CREATE_ACTION, "2", "2"),
                                params(DRAW_RECTANGLE_ACTION, "1", "1", "2", "2"),
                                params(FILL_ACTION, "1", "1", BLANK.getValue()),
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
                                params(CREATE_ACTION, "5", "5"),
                                params(DRAW_RECTANGLE_ACTION, "2", "2", "4", "4"),
                                params(FILL_ACTION, "2", "2", "R"),
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
                                params(CREATE_ACTION, "1", "1"),
                                params(FILL_ACTION, "1", "1", "long"),
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
                                params(CREATE_ACTION, "1", "1"),
                                params(FILL_ACTION, "1", "1", "a", "1", "1", "1", "1", "1"),
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
