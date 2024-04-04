package seedu.duke;

import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Character.isDigit;

public class Calculator {

    public static double calculate(StringBuilder sb) {
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
                numStack.push(result);
            }
        }
        assert numStack.size() == 1 : "wrong formula";
        return numStack.pop();
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
                    opStack.push((String) op);
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
}
