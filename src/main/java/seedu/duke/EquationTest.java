package seedu.duke;

import java.util.ArrayList;

public class EquationTest {
    int number_of_unknowns;
    int number ;
    int maxDigit;
    ArrayList<Problem> equationList = new ArrayList<>();

    EquationTest(int number_of_unknowns, int number , int maxDigits){
        this.number_of_unknowns = number_of_unknowns;
        this.number = number;
        this.maxDigit = maxDigits;
    }
    public void addToEquationTest(Problem p) {
        equationList.add(p);
    }
    public int getNumber(){
        return number;
    }
    public  ArrayList<Problem> getProblem() {
        return equationList;
    }

}
