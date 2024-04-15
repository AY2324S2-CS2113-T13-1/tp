package seedu.duke;

/**
 * Represents the type of problem set.
 */
public enum ProblemSetType {
    USER_DIY("user-DIY"),
    AUTO_GENERATED("auto-generated");

    private String value;

    private ProblemSetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
