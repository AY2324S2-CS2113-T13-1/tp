package seedu.duke;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.junit.jupiter.api.Assertions.*;
public class CheckerTest {
    private Checker checker;
    @org.junit.jupiter.api.Test
    public void sampleTest() {
        assertTrue(true);
    }

    @org.junit.jupiter.api.Test
    public void testCheckCorrectness() {
        Problem problem = new Problem("2 + 2", 4.0);
        Checker checker = new Checker(new Test("+",1,1,1));
        boolean result = checker.checkCorrectness(problem, 4.0);
        assertTrue(result, "Expected true for correct answer");
    }

    @org.junit.jupiter.api.Test
    public void testCheckCorrectness_withIncorrectAnswer() {
        Problem problem = new Problem("5 - 3", 2.0);
        Checker checker = new Checker(new Test("+",1,1,1));
        boolean result = checker.checkCorrectness(problem, 4.0);
        assertFalse(result, "Expected false for incorrect answer");
    }

/*
    This case may be unavailable currently since there's no access to the user answer
    May need access to user answer and allow modification for testing purpose.
*/

//    public static void testGetWrongAnswer() {
//        Checker checker = new Checker(new Test("+",1,2,1));
//        checker.getUserAnswer();
//        List<String> wrongAnswer = checker.getWrongAnswer();
//        assertNotNull(wrongAnswer, "Expected non-null list of wrong answers");
//        assertFalse(wrongAnswer.isEmpty(), "Expected non-empty list of wrong answers");
//    }

}
