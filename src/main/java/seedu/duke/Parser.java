package seedu.duke;

import java.time.LocalDateTime;
import java.util.List;

public class Parser {

    public static void parseRetry(String description, Ui ui) {
        try {
            int id = Integer.parseInt(description);
            Test test = Storage.problemSetByID(id);
            if (test == null) {
                ui.print("no problem set with this ID is found. Please double-check id.");
                return;
            }
            for (Problem problem : test.getProblem()) {
                System.out.println("    " + problem.getDescription());
            }
            solveProbSet(test, ui, true, id);
        } catch(Exception e) {
            ui.print("failed to parse a valid problem set ID. Please double-check format.");
        }
    }
    public static void parseRecord(String description, Ui ui) {
        String[] tokens = description.split(" ");
        int spdSortOp = 0;
        int dateSortOp = 0;
        int accSortOp = 0;
        int probSortOp = 0;
        boolean probShowDetails = false;
        for (String token : tokens) {
            if(token.isEmpty()) {
                continue;
            }
            if (token.equals("-details")) {
                probShowDetails = true;
            } else if(token.length() > 3) {
                ui.invalidParameter("records");
                return;
            }

            if (token.startsWith("-s")) {
                spdSortOp = 1;
                if (token.length() == 3 && token.endsWith("r")) {
                    spdSortOp = 2;
                }
            } else if (token.startsWith("-d")) {
                dateSortOp = 1;
                if (token.length() == 3 && token.endsWith("r")) {
                    dateSortOp = 2;
                }
            } else if (token.startsWith("-a")) {
                dateSortOp = 1;
                if (token.length() == 3 && token.endsWith("r")) {
                    dateSortOp = 2;
                }
            } else if (token.startsWith("-p")) {
                probSortOp = 1;
                if (token.length() == 3 && token.endsWith("r")) {
                    probSortOp = 2;
                }
            } else {
                ui.invalidParameter("records");
                return;
            }
        }
        ui.printRecords(Storage.sortRecords(dateSortOp, spdSortOp, accSortOp, probSortOp), probShowDetails);
    }

    public static void solveProbSet(Test test, Ui ui, boolean retry, int id) {
        Checker checker = new Checker(test);
        checker.getUserAnswer();
        String accRate = String.format("%.2f", checker.getAccuracy() * 100);
        ui.print("Acc: " + accRate + "%");
        ui.print("Spend Time: " + checker.getTime() + "s");
        List<String> wrongAnswer = checker.getWrongAnswer();
        List<Problem> wrongProblem = checker.getWrongProblem();
        ui.print("The following " + wrongProblem.size() + " answers you gave are wrong: \n");

        for (int i = 0; i < wrongProblem.size(); i++) {
            Problem problem = wrongProblem.get(i);
            ui.print("The "+ String.valueOf(i+1)+"th wrong answer of you: ");
            ui.print("Your answer: " + problem.getDescription() + " = " + wrongAnswer.get(i));
            ui.print("Correct Answer: " + problem.solved());
            // need further implementation for 3 more operators
            ui.print("If you want to see the explanation, type exp or explanation, else just type enter, " +
                    "type exit to stop showing the answer");
            String userInput = ui.readCommand();
            if(userInput.equals("exit")) {
                break;
            }
            if(userInput.equals("exp")||userInput.equals("explanation")) {
                Checker.showExplanation(problem);
            }
        }

        // Storage write to file
        double speed = (double) test.getNumber() / checker.getTime() * 60;
        if (retry) {
            Storage.addRecord(new Record(LocalDateTime.now(),
                    speed,
                    checker.getAccuracy(),
                    test.getProblem(),
                    id,
                    test.getProblemSetType()));
        } else {
            Storage.addRecord(new Record(LocalDateTime.now(),
                    speed,
                    checker.getAccuracy(),
                    test.getProblem(),
                    test.getProblemSetType()));
        }
        Storage.writeFile();
    }

    public static void parse(String command, Ui ui) {

        /*
         * Example of commands:
         *
         * Generate problem sets:
         * gen -t 1 -n 2 -d 3
         *
         * Help function:
         * help
         */

        // Split the command into two parts: action and description
        String[] parts = command.split(" ", 2);
        String action = parts[0];
        String description = "";
        if (parts.length > 1) {
            description = parts[1];
        }

        switch (action) {
        case "": // by default, it will be "gen"
        case "gen":
        case "generate":
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            solveProbSet(test, ui, false, 0);
            break;
        case "records":
            parseRecord(description, ui);
            break;
        case "retry":
            parseRetry(description, ui);
            break;
        case "DIY":
            DIYProblemSet diy = new DIYProblemSet();
            diy.addDIYProblemSet(ui);
            break;
        case "help":
            ui.help(description);
            break;
        case "exit":
            ui.exit();
            break;
        default:
            ui.invalidCommand();
            break;
        }
    }
}
