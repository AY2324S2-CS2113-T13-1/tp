package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for interacting with the chatbot.
 * This class handles all user interactions, including reading user input and displaying output.
 */
public class Ui {

    // Pre-defined sentences
    private static final String FIRST_INSTRUCTION =
            "First time instruction: To generate a problem set, Input like below:\n" +
                    "< generate -t [type] -n [number] -d [maximum digit] -l [number of operands] >";
    private static final String INPUT_INSTRUCTION =
            "Input Instructions:\n" +
                    "1. [operators]: Choose from +, -, *, /. You can use any combination of them.\n" +
                    "2. [number]: Specify the number of problems to generate. " +
                    "Must be between 1 and 100.\n" +
                    "3. [maximum digit]: Set the maximum number of digits for the numbers in the problems. " +
                    "Must be between 1 and 9.\n" +
                    "4. [number of operands]: Determine the number of operands in each problem. " +
                    "Must be between 2 and 10.\n\n" +
                    "Example Command: generate -t + -n 10 -d 2 -l 2\n" +
                    "This will generate 10 problems using the + operator only. " +
                    "Each problem will have 2 operands, and the maximum number of digits for each operand is 2.";
    private static final String GEN_COMMAND =
            "Generate problem sets: \t" + "gen -t [type] -n [number] -d [maximum digits] -l[number of operands]";
    private static final String HELP_COMMAND =
            "Help Function: \n" +
                    "To get help on a specific command, type 'help' followed by the command name. " +
                    "Here are the available commands:\n" +
                    "1. 'generate' or 'gen': Generate a problem set based on a series of parameters.\n" +
                    "2. 'records': View the records of your past problem solving sessions.\n" +
                    "3. 'retry': Retry a problem set you have solved before.\n" +
                    "4. 'DIY': add user-DIY problem set to the problem set database.\n" +
                    "5. 'exit': Exit the program.\n" +
                    "For example, 'help generate' will show you how to use the generate command.";
    private static final String RECORDS_COMMAND =
            "View past records(in-brackets parameters are optional): \t" + "records [-d] [-s] [-a] [-p] [-details]\n" +
                    "[-d]: Sort the records based on dateTime in increasing order. Add r to reverse order (-dr)\n" +
                    "[-s]: Sort the records based on speed in increasing order. Add r to reverse order (-sr)\n" +
                    "[-a]: Sort the records based on accuracy in increasing order. Add r to reverse order (-ar)\n" +
                    "[-p]: Sort the records based on problemSetID in increasing order. Add r to reverse order (-pr)\n" +
                    "[-details]: Show the details of the problem set(each individual problem).";
    private static final String RETRY_COMMAND =
            "Retry a problem set you have solved before in the past: \t" + "retry PROBLEM_SET_ID\n" +
                    "PROBLEM_SET_ID is an integer, and can be found by using the 'records' command.\n";
    private static final String DIY_COMMAND =
            "Add user defined problem sets in to the problem database: \t" + "DIY\n";
    private static final String EXPLANATION_START = 
            "\nExplanation Instruction: \n" +
            "1. To view the explanation of a problem, type 'explanation' or 'exp'.\n" +
            "2. The explanation of the problem will be shown step by step " +
            "with the formulation of column calculation.\n" +
            "3. If you want to exit the explanation, type 'exit'.";
    private static final String EXPLANATION_END = 
            "Explanation End.";

    private static final String WRONG_ANSWER = 
            "We evaluate the answer accurate in 2 decimal places.\n"+
            "You may check the answer as well as the explanation.\n"+
            "If you do not want to see the explanation, type 'exit'."; 
            
    private static final String START_ANSWER_TEST =
            "START ANSWERING TEST INSTRUCTION:\n" +
            "1.Press Enter to start answering the questions.\n" +
            "2.You can type \"exit\" to quit the test when answering the question.\n"+
            "3.You may round the answer to 2 decimal places for complex answers.";

    private static final String EXIT_TEST =
            "Exit the test! All the test not finished will be marked as wrong! No explanation for them!";

    private static final String TEST_FINISH =
            "TEST FINISHED! \n"+
            "Here is your test result: ";

    private static final String EXIT_COMMAND = "Exit program: exit\n";

