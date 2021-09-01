package io.davlac.drawingapp.context.output;

import io.davlac.drawingapp.context.output.model.RawOutput;
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
class RawOutputTest {

    private static final String ROW = "row";
    private static final String ROW2 = "row2";
    private static final String LINE_BREAK = "\n";
    public static final String EMPTY_STRING = "";

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
    void print_withOneRow_shouldPrintOneRow() throws Exception {
        RawOutput rawOutput = new RawOutput(new String[]{ROW});
        rawOutput.print();

        String consoleResult = tapSystemOut(rawOutput::print);

        assertEquals(formatExpectedResult(ROW + LINE_BREAK), consoleResult);
    }

    @Test
    void print_with2Rows_shouldPrint2Rows() throws Exception {
        RawOutput rawOutput = new RawOutput(new String[]{ROW, ROW2});
        rawOutput.print();

        String consoleResult = tapSystemOut(rawOutput::print);

        assertEquals(formatExpectedResult(ROW + LINE_BREAK + ROW2 + LINE_BREAK), consoleResult);
    }

    @Test
    void print_withEmptyRows_shouldPrintNoting() throws Exception {
        RawOutput rawOutput = new RawOutput(new String[]{});
        rawOutput.print();

        String consoleResult = tapSystemOut(rawOutput::print);

        assertEquals(EMPTY_STRING, consoleResult);
    }
}
