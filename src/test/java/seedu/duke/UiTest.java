package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private Ui ui;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ui = new Ui("ChatBot");
        System.setOut(new PrintStream(output));
    }

    @Test
    public void helpTest() {
        ui.help("gen");
        String expectedOutput = "Generate problem sets: \tgen -t [type] -n [number] -d [maximum digits]\n" +
                "Input Instructions:\n" +
                "[operators]: can be + - * /, you can combine any of them.\n" +
                "[number]: number of problem set generated\n" +
                "[maximum digit]: how big can the calculation be\n\n" +
                "For example: generate -t + -n 10 -d 2 -l 2\n" +
                "-> generate 10 problems with + and - operator, each has 2 numbers taking operations\n" +
                "and the maximum number of digits is 2 (99 max)\n" +
                "=========================\n";
        assertEquals(expectedOutput, output.toString());
    }


    @Test
    public void invalidCommandTest() {
        ui.invalidCommand();
        assertEquals("Invalid command! Please try again.\n=========================\n", output.toString());
    }

    @Test
    public void exitTest() {
        ui.exit();
        assertEquals("Bye. Hope to see you again soon!\n=========================\n", output.toString());
    }
}
