package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class Checker {
    private String[] userAnswer;
    private final Test test;
    private Boolean[] isCorrect;
    private int correctNumber;
    private double accuracy;
    private long time;
    private final List<Problem> wrongProblem = new ArrayList<>();
    private List<String> wrongAnswer = new ArrayList<>();
    private Ui ui = new Ui("");

    public Checker(Test test) {
        assert test != null : "Input null test!";
        this.userAnswer = new String[test.getNumber()];
        this.test = test;
        this.isCorrect = new Boolean[test.getNumber()];
        this.correctNumber = 0;
        accuracy = 0.0;
        this.time = 0;
    }

    Boolean checkCorrectness(Problem problem, double answer) {
        return Math.abs(problem.getAnswer() - answer) < 0.01;
    }

    void getUserAnswer() {
        long startTime = System.currentTimeMillis();
        // Scanner scanner = new Scanner(System.in);
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

    public String getExplaination(Problem problem){
        String description = problem.getDescription();
        String explanation = "";
        explanation = "Let's explain this problem by the following format:  +/n";
        String startSpace = "\t\t\t";
        if(description.contains("+")){
            String [] parser = description.split("\\+");
            double firstNumber = Double.parseDouble(parser[0]);
            double secondNubmber = Double.parseDouble(parser[1]);
            String firstString = String.valueOf(firstNumber);
            String secondString = String.valueOf(secondNubmber);
            //Shift the longer number to the first
            if(firstString.length()<secondString.length()){
                String temp = firstString;
                firstString = secondString;
                secondString = temp;
            }
            int numSpace = firstString.length()-secondString.length()-1;
            //Use the space to align the number
            String alignSpace = new String(new char[numSpace]).replace('\0', ' ');
            explanation += startSpace + firstString + "\n";
            explanation += startSpace + "\\+" + alignSpace + secondString + "\n";




        } else if (description.contains("-")) {

        } else if (description.contains("*")){

        } else if (description.contains("/")){
                explanation = "We do not support the explanation with division now!";
        }else {
            explanation = "The format of the problem do not provide any explanation now!";
        }
        return explanation;
    };
}
