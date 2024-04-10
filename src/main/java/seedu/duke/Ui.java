package seedu.duke;

//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for interacting with the chatbot.
 */
public class Ui {

    // Pre-defined sentences
    private static final String PROBLEM_FORM =
            "To generate a problem set: Input the number and difficulty like below: \n" +
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
            "Generate problem sets: \t" + "gen -t [type] -n [number] -d [maximum digits]";
    private static final String HELP_COMMAND =
            "Help Function: \n" +
                    "To get help on a specific command, type 'help' followed by the command name. " +
                    "Here are the available commands:\n" +
                    "1. 'generate' or 'gen': Generate a problem set based on a series of parameters.\n" +
                    "2. 'records': View the records of your past problem solving sessions.\n" +
                    "3. 'retry': Retry a problem set you have solved before.\n" +
                    "4. 'exit': Exit the program.\n" +
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
        System.out.println("Type 'help' to see all instructions. \n");
        showLine();
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return "";
    }

    public static void showLine() {
        System.out.println("=========================");
    }

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
        default:// by default, user asks Help Instruction
            System.out.println(HELP_COMMAND);
            break;
        }
        showLine();
    }

    // print functions
    public void print(String string) {
        System.out.println(string);
    }

    // records input
    public void printRecords(ArrayList<Record> records, boolean showProbDetails) {
        for (Record record : records) {
            showLine();
            record.print(showProbDetails);
        }
        showLine();
    }

    // invalid input

    /**
     * Displays an error message for an invalid command.
     */
    public void invalidCommand() {
        System.out.println("Invalid command! Please try again.");
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

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }
}
