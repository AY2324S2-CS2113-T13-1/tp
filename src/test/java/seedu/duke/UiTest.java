package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private Ui ui;

    public String cleanOutput() {
        String res = output.toString().replaceAll("\n", "");
        res = res.replaceAll("\r", "");
        res = res.replaceAll("\t", "");
        return res;
    }

    @BeforeEach
    public void setUp() {
        ui = new Ui("ChatBot");
        System.setOut(new PrintStream(output));
    }

    @Test
    public void helpTest() {
        ui.help("gen");
        String expectedOutput = "Generate problem sets: gen -t [type] -n [number] -d [maximum digits] "+
                "-l[number of operands]" +
                "Input Instructions:" +
                "1. [operators]: Choose from +, -, *, /. " +
                "You can use any combination of them." +
                "2. [number]: Specify the number of problems to generate. " +
                "Must be between 1 and 100." +
                "3. [maximum digit]: Set the maximum number of digits for the numbers in the problems. " +
                "Must be between 1 and 9." +
                "4. [number of operands]: Determine the number of operands in each problem. " +
                "Must be between 2 and 10." +
                "Example Command: generate -t + -n 10 -d 2 -l 2" +
                "This will generate 10 problems using the + operator only. " +
                "Each problem will have 2 operands, and the maximum number of digits for each operand is 2." +
                "=========================";
        String cleanOutput = output.toString().replaceAll("\n", "").replaceAll("\t", "");
        cleanOutput = cleanOutput.replaceAll("\r", "");
        assertEquals(expectedOutput, cleanOutput());
    }

    @Test
    public void invalidCommandTest() {
        ui.invalidCommand();
        assertEquals("Invalid command! Please try again or enter 'help' for help.=========================",
                cleanOutput());
    }

    @Test
    public void exitTest() {
        ui.exit();
        assertEquals("Bye. Hope to see you again soon!=========================", cleanOutput());
    }
}
