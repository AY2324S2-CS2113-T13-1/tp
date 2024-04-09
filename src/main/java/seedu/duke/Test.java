package seedu.duke;

import java.util.ArrayList;

public class Test {

    String operators;
    int maxDigits;
    int number;

    int length;
    ArrayList<Problem> problemList = new ArrayList<>();

    public Test(String operators, int maxDigits, int number, int length) {
        this.operators = operators;
        this.maxDigits = maxDigits;
        this.number = number;
    }

    public Test(ArrayList<Problem> problemList) {
        this.operators = "";
        this.maxDigits = 0;
        this.number = problemList.size();
        this.problemList = new ArrayList<>(problemList);
    }

    public void addToTest(Problem p) {
        problemList.add(p);
    }

    public int getNumber(){
        return number;
    }

    public  ArrayList<Problem> getProblem() {
        return problemList;
    }

}
