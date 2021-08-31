package io.davlac.drawingapp.integrationtest;

import io.davlac.drawingapp.DrawingApplication;
import io.davlac.drawingapp.integrationtest.config.DrawFillTestParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static io.davlac.drawingapp.config.SystemInputUtils.setUserInput;
import static io.davlac.drawingapp.config.SystemOutputUtils.formatExpectedResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DrawingApplicationDrawFillIntTest {

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

    private static Stream<Arguments> drawFillParameters() {
        return DrawFillTestParameters.drawFillParametersConfig();
    }

    @ParameterizedTest
    @MethodSource("drawFillParameters")
    void main_withDrawFill_shouldDrawFillAndQuit(String testDescription,
                                                 List<String> userInputs,
                                                 String expectedResult) throws Exception {
        setUserInput(userInputs);
        String outputResult = tapSystemOut(() -> DrawingApplication.main(new String[]{}));
        assertEquals(formatExpectedResult(expectedResult), outputResult);
    }

    private static Stream<Arguments> drawFillErrorParameters() {
        return DrawFillTestParameters.drawFillErrorParametersConfig();
    }

    @ParameterizedTest
    @MethodSource("drawFillErrorParameters")
    void main_withErrorDrawFill_shouldNotDrawFillAndQuit(String testDescription,
                                                         List<String> userInputs,
                                                         String expectedResult) throws Exception {
        setUserInput(userInputs);
        String outputResult = tapSystemOut(() -> DrawingApplication.main(new String[]{}));
        assertEquals(formatExpectedResult(expectedResult), outputResult);
    }
}
