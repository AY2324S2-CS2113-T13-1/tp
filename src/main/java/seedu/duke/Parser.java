package seedu.duke;

import java.time.LocalDateTime;
import java.util.List;

public class Parser {

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
        // notice: write your parser function by your own
        case "": // by default, it will be "gen"
        case "gen":
        case "generate":
            //ProblemGenerator ;
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            Checker checker = new Checker(test);
            checker.getUserAnswer();
            String accRate = String.format("%.2f", checker.getAccuracy()*100);
            System.out.println("Acc: " + accRate +"%");
            System.out.println("Spend Time: " + checker.getTime() + "s");
            List<String> wrongAnswer = checker.getWrongAnswer();
            List<Problem> wrongProblem = checker.getWrongProblem();
            System.out.println("The following problem you have solved are wrong:\n");
            for(int i=0;i<wrongProblem.size();i++){
                Problem problem = wrongProblem.get(i);
                System.out.println("Your answer: "+ problem.getDescription()+" = "+wrongAnswer.get(i));
                System.out.println("Correct Answer: "+problem.solved());
            }
            // Storage write to file
            double speed = (double) test.getNumber() / checker.getTime();
            Storage.addRecord(new Record(LocalDateTime.now(), speed, checker.getAccuracy()));
            Storage.writeFile();

            break;
        case "help":
            ui.help(command);
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
