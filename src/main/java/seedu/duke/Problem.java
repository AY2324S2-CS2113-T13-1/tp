package seedu.duke;

public class Problem {

    private final String description;
    private final double answer;
    private String explanations;

    public Problem(String description, double answer, String explanations2) {
        this.description = description;
        this.answer = answer;
        this.explanations = explanations2;
        

    }

    public String solved() {
        return description + " = " + answer;
    }

    public String unsolved() {
        return description + "=__";
    }

    public double getAnswer() {
        return answer;
    }

    public String getDescription() {
        return description;
    }

    public String getExplanations() {
        return explanations;
    }
}
