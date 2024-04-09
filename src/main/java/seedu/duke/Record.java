package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Record {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalDateTime dateTime;

    private double speed;

    private double accuracy;

    private ArrayList<Problem> probSet = new ArrayList<>();

    private int psIndex;

    public Record(LocalDateTime dateTime, double speed, double accuracy, ArrayList<Problem> probSet) {
        setSpeed(speed);
        setAccuracy(accuracy);
        setDateTime(dateTime);
        setProbSet(probSet);
        psIndex = probSet.hashCode();
    }

    public Record(LocalDateTime dateTime, double speed, double accuracy, ArrayList<Problem> probSet, int psIndex) {
        setSpeed(speed);
        setAccuracy(accuracy);
        setDateTime(dateTime);
        setProbSet(probSet);
        setPsIndex(psIndex);
    }

    public void print(boolean showProbDetails) {
        // ui.printRecords(showProbDetails, this);

        System.out.println("Date Time: " + getDateTime().format(formatter));
        System.out.println("ProblemSet ID: " + getPsIndex());
        if (showProbDetails) {
            for (Problem problem : probSet) {
                System.out.println("    " + problem.getDescription());
            }
        }
        System.out.println("Speed: " + getSpeed() + " problems per minute");
        System.out.println("Accuracy: " + getAccuracy() * 100 + "%");
    }

    public String writeLine() {
        StringBuilder probStr = new StringBuilder();
        for (Problem problem : probSet) {
            probStr.append(problem.getDescription()).append(",").append(problem.getAnswer());
            probStr.append(" ");
        }

        return getDateTime().format(formatter) + " " + getSpeed() + " " +
                getAccuracy() + " " + getPsIndex() + " " + probStr;
    }

    public int getPsIndex() {
        return psIndex;
    }

    public void setPsIndex(int psIndex) {
        this.psIndex = psIndex;
    }

    public ArrayList<Problem> getProbSet() {
        return probSet;
    }

    public void setProbSet(ArrayList<Problem> probSet) {
        this.probSet.addAll(probSet);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
