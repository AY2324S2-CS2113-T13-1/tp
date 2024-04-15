package seedu.duke;

import java.util.ArrayList;

public class Test {

    String operators;
    int maxDigits;
    int number;

    int length;
    ArrayList<Problem> problemList = new ArrayList<>();
    String problemSetType;

    public Test(String operators, int maxDigits, int number, int length) {
        this.operators = operators;
        this.maxDigits = maxDigits;
        this.number = number;
        this.length = length;
        this.problemSetType = ProblemSetType.AUTO_GENERATED.getValue();
    }

    public Test(ArrayList<Problem> problemList, String problemSetType) {
        this.operators = "";
        this.maxDigits = 0;
        this.number = problemList.size();
        this.problemList = new ArrayList<>(problemList);
        this.problemSetType = problemSetType;
    }

    public void addToTest(Problem p) {
        problemList.add(p);
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Problem> getProblem() {
        return problemList;
    }

    public String getProblemSetType() {
        return problemSetType;
    }

}
