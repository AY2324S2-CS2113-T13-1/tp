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
        ui.print("Please input your DIY problemSet: ");
        String description;
        String correctAnswer;
        double answer = 0.0;
        String explanations = "";
        String quit = "";
        while (!quit.equals("y")) {
            ui.print("input the description of the problem (e.g. 1+2*3): ");
            description = ui.readCommand();
            ui.print("input the correct answer of the problem (e.g. 7): ");
            correctAnswer = ui.readCommand();
            boolean isValidAnswer = false;
            while (!isValidAnswer) {
                try {
                    answer = Double.parseDouble(correctAnswer);
                    isValidAnswer = true;
                } catch (NumberFormatException e) {
                    ui.print("Invalid answer! Please input a number.");
                    correctAnswer = ui.readCommand();
                }
            }
            ui.print("Input the explanations of the problem (e.g. 1+2*3=7): ");
            explanations = ui.readCommand();
            Problem problem = new Problem(description,answer,explanations);
            problemSet.add(problem);
            ui.print("Have you finished adding problems? y/n: ");
            quit = ui.readCommand();
            while (!quit.equals("y") && !quit.equals("n")) {
                ui.print("input is invalid! Please input 'y' or 'n': ");
                quit = ui.readCommand();
            }
        }
        Record record = new Record(LocalDateTime.now(), 0.0, 0.0, problemSet, ProblemSetType.USER_DIY.getValue());
        Storage.addRecord(record);
        ui.print("Record successfully saved!");
        record.print(true);
        Ui.showLine();
    }
}
