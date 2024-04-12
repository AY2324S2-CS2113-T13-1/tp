package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckerTest {
    private Checker checker;

    @org.junit.jupiter.api.Test
    public void sampleTest() {
        assertTrue(true);
    }

    @org.junit.jupiter.api.Test
    public void testCheckCorrectness() {
        Problem problem = new Problem("2 + 2", 4.0, null);
        Checker checker = new Checker(new Test("+", 1, 1, 1));
        boolean result = checker.checkCorrectness(problem, 4.0);
        assertTrue(result, "Expected true for correct answer");
    }

    @org.junit.jupiter.api.Test
    public void testCheckCorrectness_withIncorrectAnswer() {
        Problem problem = new Problem("5 - 3", 2.0, null);
        Checker checker = new Checker(new Test("+", 1, 1, 1));
        boolean result = checker.checkCorrectness(problem, 4.0);
        assertFalse(result, "Expected false for incorrect answer");
    }

}
