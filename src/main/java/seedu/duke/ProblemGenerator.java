package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Character.isDigit;

public class ProblemGenerator {
    static final String DEFAULT_NUMBER = "10";
    static final String DEFAULT_MAX_DIGITS = "2";
    static final String DEFAULT_OPERATORS = "+-*/";
    static final String DEFAULT_LENGTH = "2";

    public static HashMap<String, String> parseCommand(String command) {
        HashMap<String, String> options = new HashMap<>();
        String[] tokens = command.split("\\s+");

        for (int i = 0; i < tokens.length; i++) {
            if(i==tokens.length-1){
                break;
            }
            if (tokens[i].equals("-t")) {
                options.put("operators", tokens[i + 1]);
            } else if (tokens[i].equals("-n")) {
                options.put("number", tokens[i + 1]);
            } else if (tokens[i].equals("-d")) {
                options.put("maximumDigits", tokens[i + 1]);
            } else if (tokens[i].equals("-l")){
                options.put("length", tokens[i + 1]);
            }

        }

        defaultOptions(command, options);

        return options;
    }

    private static void defaultOptions(String command, HashMap<String, String> options) {
        if (!command.contains("-t")) {
            options.put("operators", DEFAULT_OPERATORS);
            Ui.missingMessage("operators");
        }
        if (!command.contains("-n")) {
            options.put("number", DEFAULT_NUMBER);
            Ui.missingMessage("number");
        }
        if (!command.contains("-d")) {
            options.put("maximumDigits", DEFAULT_MAX_DIGITS);
            Ui.missingMessage("maximumDigits");
        }
        if(!command.contains("-l")){
            options.put("length", DEFAULT_LENGTH);
            Ui.missingMessage("length");
        }

    }

    public Test typeChoose(String command) {
        HashMap<String, String> parameter = parseCommand(command);
        return generate(parameter);
    }

    private Test generate(HashMap<String, String> parameter) {

        int number = Integer.parseInt(parameter.get("number"));

        int maxDigit = Integer.parseInt(parameter.get("maximumDigits"));
        String op = parameter.get("operators");

        int length  = Integer.parseInt(parameter.get("length"));

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



        Test test = new Test(op, maxDigit, number,length);

        for (int i = 0; i < number; i++) {
/*
            String description;
            double answer;
            int max = (int) Math.pow(10, maxDigit);
            int op1 = (int) (Math.random() * max);
            int op2 = (int) (Math.random() * max);
            String tempOperator = operations.get((int) (Math.random() * operations.size()));


            switch (tempOperator) {
            case ("+"):
                answer = op1 + op2;
                break;
            case ("-"):
                answer = op1 - op2;
                break;
            case ("*"):
                answer = op1 * op2;
                break;
            case ("/"):
                if (op2 == 0) {
                    continue;
                }
                answer = (double) op1 / op2;
                break;
            default:
                continue;
            }

            description = op1 + tempOperator + op2 + "=";
*/

            StringBuilder descriptionBuilder = new StringBuilder();
            double answer;
            int max = (int) Math.pow(10, maxDigit);

            for (int j = 0;j<length;j++){
                int temp_random_number = (int) (Math.random() * max);
                descriptionBuilder.append(temp_random_number);

                if(j != length - 1){
                    String temp_random_operator = operations.get((int) (Math.random() * operations.size()));
                    descriptionBuilder.append(temp_random_operator);
                }
            }

            descriptionBuilder = division_check(descriptionBuilder);

            answer = Calculator.calculate(descriptionBuilder);
            String  description =descriptionBuilder.toString();


            Problem p = new Problem(description, answer);
            System.out.println((i + 1) + ". " + p.unsolved());
            test.addToTest(p);


        }
        return test;
    }


    private StringBuilder division_check(StringBuilder sb) {
        // change the divisor to a non-zero one-digit number

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '/') {

                int numStart = i + 1;
                int numEnd = numStart;
                while (numEnd < sb.length() && isDigit(sb.charAt(numEnd))) {
                    numEnd++;
                }
                int divisor = 0;

                while(divisor == 0){
                    divisor = (int) (Math.random()*10);// divisor should be 1 to 9 with equal probability
                }
                sb.replace(numStart, numEnd, Integer.toString(divisor));
            }
        }

        return sb;

    }


}
