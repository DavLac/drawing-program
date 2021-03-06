package io.davlac.drawingapp.integrationtest.config;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static io.davlac.drawingapp.utils.Constants.CREATE_ACTION;
import static io.davlac.drawingapp.utils.Constants.DRAW_LINE_ACTION;
import static io.davlac.drawingapp.utils.Constants.DRAW_RECTANGLE_ACTION;
import static io.davlac.drawingapp.utils.Constants.FILL_ACTION;
import static io.davlac.drawingapp.utils.Constants.QUIT_ACTION;
import static io.davlac.drawingapp.utils.SystemInputUtils.params;

public class DrawAllTestParameters {

    public static Stream<Arguments> drawAllParametersConfig() {
        return Stream.of(
                Arguments.of("Draw 1 line, 1 rectangle and fill",
                        List.of(
                                params(CREATE_ACTION, "9", "9"),
                                params(DRAW_LINE_ACTION, "1", "4", "9", "4"),
                                params(DRAW_RECTANGLE_ACTION, "2", "2", "6", "6"),
                                params(FILL_ACTION, "8", "8", "@"),
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
                                "|         |\n" +
                                "|         |\n" +
                                "|xxxxxxxxx|\n" +
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
                                "|xxxxxxxxx|\n" +
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
                                "|xxxxxxxxx|\n" +
                                "|@x   x@@@|\n" +
                                "|@xxxxx@@@|\n" +
                                "|@@@@@@@@@|\n" +
                                "|@@@@@@@@@|\n" +
                                "|@@@@@@@@@|\n" +
                                "-----------\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n"),
                Arguments.of("Draw like example problem",
                        List.of(
                                params(CREATE_ACTION, "20", "4"),
                                params(DRAW_LINE_ACTION, "1", "2", "6", "2"),
                                params(DRAW_LINE_ACTION, "6", "3", "6", "4"),
                                params(DRAW_RECTANGLE_ACTION, "14", "1", "18", "3"),
                                params(FILL_ACTION, "10", "2", "o"),
                                QUIT_ACTION),
                        "### Welcome on Drawing app ###\n" +
                                "\n" +
                                "enter command: \n" +
                                "----------------------\n" +
                                "|                    |\n" +
                                "|                    |\n" +
                                "|                    |\n" +
                                "|                    |\n" +
                                "----------------------\n" +
                                "\n" +
                                "enter command: \n" +
                                "----------------------\n" +
                                "|                    |\n" +
                                "|xxxxxx              |\n" +
                                "|                    |\n" +
                                "|                    |\n" +
                                "----------------------\n" +
                                "\n" +
                                "enter command: \n" +
                                "----------------------\n" +
                                "|                    |\n" +
                                "|xxxxxx              |\n" +
                                "|     x              |\n" +
                                "|     x              |\n" +
                                "----------------------\n" +
                                "\n" +
                                "enter command: \n" +
                                "----------------------\n" +
                                "|             xxxxx  |\n" +
                                "|xxxxxx       x   x  |\n" +
                                "|     x       xxxxx  |\n" +
                                "|     x              |\n" +
                                "----------------------\n" +
                                "\n" +
                                "enter command: \n" +
                                "----------------------\n" +
                                "|oooooooooooooxxxxxoo|\n" +
                                "|xxxxxxooooooox   xoo|\n" +
                                "|     xoooooooxxxxxoo|\n" +
                                "|     xoooooooooooooo|\n" +
                                "----------------------\n" +
                                "\n" +
                                "enter command: \n" +
                                "\n" +
                                "### End of Drawing app ###\n")
        );
    }
}
