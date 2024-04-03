package seedu.duke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProblemGeneratorTest {
    public static String[] commands = {"generate -t + -n 1 -d 2 -l 2", "generate -t - -n 2 -d 3 -l 3",
        "generate -t * -n 3 -d 4 -l 4", "generate -t / -n 4 -d 5 -l 5"};
    @org.junit.jupiter.api.Test
    public void operatorTest() {
        for (String command: commands) {
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            ArrayList<Problem> problems = test.getProblem();
            for (Problem problem: problems) {
                if (command.equals(commands[0])) {
                    assertTrue(problem.unsolved().contains("+"),
                            "+: Problem format is incorrect: " + problem.unsolved());
                } else if (command.equals(commands[1])) {
                    assertTrue(problem.unsolved().contains("-"),
                            "-: Problem format is incorrect: " + problem.unsolved());
                } else if (command.equals(commands[2])) {
                    assertTrue(problem.unsolved().contains("*"),
                            "*: Problem format is incorrect: " + problem.unsolved());
                } else if (command.equals(commands[3])) {
                    assertTrue(problem.unsolved().contains("/"),
                            "/: Problem format is incorrect: " + problem.unsolved());
                } else {
                    fail("fail: Problem format is incorrect: " + problem.unsolved());
                }
            }
        }
    }
    private HashMap<String, String> parseCommand(String command) {
        return ProblemGenerator.parseCommand(command);
    }

    private ArrayList<Integer> parseNumbers(String problem) {

        // 使用正则表达式匹配数字和运算符
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(problem);

        // 提取匹配到的数字
        ArrayList<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        return numbers;
    }
    @org.junit.jupiter.api.Test
    public void numberTest() {
        for (String command: commands) {
            HashMap<String, String> parsedCommand = parseCommand(command);
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            ArrayList<Problem> problems = test.getProblem();
            assertEquals(Integer.parseInt(parsedCommand.get("number")), problems.size());
        }
    }
    @org.junit.jupiter.api.Test
    public void digitTest() {
        for (String command: commands) {
            HashMap<String, String> parsedCommand = parseCommand(command);
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            ArrayList<Problem> problems = test.getProblem();
            for (Problem problem: problems) {
                ArrayList<Integer> numbers = parseNumbers(problem.unsolved());
                for (int number: numbers) {
                    assertTrue(Integer.parseInt(parsedCommand.get("maximumDigits")) >= (int) Math.log10(number) + 1);
                }
            }
        }
    }
    @org.junit.jupiter.api.Test
    public void lengthTest() {
        for (String command: commands) {
            HashMap<String, String> parsedCommand = parseCommand(command);
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            ArrayList<Problem> problems = test.getProblem();
            for (Problem problem: problems) {
                ArrayList<Integer> numbers = parseNumbers(problem.unsolved());
                assertEquals(numbers.size(), Integer.parseInt(parsedCommand.get("length")),
                        "length" + problem.unsolved() + "is incorrect");
            }

        }
    }

    @org.junit.jupiter.api.Test
    public void calculateTest() {
        ProblemGenerator pb = new ProblemGenerator();
        StringBuilder formula = new StringBuilder();
        formula.append(10);
        formula.append("+");
        formula.append(5);
        formula.append("*");
        formula.append(5);
        formula.append("*");
        formula.append(3);
        assertEquals(Calculator.calculate(formula),85);
    }
}
