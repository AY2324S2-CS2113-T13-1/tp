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
        String expectedOutput = "Generate problem sets: gen -t [type] -n [number] -d [maximum digits]" +
                "Input Instructions:" +
                "[operators]: can be + - * /, you can combine any of them." +
                "[number]: number of problem set generated" +
                "[maximum digit]: how big can the calculation be" +
                "For example: generate -t + -n 10 -d 2 -l 2" +
                "-> generate 10 problems with + and - operator, each has 2 numbers taking operations" +
                "and the maximum number of digits is 2 (99 max)" +
                "=========================";
        String cleanOutput = output.toString().replaceAll("\n", "").replaceAll("\t", "");

        assertEquals(expectedOutput, cleanOutput);
    }


    @Test
    public void invalidCommandTest() {
        ui.invalidCommand();
        String cleanOutput = output.toString().replaceAll("\n", "").replaceAll("\t", "");
        assertEquals("Invalid command! Please try again.=========================", cleanOutput);
    }

    @Test
    public void exitTest() {
        ui.exit();
        String cleanOutput = output.toString().replaceAll("\n", "").replaceAll("\t", "");
        assertEquals("Bye. Hope to see you again soon!=========================", cleanOutput);
    }
}
