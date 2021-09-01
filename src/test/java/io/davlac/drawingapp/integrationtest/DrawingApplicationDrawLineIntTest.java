package io.davlac.drawingapp.integrationtest;

import io.davlac.drawingapp.DrawingApplication;
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
import static io.davlac.drawingapp.utils.SystemInputUtils.setUserInput;
import static io.davlac.drawingapp.utils.SystemOutputUtils.formatExpectedResult;
import static io.davlac.drawingapp.integrationtest.config.DrawLineTestParameters.drawLineErrorParametersConfig;
import static io.davlac.drawingapp.integrationtest.config.DrawLineTestParameters.drawLineParametersConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DrawingApplicationDrawLineIntTest {

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

    private static Stream<Arguments> drawLineParameters() {
        return drawLineParametersConfig();
    }

    @ParameterizedTest
    @MethodSource("drawLineParameters")
    void main_withDrawLine_shouldDrawLineAndQuit(String testDescription,
                                                 List<String> userInputs,
                                                 String expectedResult) throws Exception {
        setUserInput(userInputs);
        String outputResult = tapSystemOut(() -> DrawingApplication.main(new String[]{}));
        assertEquals(formatExpectedResult(expectedResult), outputResult);
    }

    private static Stream<Arguments> drawLineErrorParameters() {
        return drawLineErrorParametersConfig();
    }

    @ParameterizedTest
    @MethodSource("drawLineErrorParameters")
    void main_withErrorDrawLine_shouldNotDrawLineAndQuit(String testDescription,
                                                         List<String> userInputs,
                                                         String expectedResult) throws Exception {
        setUserInput(userInputs);
        String outputResult = tapSystemOut(() -> DrawingApplication.main(new String[]{}));
        assertEquals(formatExpectedResult(expectedResult), outputResult);
    }
}
