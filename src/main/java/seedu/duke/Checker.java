package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class Checker {
    private final String[] userAnswer;
    private final Test test;
    private final Boolean[] isCorrect;
    private final List<Problem> wrongProblem = new ArrayList<>();
    private final List<String> wrongAnswer = new ArrayList<>();
    private final Ui ui = new Ui("");
    private int correctNumber;
    private double accuracy;
    private long time;

    public Checker(Test test) {
        assert test != null : "Input null test!";
        this.userAnswer = new String[test.getNumber()];
        this.test = test;
        this.isCorrect = new Boolean[test.getNumber()];
        this.correctNumber = 0;
        accuracy = 0.0;
        this.time = 0;
    }

    public static String getExplanation(Problem problem) {
        String description = problem.getDescription();
        String start = "Let's explain this problem by the following format:  " + "\n\n";
        List<String> explanation = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        if (description.contains("+")) {
            String[] parser = description.split("\\+");
            double firstNumber = Double.parseDouble(parser[0]);
            double secondNumber = Double.parseDouble(parser[1]);
            String answerString = String.valueOf(problem.getAnswer());
            String firstString = String.valueOf(firstNumber);
            String secondString = String.valueOf(secondNumber);
            //Shift the longer number to the first
            if (firstString.length() < secondString.length()) {
                String temp = firstString;
                firstString = secondString;
                secondString = temp;
            }
            explanation.add(firstString);
            explanation.add("+" + "          " + secondString);

        } else if (description.contains("-")) {
            String[] parser = description.split("-");
            double firstNumber = Double.parseDouble(parser[0]);
            double secondNumber = Double.parseDouble(parser[1]);
            String answerString = String.valueOf(problem.getAnswer());
            String firstString = String.valueOf(firstNumber);
            String secondString = String.valueOf(secondNumber);
            //Shift the longer number to the first
            if (firstString.length() < secondString.length()) {
                String temp = firstString;
                firstString = secondString;
                secondString = temp;
            }
            explanation.add(firstString);
            explanation.add("-" + "          " + secondString);

        } else if (description.contains("*")) {
            String[] parser = description.split("\\*");
            double firstNumber = Double.parseDouble(parser[0]);
            double secondNumber = Double.parseDouble(parser[1]);
            String answerString = String.valueOf(problem.getAnswer());
            String firstString = String.valueOf(firstNumber);
            String secondString = String.valueOf(secondNumber);
            //Shift the longer number to the first
            if (firstString.length() < secondString.length()) {
                String temp = firstString;
                firstString = secondString;
                secondString = temp;
            }
            explanation.add(firstString);
            explanation.add("X" + "          " + secondString);
        } else if (description.contains("/")) {
            return "We do not support the explanation with division now!";
        } else {
            return "The format of the problem do not provide any explanation now!";
        }
        explanation.add("---------------------------------------");
        explanation.add(String.valueOf(problem.getAnswer()));
        for (String element : explanation) {
            builder.append(String.format("%" + 20 + "s%n", element));
        }
        String alignedProblem = builder.toString();
        String end = "You can compare your answer with the above function\n";
        return start + alignedProblem + "\n" + end;
    }

    public static void testExplanation() {
        Problem testProblem = new Problem("99+9", 108);
        System.out.println(getExplanation(testProblem));
    }

    Boolean checkCorrectness(Problem problem, double answer) {
        return Math.abs(problem.getAnswer() - answer) < 0.01;
    }

    void getUserAnswer() {
        long startTime = System.currentTimeMillis();
        ui.print("Press Enter to start answering the questions...");
        String userInput = ui.readCommand();

        for (int i = 0; i < test.getNumber(); i++) {
            Problem problem = test.getProblem().get(i);
            ui.print(problem.unsolved());
            userInput = ui.readCommand();
            userAnswer[i] = userInput;
            double answer = Double.NEGATIVE_INFINITY;
            try {
                answer = Double.parseDouble(userInput);
            } catch (NumberFormatException e) {
                ui.print("Invalid Input, please enter a number");
                wrongAnswer.add(userInput);
                wrongProblem.add(problem);
                continue;
            }

            if (checkCorrectness(problem, answer)) {
                correctNumber++;
                isCorrect[i] = true;
            } else {
                wrongAnswer.add(userInput);
                wrongProblem.add(problem);
            }

        }
        // hand with time and acc
        long endTime = System.currentTimeMillis();
        accuracy = (double) correctNumber / test.getNumber();
        //millisecond to second
        this.time = (endTime - startTime) / 1000;
        for (int i = 0; i < test.getNumber(); i++) {
            if (isCorrect[i] = false) {
                wrongProblem.add(test.getProblem().get(i));
                wrongAnswer.add(userAnswer[i]);
            }
        }
    }

    public Boolean[] checkAnswer() {
        return isCorrect;
    }

    public List<String> getWrongAnswer() {
        return wrongAnswer;
    }

    public List<Problem> getWrongProblem() {
        return wrongProblem;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public long getTime() {
        return time;
    }

}
