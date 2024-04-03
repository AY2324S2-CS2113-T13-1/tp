package seedu.duke;

import java.util.ArrayList;

public class Problem {

    private final String description;
    private ArrayList<Double> answer = new ArrayList<Double>();


    public Problem(String description, double answer) {
        this.description = description;
        this.answer.add(answer)  ;
    }
    public Problem(String description, ArrayList<Double> answer) {
        this.description = description;
        this.answer =answer  ;
    }


    public String solved() {
        return description +" = "+ answer;
    }

    public String unsolved() {
        return description + "__";
    }

    public ArrayList<Double> getAnswer() {
        return answer;
    }

    public String getDescription() {
        return description;
    }
}
