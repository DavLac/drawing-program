package io.davlac.drawingapp.integrationtest;

import io.davlac.drawingapp.DrawingApplication;
import io.davlac.drawingapp.integrationtest.config.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static io.davlac.drawingapp.config.SystemInputUtils.setUserInput;
import static io.davlac.drawingapp.config.SystemOutputUtils.formatExpectedResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DrawingApplicationQuitIntTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private InputStream sysInBackup;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        sysInBackup = System.in;
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(sysInBackup);
    }

    @Test
    void main_withNullInput_shouldThrowError() throws Exception {
        setUserInput(List.of(" ", Constants.QUIT_ACTION));

        String expectedResult = "### Welcome on Drawing app ###\n" +
                "\n" +
                "enter command: \n" +
                "ERROR : Command is empty.\n" +
                "\n" +
                "enter command: \n" +
                "\n" +
                "### End of Drawing app ###\n";

        String outputResult = tapSystemOut(() -> DrawingApplication.main(new String[]{}));
        assertEquals(formatExpectedResult(expectedResult), outputResult);
    }

    @Test
    void main_withEmptyInput_shouldThrowError() throws Exception {
        setUserInput(List.of(" ", Constants.QUIT_ACTION));

        String expectedResult = "### Welcome on Drawing app ###\n" +
                "\n" +
                "enter command: \n" +
                "ERROR : Command is empty.\n" +
                "\n" +
                "enter command: \n" +
                "\n" +
                "### End of Drawing app ###\n";

        String outputResult = tapSystemOut(() -> DrawingApplication.main(new String[]{}));
        assertEquals(formatExpectedResult(expectedResult), outputResult);
    }

    @Test
    void main_withUnknownAction_shouldThrowError() throws Exception {
        setUserInput(List.of("b", Constants.QUIT_ACTION));

        String expectedResult = "### Welcome on Drawing app ###\n" +
                "\n" +
                "enter command: \n" +
                "ERROR : Action not found with command = 'b'.\n" +
                "\n" +
                "enter command: \n" +
                "\n" +
                "### End of Drawing app ###\n";

        String outputResult = tapSystemOut(() -> DrawingApplication.main(new String[]{}));
        assertEquals(formatExpectedResult(expectedResult), outputResult);
    }

    @Test
    void main_withJustQuitAction_shouldNotDrawAnyCanvasAndQuit() throws Exception {
        setUserInput(List.of(Constants.QUIT_ACTION));

        String expectedResult = "### Welcome on Drawing app ###\n" +
                "\n" +
                "enter command: \n" +
                "\n" +
                "### End of Drawing app ###\n";

        String outputResult = tapSystemOut(() -> DrawingApplication.main(new String[]{}));
        assertEquals(formatExpectedResult(expectedResult), outputResult);
    }
}
