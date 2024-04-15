package seedu.duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    public static void testSortRecords() {
        Record record1 = new Record(LocalDateTime.now().minusDays(2), 5.0, 0.8, new ArrayList<>(), "user-DIY");
        Record record2 = new Record(LocalDateTime.now().minusDays(1), 6.0, 0.7, new ArrayList<>(), "auto-generated");
        Record record3 = new Record(LocalDateTime.now(), 7.0, 0.9, new ArrayList<>(), "auto-generated");
        Storage.addRecord(record1);
        Storage.addRecord(record2);
        Storage.addRecord(record3);

        ArrayList<Record> sortedByDate = Storage.sortRecords(1, 0, 0, 0);
        assertEquals(record1, sortedByDate.get(0));
        assertEquals(record2, sortedByDate.get(1));
        assertEquals(record3, sortedByDate.get(2));

        ArrayList<Record> reverseSortedByDate = Storage.sortRecords(2, 0, 0, 0);
        assertEquals(record3, reverseSortedByDate.get(0));
        assertEquals(record2, reverseSortedByDate.get(1));
        assertEquals(record1, reverseSortedByDate.get(2));

        ArrayList<Record> sortedBySpeed = Storage.sortRecords(0, 1, 0, 0);
        assertEquals(record1, sortedBySpeed.get(0));
        assertEquals(record2, sortedBySpeed.get(1));
        assertEquals(record3, sortedBySpeed.get(2));

        ArrayList<Record> reverseSortedBySpeed = Storage.sortRecords(0, 2, 0, 0);
        assertEquals(record3, reverseSortedBySpeed.get(0));
        assertEquals(record2, reverseSortedBySpeed.get(1));
        assertEquals(record1, reverseSortedBySpeed.get(2));

        ArrayList<Record> sortedByAccuracy = Storage.sortRecords(0, 0, 1, 0);

        assertEquals(record2, sortedByAccuracy.get(0));
        assertEquals(record1, sortedByAccuracy.get(1));
        assertEquals(record3, sortedByAccuracy.get(2));

        ArrayList<Record> reverseSortedByAccuracy = Storage.sortRecords(0, 0, 2, 0);
        assertEquals(record3, reverseSortedByAccuracy.get(0));
        assertEquals(record1, reverseSortedByAccuracy.get(1));
        assertEquals(record2, reverseSortedByAccuracy.get(2));

        ArrayList<Record> sortedByProblemIndex = Storage.sortRecords(0, 0, 0, 1);
        assertEquals(record1, sortedByProblemIndex.get(0));
        assertEquals(record2, sortedByProblemIndex.get(1));
        assertEquals(record3, sortedByProblemIndex.get(2));

        ArrayList<Record> reverseSortedByProblemIndex = Storage.sortRecords(0, 0, 0, 2);
        assertEquals(record3, reverseSortedByProblemIndex.get(0));
        assertEquals(record2, reverseSortedByProblemIndex.get(1));
        assertEquals(record1, reverseSortedByProblemIndex.get(2));
    }

    public static void testAddRecord() {
        Record record = new Record(LocalDateTime.now(), 5.0, 0.8, new ArrayList<>(), "auto-generated");
        Storage.addRecord(record);
        assertEquals(1, Storage.getRecords().size());
        assertEquals(record, Storage.getRecords().get(0));
    }

    public static void testClearRecords() {
        Record record1 = new Record(LocalDateTime.now(), 5.0, 0.8, new ArrayList<>(), "auto-generated");
        Record record2 = new Record(LocalDateTime.now(), 6.0, 0.7, new ArrayList<>(), "user-DIY");
        Storage.addRecord(record1);
        Storage.addRecord(record2);
        assertEquals(2, Storage.getRecords().size());
        Storage.clearRecords();
        assertEquals(0, Storage.getRecords().size());
    }

    public static void testProcessLine() {
        String line = "2024-04-04 10:30:00 5.0 0.8 1234 auto-generated problem1,1.0 problem2,2.0";
        try {
            Storage.processLine(line);
            assertEquals(1, Storage.getRecords().size());
            Record record = Storage.getRecords().get(0);
            assertEquals(5.0, record.getSpeed(), 0.001);
            assertEquals(0.8, record.getAccuracy(), 0.001);
            assertEquals(1234, record.getPsIndex());
            List<Problem> probSet = record.getProbSet();
            assertEquals(2, probSet.size());
            assertEquals("problem1", probSet.get(0).getDescription());
            assertEquals(1.0, probSet.get(0).getAnswer(), 0.001);
            assertEquals("problem2", probSet.get(1).getDescription());
            assertEquals(2.0, probSet.get(1).getAnswer(), 0.001);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    public static void main(String[] args) {
        Storage.clearRecords();
        testAddRecord();
        System.out.println("pass add record test!");
        Storage.clearRecords();
        testClearRecords();
        System.out.println("pass clear record test!");
        Storage.clearRecords();
        testProcessLine();
        System.out.println("pass process line test!");
        Storage.clearRecords();
        testSortRecords();
        System.out.println("pass sort records test!");
    }

}
