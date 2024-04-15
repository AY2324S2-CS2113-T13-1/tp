package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a test.
 * A Test object corresponds to a test that is created by the user.
 */
public class Test {

    String operators;
    int maxDigits;
    int number;

    int length;
    ArrayList<Problem> problemList = new ArrayList<>();
    String problemSetType;

    /**
     * Constructor for a test.
     *
     * @param operators the operators used in the test
     * @param maxDigits the maximum number of digits in the test
     * @param number the number of problems in the test
     * @param length the length of the test
     */
    public Test(String operators, int maxDigits, int number, int length) {
        this.operators = operators;
        this.maxDigits = maxDigits;
        this.number = number;
        this.length = length;
        this.problemSetType = ProblemSetType.AUTO_GENERATED.getValue();
    }

    /**
     * Constructor for a test.
     *
     * @param problemList the list of problems in the test
     * @param problemSetType the type of the problem set
     */
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
