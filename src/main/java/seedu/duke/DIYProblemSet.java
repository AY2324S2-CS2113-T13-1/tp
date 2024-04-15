package seedu.duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class DIYProblemSet {
    ArrayList<Problem> problemSet;

    public DIYProblemSet() {
        problemSet = new ArrayList<>();
    }

    public void addDIYProblemSet(Ui ui) {
        Scanner scanner = new Scanner(System.in);
        ui.print("Please input your DIY problemSet: ");
        String description;
        String correctAnswer;
        double answer = 0.0;
        String explanations = "";
        String quit = "";
        while (!quit.equals("y")) {
            ui.print("input the description of the problem (e.g. 1+2*3): ");
            description = scanner.nextLine();
            ui.print("input the correct answer of the problem (e.g. 7): ");
            correctAnswer = scanner.nextLine();
            boolean isValidAnswer = false;
            while (!isValidAnswer) {
                try {
                    answer = Double.parseDouble(correctAnswer);
                    isValidAnswer = true;
                } catch (NumberFormatException e) {
                    ui.print("Invalid answer! Please input a number.");
                    correctAnswer = scanner.nextLine();
                }
            }
            ui.print("Input the explanations of the problem (e.g. 1+2*3=7): ");
            explanations = scanner.nextLine();
            Problem problem = new Problem(description,answer,explanations);
            problemSet.add(problem);
            ui.print("Have you finished adding problems? y/n: ");
            quit = scanner.nextLine();
            while (!quit.equals("y") && !quit.equals("n")) {
                ui.print("input is invalid! Please input 'y' or 'n': ");
                quit = scanner.nextLine();
            }
        }
        Record record = new Record(LocalDateTime.now(), 0.0, 0.0, problemSet, ProblemSetType.USER_DIY.getValue());
        Storage.addRecord(record);
        ui.print("Record successfully saved!");
        record.print(true);
        Ui.showLine();
        scanner.close();
    }


}