    private final String name;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new Ui with the given name.
     *
     * @param name The name of the chatbot.
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Displays a greeting message.
     */
    public void greet() {
        showLine();
        String logo = "__   __       _   _      ____            _\n" +
                "|  \\/  | __ _| |_| |__  / ___| ___ _ __ (_)_   _ ___\n" +
                "| |\\/| |/ _` | __| '_ \\| |  _ / _ \\ '_ \\| | | | / __|\n" +
                "| |  | | (_| | |_| | | | |_| |  __/ | | | | |_| \\__ \\\n" +
                "|_|  |_|\\__,_|\\__|_| |_|\\____|\\___|_| |_|_|\\__,_|___/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm " + name + "!");
        System.out.println("Type 'help' to see all instructions.\n");
        System.out.println(FIRST_INSTRUCTION);
        showLine();
    }

    /**
     * Reads the user's command.
     *
     * @return The user's command as a String.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return "";
    }

    /**
     * Displays a line separator.
     */
    public static void showLine() {
        System.out.println("=========================");
    }

    /**
     * Displays help information based on the helpType.
     *
     * @param helpType The type of help information to display.
     */
    public void help(String helpType) {
        switch (helpType) {
        case "gen":
        case "generate":
            System.out.println(GEN_COMMAND);
            System.out.println(INPUT_INSTRUCTION);
            break;
        case "records":
            System.out.println(RECORDS_COMMAND);
            break;
        case "retry":
            System.out.println(RETRY_COMMAND);
            break;
        case "exit":
            System.out.println(EXIT_COMMAND);
            break;
        case "DIY":
            System.out.println(DIY_COMMAND);
            break;
        default:// by default, user asks Help Instruction
            System.out.println(HELP_COMMAND);
            break;
        }
        showLine();
    }

    /**
     * Prints the given string.
     *
     * @param string The string to print.
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Displays the records.
     *
     * @param records The records to display.
     * @param showProbDetails Whether to show problem details.
     */
    public void printRecords(ArrayList<Record> records, boolean showProbDetails) {
        for (Record record : records) {
            showLine();
            record.print(showProbDetails);
        }
        showLine();
    }

    /**
     * Displays an error message for an invalid inputs.
     */

    public void invalidCommand() {
        System.out.println("Invalid command! Please try again or enter 'help' for help.");
        showLine();
    }

    public void invalidParameter(String command) {
        System.out.println("Invalid parameters for command: " + command);
        System.out.println("Try 'help COMMAND_NAME' for proper command formats");
        showLine();
    }

    static void invalidMessage(String parameters) {
        System.out.println("Detected invalid " + parameters + "!");
    }

    static void missingMessage(String parameters) {
        System.out.println("Using default " + parameters + " instead!");
    }

    /**
     * Displays the explanation start message.
     */
    public void showExplanation() {
        System.out.println(EXPLANATION_START);
        showLine();
    }

    public void showExplanationEnd() {
        System.out.println(EXPLANATION_END);
        showLine();
    }

    /**
     * Displays the wrong answer message.
     *
     * @param i The number of wrong answers.
     */
    public void showWrongAnswer(int i) {
        System.out.println("You have " + i + " wrong answers.");
        System.out.println(WRONG_ANSWER);
        System.out.println("The following " + i + " answers you gave are wrong: ");
        showLine();
    }

    public void startAnswerTest() {
        showLine();
        System.out.println(START_ANSWER_TEST);
        showLine();
    }

    public void exitTest() {
        System.out.println(EXIT_TEST);
        showLine();
    }
    
    /**
     * Displays the test result.
     *
     * @param acc The accuracy of the test.
     * @param time The time spent on the test.
     */
    public void showTestResult(double acc, long time) {
        System.out.println(TEST_FINISH);
        String accRate = String.format("%.2f", acc * 100);
        System.out.println("Accuracy: " + accRate + "%");
        System.out.println("Time Spend: " + time + "s");
        
        if(acc>=90) {
            System.out.println("Well Done! You have a good performance!");
        } else if(acc>=50) {
            System.out.println("Good Job! Keep going!");
        } else {
            System.out.println("You may need more practice!");
        }
        showLine();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }
}
