package seedu.duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Checker class is used to check the correctness of the user's answer.
 * It will store the user's answer and the correctness of the answer.
 * It will also store the wrong answer and the corresponding problem.
 */
public class Checker {

    private static final double TOLERANCE = 0.01;

    private final String[] userAnswer;
    private final Test test;
    private final Boolean[] isCorrect;
    private final List<Problem> wrongProblem = new ArrayList<>();
    private final List<String> wrongAnswer = new ArrayList<>();
    private final Ui ui = new Ui("");
    private int correctNumber;
    private double accuracy;
    private long time;

    /**
     * Constructor of the Checker class.
     *
     * @param test the test that the user is taking.
     */
    public Checker(Test test) {
        assert test != null : "Input null test!";
        this.userAnswer = new String[test.getNumber()];
        this.test = test;
        this.isCorrect = new Boolean[test.getNumber()];
        this.correctNumber = 0;
        accuracy = 0.0;
        this.time = 0;
    }

    /**
     * Show the explanation of the problem.
     *
     * @param problem the problem that the user is solving.
     */
    public static void showExplanation(Problem problem) {
        String explanations = problem.getExplanations();
        Ui ui = new Ui("");
        ui.print("The explanation of the problem: " + problem.solved());
        ui.print("Let us calculate it step by step:");
        ui.print(explanations);
        ui.print("From all the steps above, we can get the answer: " + problem.solved() + "\n");

    }

    /**
     * Check the correctness of the user's answer.
     *
     * @param problem the problem that the user is solving.
     * @param answer  the answer that the user input.
     * @return true if the answer is correct, false otherwise.
     */
    Boolean checkCorrectness(Problem problem, double answer) {
        return Math.abs(problem.getAnswer() - answer) < TOLERANCE;
    }

    /**
     * Get the user's answer and check the correctness of the answer.
     */
    void getUserAnswer() {
        long startTime = System.currentTimeMillis();
        ui.print(
                "you can type \"exit\" to quit the test when answering the question...");
        //
        boolean isQuit = false;
        for (int i = 0; i < test.getNumber(); i++) {

            if (isQuit){
                break;
            }

            Problem problem = test.getProblem().get(i);
            ui.print(problem.unsolved());
            String userInput = ui.readCommand();
            userAnswer[i] = userInput;
            double answer = Double.NEGATIVE_INFINITY;

            boolean isValid = false;
            if (userInput.equals("exit")) {
                ui.print("Exit the test! All the test not finished will be marked as wrong!");
                break;
            }

            while (!isValid) {
                try {
                    answer = Double.parseDouble(userInput);
                    isValid = true;

                } catch (NumberFormatException e) {
                    ui.print("Invalid Input, please enter a number");
                    ui.print(problem.unsolved());
                    userInput = ui.readCommand();
                    if (userInput.equals("exit")) {
                        ui.print("Exit the test! All the test not finished will be marked as wrong!");
                        isQuit = true;
                        break;
                    }
                }
            }
            if (isQuit){
                break;
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
        // millisecond to second
        this.time = (endTime - startTime) / 1000;
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
