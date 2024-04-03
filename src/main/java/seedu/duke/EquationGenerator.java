package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EquationGenerator {

    static final String DEFAULT_NUMBER = "1";
    static final String DEFAULT_UKNOWNS = "2";
    static final String DEFAULT_MAX_DIGITS = "1";
     EquationTest generateEquations(HashMap<String, String> parameter){
         String[] unknownList = {"x", "y", "z", "u", "v"};
         int number = Integer.parseInt(parameter.get("number"));

         int maxDigit = Integer.parseInt(parameter.get("maximumDigits"));

         int number_of_unknowns  = Integer.parseInt(parameter.get("number_of_unknowns"));
         if(number_of_unknowns>5||number_of_unknowns<1){
             System.out.println("too many or too less unknowns , using default unknowns");
             number_of_unknowns = 2;
         }

         EquationTest Test = new EquationTest(number_of_unknowns,number,maxDigit);
         int max = (int) Math.pow(10, maxDigit);

         for(int i=0;i<number;i++) {
             int[][] unknownArray = new int[number_of_unknowns][1];
             fillRandom(unknownArray, max);

             int[][] coefficients = new int[number_of_unknowns][number_of_unknowns];
             while (determinant(coefficients) == 0) {
                  fillRandom(coefficients, max);
             }

             int[][] answerArray = multiply(coefficients, unknownArray);

             String description = arrayToDescription(coefficients,unknownList,unknownArray);
             ArrayList<Double> answer = new ArrayList<>();
             for(int j =0;j<number_of_unknowns;j++){
                 answer.add((double) unknownArray[j][0]);
             }
             Problem p =new Problem(description,answer);
             Test.addToEquationTest(p);
             System.out.println(description);
         }

         return Test;
     }


    public static HashMap<String, String> parseCommand(String command) {
        HashMap<String, String> options = new HashMap<>();
        String[] tokens = command.split("\\s+");

        for (int i = 0; i < tokens.length; i++) {
            if(i==tokens.length-1){
                break;
            }
            if (tokens[i].equals("-u")) {
                options.put("number_of_unknowns", tokens[i + 1]);
            } else if (tokens[i].equals("-n")) {
                options.put("number", tokens[i + 1]);
            } else if (tokens[i].equals("-d")) {
                options.put("maximumDigits", tokens[i + 1]);
            }
        }
        defaultOptions(command, options);

        return options;
    }

    private static void defaultOptions(String command, HashMap<String, String> options) {

        if (!command.contains("-n")) {
            options.put("number", DEFAULT_NUMBER);
            Ui.missingMessage("number");
        }
        if (!command.contains("-d")) {
            options.put("maximumDigits", DEFAULT_MAX_DIGITS);
            Ui.missingMessage("maximumDigits");
        }
        if(!command.contains("-u")){
            options.put("number_of_unknowns", DEFAULT_UKNOWNS);
            Ui.missingMessage(" unknowns");
        }

    }

    public EquationTest typeChoose(String command) {
        HashMap<String, String> parameter = parseCommand(command);
        return  generateEquations(parameter);
    }

    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;

        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    public static void fillRandom(int[][] array, int max) {


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int random = (int)(Math.random()*max);
                int sign = (Math.random()>0.5)? 1: -1;
                random = random*sign;
                array[i][j] =random;

            }
        }
    }

    public static double determinant(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        } else if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            double det = 0;
            for (int j = 0; j < n; j++) {

                int[][] minor = getMinor(matrix, 0, j);

                det += Math.pow(-1, j) * matrix[0][j] * determinant(minor);
            }
            return det;
        }
    }


    private static int[][] getMinor(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int[][] minor = new int[n - 1][n - 1];
        for (int i = 0, k = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0, l = 0; j < n; j++) {
                if (j == col) {
                    continue;
                }
                minor[k][l] = matrix[i][j];
                l++;
            }
            k++;
        }
        return minor;
    }
    public static String arrayToDescription(int[][] coefficients,String[] unknownList,int[][] answerArray) {
        StringBuilder sb = new StringBuilder();
        int rows = coefficients.length;
        int cols = coefficients[0].length;

        for (int i= 0;i<rows;i++) {
            for (int j = 0;j<cols;j++) {
                if(coefficients[i][j]==0){
                    continue;
                }
                if(coefficients[i][j]>=0&&j>0){
                    sb.append("+");
                }

                sb.append(coefficients[i][j]);
                sb.append(unknownList[j]);
            }
            sb.append( "= ");
            sb.append(answerArray[i][0]);
            sb.append("\n");
        }
        return sb.toString();
    }


}
