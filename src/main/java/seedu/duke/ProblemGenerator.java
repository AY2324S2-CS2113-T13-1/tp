package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The ProblemGenerator class is responsible for generating math problems based on user input.
 * It provides methods to parse user commands, check the validity of input parameters, generate problems,
 * and apply specific rules to the generated problems.
 */
public class ProblemGenerator {

    private static final int MINIMUM_NUMBER = 0;
    private static final int MAXIMUM_NUMBER = 100;
    private static final int MINIMUM_DIGIT = 0;
    private static final int MAXIMUM_DIGITS = 9;
    private static final int MINIMUM_LENGTH = 2;
    private static final String VALID_OPERATORS = "+-*/";
    private static final int MAXIMUM_LENGTH = 10;
    private static final String DEFAULT_NUMBER = "10";
    private static final String DEFAULT_MAX_DIGITS = "2";
    private static final String DEFAULT_OPERATORS = VALID_OPERATORS;
    private static final String DEFAULT_LENGTH = "2";

    /**
     * This method parses the user command and returns a HashMap of options.
     * It splits the command into tokens and processes each token.
     *
     * @param command The command to be parsed.
     * @return A HashMap containing the parsed options.
     */
    public static HashMap<String, String> parseCommand(String command) {
        HashMap<String, String> options = new HashMap<>();
        String[] tokens = command.split("\\s+");

        for (int i = 0; i < tokens.length; i++) {
            if (i == tokens.length - 1) {
                break;
            }
            switch (tokens[i]) {
            case "-t":
                options.put("operators", tokens[i + 1]);
                break;
            case "-n":
                options.put("number", tokens[i + 1]);
                break;
            case "-d":
                options.put("maximumDigits", tokens[i + 1]);
                break;
            case "-l":
                options.put("length", tokens[i + 1]);
                break;
            default:
                break;
            }
        }
        return options;
    }

    /**
     * This method applies default options to the provided HashMap if certain options are missing.
     *
     * @param options The HashMap containing the parsed options.
     * @return The updated HashMap with default options applied.
     */
    private static HashMap<String, String> defaultOptions(HashMap<String, String> options) {
        if (!options.containsKey("operators")) {
            options.put("operators", DEFAULT_OPERATORS);
            Ui.missingMessage("operators");
        }
        if (!options.containsKey("number")) {
            options.put("number", DEFAULT_NUMBER);
            Ui.missingMessage("number");
        }
        if (!options.containsKey("maximumDigits")) {
            options.put("maximumDigits", DEFAULT_MAX_DIGITS);
            Ui.missingMessage("maximumDigits");
        }
        if (!options.containsKey("length")) {
            options.put("length", DEFAULT_LENGTH);
            Ui.missingMessage("length");
        }
        return options;
    }

    /**
     * This method processes the user command, checks the validity of the parameters,
     * applies default options, and generates a Test object based on the parameters.
     *
     * @param command The user command.
     * @return The generated Test object.
     */
    public Test typeChoose(String command) {
        HashMap<String, String> parameter = parseCommand(command);
        parameter = checkValidity(parameter);
        parameter = defaultOptions(parameter);
        return generate(parameter);
    }

    /**
     * This method checks the validity of the parameters in the provided HashMap.
     * It validates the number of problems, maximum digits, length, and operators.
     *
     * @param parameter The HashMap containing the parameters.
     * @return The updated HashMap with valid parameters.
     */
    private HashMap<String, String> checkValidity(HashMap<String, String> parameter) {

        checkNumber(parameter);

        checkMaxDigit(parameter);

        checkLength(parameter);
        checkOperations(parameter);
        return parameter;

    }

    private static void checkOperations(HashMap<String, String> parameter) {
        String op = parameter.get("operators");
        if (op != null) {
            for (char ch : op.toCharArray()) {
                if (VALID_OPERATORS.indexOf(ch) == -1) {// neither + - * or /
                    System.out.println("Operators should only be chosen from + - * and /!");
                    parameter.remove("operators");
                    Ui.invalidMessage("operators");
                    break;
                }
            }
        } else {
            parameter.remove("operators");
            Ui.invalidMessage("operators");
        }

    }

