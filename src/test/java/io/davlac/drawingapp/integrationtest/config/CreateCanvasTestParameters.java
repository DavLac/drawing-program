package io.davlac.drawingapp.integrationtest.config;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static io.davlac.drawingapp.config.SystemInputUtils.param;
import static io.davlac.drawingapp.integrationtest.config.Constants.CREATE_ACTION;
import static io.davlac.drawingapp.integrationtest.config.Constants.QUIT_ACTION;

public class CreateCanvasTestParameters {
    public static Stream<Arguments> createCanvasErrorParametersConfig() {
        return Stream.of(
                Arguments.of("Create with width and height to 0",
                        List.of(
                                param(CREATE_ACTION, "0", "0"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: argument validation failed when create a Canvas : '[must be greater than 0, must be greater than 0]'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Create with one param",
                        List.of(
                                param(CREATE_ACTION, "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: Action 'CREATE_CANVAS' - not enough arguments (2)\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Create with negative param",
                        List.of(
                                param(CREATE_ACTION, "1", "-1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: argument validation failed when create a Canvas : '[must be greater than 0]'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Create with not numeric param",
                        List.of(
                                param(CREATE_ACTION, "1", "a"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: bad argument type For input string: \"a\"\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Create with too long action",
                        List.of(
                                param(CREATE_ACTION + "create", "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR : Action should be 1 character 'Ccreate'.\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Create with too big param",
                        List.of(
                                param(CREATE_ACTION, "5001", "5001"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "ERROR: argument validation failed when create a Canvas : '[must be less than or equal to 5000, must be less than or equal to 5000]'\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
        );
    }

    public static Stream<Arguments> createCanvasParametersConfig() {
        return Stream.of(
                Arguments.of("Create smallest canvas",
                        List.of(
                                param(CREATE_ACTION, "1", "1"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Create with good param",
                        List.of(
                                param(CREATE_ACTION, "3", "4"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "-----\n" +
                                "|   |\n" +
                                "|   |\n" +
                                "|   |\n" +
                                "|   |\n" +
                                "-----\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Create spaces between params",
                        List.of(
                                param("       " + CREATE_ACTION, "       1        ", "               3        "),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "---\n" +
                                "| |\n" +
                                "| |\n" +
                                "| |\n" +
                                "---\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
                ,
                Arguments.of("Create with too much params, just take 2 first",
                        List.of(
                                param(CREATE_ACTION, "3", "4", "4", "4", "4", "4"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "-----\n" +
                                "|   |\n" +
                                "|   |\n" +
                                "|   |\n" +
                                "|   |\n" +
                                "-----\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
        );
    }
}
