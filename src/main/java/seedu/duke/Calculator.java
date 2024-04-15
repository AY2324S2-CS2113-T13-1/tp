package seedu.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Character.isDigit;

public class Calculator {

    private static List<String> explanations = new ArrayList<>();

    public double calculate(StringBuilder sb) {
        Stack<Double> numStack = new Stack<>();
        Stack<String> opStack = new Stack<>();

        ArrayList<Object> formula = toFormula(sb);
        ArrayList<Object> suffix = toSuffix(formula);
        for (Object object : suffix) {
            if (object instanceof Integer) {
                numStack.push(Double.valueOf((Integer) object));
            } else {

                double num2 = numStack.pop();
                double num1 = numStack.pop();
                double result = calculateTwo(num1, num2, (String) object);
                explanations.add(getExplanation(num1, num2, (String) object, result));
                numStack.push(result);
            }
        }
        assert numStack.size() == 1 : "wrong formula";
        // round to 3 decimal places
        Double result = Math.round(numStack.peek() * 1000.0) / 1000.0;
        return result;
    }

    private static String getExplanation(double num1, double num2, String op, double answer) {
        String start = "The computation of the problem: " +
                String.valueOf(num1) + " " + op + " " +
                String.valueOf(num2) + " = " +
                String.valueOf(answer) + "\n\n";
        List<String> explanation = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        String alignedProblem = "";
        if (op.equals("/")) {
            alignedProblem = "The division of " + num1 + " and " + num2 + " is " + answer + "\n";
        } else {
            String firstString = String.valueOf(num1);
            String secondString = String.valueOf(num2);
            String firstIntergerPart = firstString.split("\\.")[0];
            String secondIntergerPart = secondString.split("\\.")[0];
            if (firstIntergerPart.length() < secondIntergerPart.length()) {
                String temp = firstString;
                firstString = secondString;
                secondString = temp;
            }
            if (op.equals("*")) {
                op = "x";
            }
            // align the problem to the . place
            String firstDecimalPart = firstString.split("\\.")[1];
            String secondDecimalPart = secondString.split("\\.")[1];

            if (firstDecimalPart.length() < secondDecimalPart.length()) {
                firstString = firstString +
                        new String(new char[secondDecimalPart.length() -
                                firstDecimalPart.length()]).replace("\0", "0");
            } else {
                secondString = secondString +
                        new String(new char[firstDecimalPart.length() -
                                secondDecimalPart.length()]).replace("\0", "0");
            }

            explanation.add(firstString.trim());
            explanation.add(op + "          " + secondString.trim());
            explanation.add("---------------------------------------");
            explanation.add(String.valueOf(answer));
            for (String element : explanation) {
                builder.append(String.format("%30s%n", element));
            }
            alignedProblem = builder.toString();

        }
        return start + alignedProblem + "\n";
    }

    private static double calculateTwo(double num1, double num2, String op) {

        double answer;
        switch (op) {
        case ("+"):
            answer = num1 + num2;
            break;
        case ("-"):
            answer = num1 - num2;
            break;
        case ("*"):
            answer = num1 * num2;
            break;
        case ("/"):
            answer = num1 / num2;
            break;
        default:
            return 0;
        }
        return answer;
    }

    private static ArrayList<Object> toSuffix(ArrayList<Object> formula) {
        ArrayList<Object> suffix = new ArrayList<>();
        Stack<String> opStack = new Stack<>();

        for (Object object : formula) {
            if (object instanceof Integer) {
                suffix.add(object);
            } else if (object instanceof String) {
                String op = (String) object;

                if (opStack.empty()) {
                    opStack.push(op);
                    continue;
                }

                while (!opStack.empty()) {
                    if (!prior(op, opStack.peek())) {
                        suffix.add(opStack.pop());
                    } else {
                        break;
                    }
                }
                opStack.push(op);

            }

        }

        while (!opStack.empty()) {
            suffix.add(opStack.pop());
        }
        return suffix;
    }

    private static boolean prior(String op1, String op2) {
        int priority1;
        int priority2;

        priority1 = getPriority(op1);
        priority2 = getPriority(op2);

        return priority1 > priority2;
    }

    static int getPriority(String op) {
        int priority = 0;
        switch (op) {
        case "+":
        case "-":
            priority = 1;
            break;
        case "*":
        case "/":
            priority = 2;
            break;
        default:
            break;
        }
        return priority;
    }

    private static ArrayList<Object> toFormula(StringBuilder sb) {
        ArrayList<Object> formula = new ArrayList<>();
        for (int i = 0; i < sb.length(); ) { // change to separate objects

            if (isDigit(sb.charAt(i))) {

                int numStart = i;
                int numEnd = numStart;
                while (numEnd < sb.length() && isDigit(sb.charAt(numEnd))) {
                    numEnd++;
                }
                Integer num = Integer.parseInt(sb.substring(numStart, numEnd));
                formula.add(num);
                i = numEnd;
            } else {
                formula.add(Character.toString(sb.charAt(i)));
                i++;
            }
        }
        return formula;
    }

    public List<String> getExplanations() {
        return explanations;
    }

    public String getExplanationsString() {
        StringBuilder builder = new StringBuilder();
        for (String explanation : explanations) {
            builder.append(explanation);
        }
        return builder.toString();
    }
}
