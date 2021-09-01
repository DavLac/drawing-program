package io.davlac.drawingapp.context.consolescheduler.service;

import io.davlac.drawingapp.context.consoleshceduler.utils.ConsoleLogUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static io.davlac.drawingapp.utils.SystemOutputUtils.formatExpectedResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ConsoleLogUtilsTest {

    private static final String LINE_BREAK = "\n";

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void printBreakLine_shouldPrintLineBreak() throws Exception {
        String consoleResult = tapSystemOut(ConsoleLogUtils::printBreakLine);
        assertEquals(formatExpectedResult(LINE_BREAK), consoleResult);
    }
}
