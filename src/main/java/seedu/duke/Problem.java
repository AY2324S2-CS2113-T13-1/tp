package seedu.duke;

/**
 * Represents a problem.
 * A Problem object corresponds to a problem that is solved by the user.
 */
public class Problem {

    private final String description;
    private final double answer;
    private String explanations;

    /**
     * Constructor for a problem.
     *
     * @param description the description of the problem
     * @param answer the answer to the problem
     * @param explanations2 the explanations of the problem
     */
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