    private static void checkLength(HashMap<String, String> parameter) {
        try {
            NumberFormatException e = new NumberFormatException();
            int length = Integer.parseInt(parameter.get("length"));
            if (length < MINIMUM_LENGTH) {
                System.out.println("Number of operands should be at least 2!");

                throw e;
            }
            if (length > MAXIMUM_LENGTH) {
                System.out.println("Number of operands should be at most 10!");
                throw e;

            }
        } catch (NumberFormatException e) {
            parameter.remove("length");
            Ui.invalidMessage("length");
        }
    }

    private static void checkMaxDigit(HashMap<String, String> parameter) {
        try {
            NumberFormatException e = new NumberFormatException();
            int maxDigit = Integer.parseInt(parameter.get("maximumDigits"));
            if (maxDigit < MINIMUM_DIGIT) {
                System.out.println("Maximum digits of operands should be at least 1!");
                throw e;
            }
            if (maxDigit > MAXIMUM_DIGITS) {
                System.out.println("Maximum digits of operands should be at most 9!");
                throw e;
            }
        } catch (NumberFormatException e) {
            parameter.remove("maximumDigits");
            Ui.invalidMessage("maximum of digits");
        }
    }

    private static void checkNumber(HashMap<String, String> parameter) {
        try {
            NumberFormatException e = new NumberFormatException();
            int number = Integer.parseInt(parameter.get("number"));
            if (number < MINIMUM_NUMBER) {
                System.out.println("Number of problems should be at least 1!");

                throw e;
            }
            if (number > MAXIMUM_NUMBER) {
                System.out.println("Number of problems should be at most 100!");
                throw e;
            }
        } catch (NumberFormatException e) {
            parameter.remove("number");
            Ui.invalidMessage("number");
        }
    }

    /**
     * This method generates a Test object based on the provided parameters.
     * It generates the specified number of problems with the given maximum digits, length, and operators.
     *
     * @param parameter The HashMap containing the parameters.
     * @return The generated Test object.
     */
    private Test generate(HashMap<String, String> parameter) {
        int number = Integer.parseInt(parameter.get("number"));
        int maxDigit = Integer.parseInt(parameter.get("maximumDigits"));
        String op = parameter.get("operators");
        int length = Integer.parseInt(parameter.get("length"));
        Test test = new Test(op, maxDigit, number, length);

        ArrayList<String> operations = getOperations(op);


        for (int i = 0; i < number; i++) {
            StringBuilder descriptionBuilder = new StringBuilder();
            double answer;
            String explanations;
            int max = (int) Math.pow(MAXIMUM_LENGTH, maxDigit);

            buildFormula(length, max, descriptionBuilder, operations);

            descriptionBuilder = division_check(descriptionBuilder);
            Calculator calculator = new Calculator();
            answer = calculator.calculate(descriptionBuilder);
            explanations = calculator.getExplanationsString();
            String description = descriptionBuilder.toString();

            Problem p = new Problem(description, answer, explanations);
            System.out.println((i + 1) + ". " + p.unsolved());
            test.addToTest(p);
        }
        return test;
    }

    private void buildFormula(int length, int max, StringBuilder descriptionBuilder, ArrayList<String> operations) {
        for (int j = 0; j < length; j++) {
            int tempRandomNumber = (int) (Math.random() * max);
            descriptionBuilder.append(tempRandomNumber);

            if (j != length - 1) {
                String tempRandomOperator = operations.get((int) (Math.random() * operations.size()));
                descriptionBuilder.append(tempRandomOperator);
            }
        }
    }

    private static ArrayList<String> getOperations(String op) {
        ArrayList<String> operations = new ArrayList<>();

        if (op.contains("+")) {
            operations.add("+");
        }
        if (op.contains("-")) {
            operations.add("-");
        }
        if (op.contains("*")) {
            operations.add("*");
        }
        if (op.contains("/")) {
            operations.add("/");
        }
        return operations;
    }

    private StringBuilder division_check(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '/') {
                int numStart = i + 1;
                int numEnd = numStart;
                while (numEnd < sb.length() && isDigit(sb.charAt(numEnd))) {
                    numEnd++;
                }
                int divisor = 0;
                while (divisor == 0) {
                    divisor = (int) (Math.random() * 10);// divisor should be 1 to 9 with equal probability
                }
                sb.replace(numStart, numEnd, Integer.toString(divisor));
            }
        }
        return sb;
    }

    // Helper method to check if a character is a digit
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}


