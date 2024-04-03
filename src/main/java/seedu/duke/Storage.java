package seedu.duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class for reading & writing input/output to file
 */
public class Storage {

    private static final String filePath = "recordList.txt";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final List<Record> records = new ArrayList<>();

    public static void addRecord(Record record) {
        records.add(record);
    }

    public static void clearRecords() {
        records.clear();
    }

    public static ArrayList<Record> sortRecords(int dateSortOp, int spdSortOp, int accSortOp, int probSortOp) {
        int notSortOp = 0;
        int toSort = 1;
        int reverseSortOp = 2;
        ArrayList<Record> sortedRecords = new ArrayList<>(records);
        if (dateSortOp != notSortOp) {
            sortedRecords.sort(Comparator.comparing(Record::getDateTime));
            if(dateSortOp == reverseSortOp) {
                Collections.reverse(sortedRecords);
            }
        } else if(spdSortOp != notSortOp) {
            sortedRecords.sort(Comparator.comparing(Record::getSpeed));
            if(spdSortOp == reverseSortOp) {
                Collections.reverse(sortedRecords);
            }
        } else if(accSortOp != notSortOp) {
            sortedRecords.sort(Comparator.comparing(Record::getDateTime));
            if(accSortOp == reverseSortOp) {
                Collections.reverse(sortedRecords);
            }
        } else if(probSortOp != notSortOp) {
            sortedRecords.sort(Comparator.comparing(Record::getPsIndex));
            if(probSortOp == reverseSortOp) {
                Collections.reverse(sortedRecords);
            }
        }
        return sortedRecords;
    }

    /**
     * Method for processing a line of input
     * @param line the line to be processed
     * @throws Exception exception is thrown whenever the input format is corrupt.
     */
    public static void processLine(String line) throws Exception {
        int minimumLength = 4;

        String[] words = line.split(" ");

        if (words.length < minimumLength ) {
            throw new Exception();
        }

        LocalDateTime dateTime = LocalDateTime.parse(words[0] + " " + words[1], formatter);
        double speed = Double.parseDouble(words[2]);
        double accuracy = Double.parseDouble(words[3]);

        int psIndex = Integer.parseInt(words[4]);

        ArrayList<Problem> probSet = new ArrayList<>();

        for (int i = minimumLength + 1; i < words.length; i++) {
            String[] term = words[i].split(",");
            probSet.add(new Problem(term[0], Double.parseDouble(term[1])));
        }

        Record record = new Record(dateTime, speed, accuracy, probSet, psIndex);
        addRecord(record);
    }

    /**
     * Method for reading the input file
     */
    public static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    processLine(line);
                }
                System.out.println("Storage: Past records successfully loaded!");
            } catch (Exception e) {
                clearRecords();
                System.out.println("Storage: Past record save file corrupt! Records cleared!");
            }
        } catch (IOException e) {
            clearRecords();
            System.out.println("Storage: No past records found. Starting anew!");
        }
    }

    /**
     * Method for writing all your previous records to the file
     */
    public static void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Record record : records) {
                writer.write(record.writeLine());
                writer.newLine();
            }
            writer.flush();
            System.out.println("Record successfully saved!");
        } catch (IOException e) {
            System.out.println("Error when saving record!");
            e.printStackTrace();
        }
    }
}

