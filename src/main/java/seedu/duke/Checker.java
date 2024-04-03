package seedu.duke;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Checker {
    private String[] userAnswer;
    private final Test test;
    private Boolean[] isCorrect;
    private int correctNumber;
    private double accuracy;
    private long time;
    List<Problem> wrongProblem= new ArrayList<>();
    List<String> wrongAnswer = new ArrayList<>();

    public Checker(Test test){
        assert test != null: "Input null test!";
        this.userAnswer = new String[test.getNumber()];
        this.test = test;
        this.isCorrect = new Boolean[test.getNumber()];
        this.correctNumber = 0;
        accuracy = 0.0;
        this.time = 0;

    }

    Boolean checkCorrectness(Problem problem, double answer){
        return Math.abs(problem.getAnswer() - answer) < 0.01;
    }


    void getUserAnswer() {
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < test.getNumber(); i++) {
            Problem problem = test.getProblem().get(i);
            System.out.println(problem.unsolved());
            String userInput = scanner.nextLine();
            userAnswer[i] = userInput;
            double answer = Double.NEGATIVE_INFINITY;
            try {
                answer = Double.parseDouble(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, please enter a number");
                wrongAnswer.add(userInput);
                wrongProblem.add(problem);
                continue;
            }

            if (checkCorrectness(problem, answer)) {
                correctNumber++;
                isCorrect[i] = true;
            }
            else {
                wrongAnswer.add(userInput);
                wrongProblem.add(problem);
            }

        }
        // hand with time and acc
        long endTime = System.currentTimeMillis();
        accuracy = (double) correctNumber / test.getNumber();
        this.time = (endTime - startTime) / 1000;
        for(int i=0;i<test.getNumber();i++){
            if(isCorrect[i]=false){
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
