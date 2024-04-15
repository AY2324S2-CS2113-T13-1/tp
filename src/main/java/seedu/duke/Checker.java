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

    public static void showExplanation(Problem problem) {
        String explanations = problem.getExplanations();
        Ui ui = new Ui("");
        ui.print("The explanation of the problem: " + problem.solved());
        ui.print("Let us caculate it step by step:");
        ui.print(explanations);

        ui.print("From all the steps above, we can get the answer: " + problem.solved()+"\n");

    }


    Boolean checkCorrectness(Problem problem, double answer) {
        return Math.abs(problem.getAnswer() - answer) < 0.01;
    }

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
