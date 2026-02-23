package musangking;

import musangking.gui.DisplayMessage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private static final Path dirPath = Paths.get("data/");
    private static final Path filePath = Paths.get("data/TasklistSaveFile.txt");
    private static final Path tempPath = Paths.get("data/temp.txt");

    public static DisplayMessage initialiseFileManager(TaskList tasklist) throws IOException {
        checkIfDirectoryExists();
        if (!fileExists()) {
            return new DisplayMessage("""
                    New session in progress.
                    No save files were found.
                    Creating new save file...
                    """);
        } else if (fileIsCorrupted()) {
            return new DisplayMessage("""
                    New session in progress.
                    The save file from the previous session is corrupted.
                    Data from the previous session may be lost.
                    Creating new save file...
                    """);
        } else {
            initialiseTaskList(tasklist);
            return new DisplayMessage("""
                    New session in progress.
                    Recovering data from the previous session...
                    """);
        }
    }

    public static void updateFile(TaskList tasklist) throws IOException {
        Files.deleteIfExists(tempPath);
        Files.createFile(tempPath);
        writeData(tasklist);
        Files.deleteIfExists(filePath);
        Files.move(tempPath, filePath);
    }

    private static void checkIfDirectoryExists() throws IOException {
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
    }

    private static boolean fileExists() throws IOException {
        if (Files.exists(filePath)) {
            return true;
        }
        Files.createFile(filePath);
        return false;
    }

    private static boolean fileIsCorrupted() throws IOException {
        if (Files.isRegularFile(filePath) || Files.isReadable(filePath)) {
            return false;
        }
        Files.deleteIfExists(filePath);
        Files.createFile(filePath);
        return true;
    }

    private static void initialiseTaskList(TaskList tasklist) throws IOException {
        List<String[]> lines = readSaveData();
        for (String[] task : lines) {
            switch (task[0]) {
                case "T":
                    addTodoTask(task, tasklist);
                    break;
                case "D":
                    addDeadlineTask(task, tasklist);
                    break;
                case "E":
                    addEventTask(task, tasklist);
                    break;
            }
        }
    }

    private static List<String[]> readSaveData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
        List<String[]> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line.split(" \\| "));
        }
        br.close();
        return lines;
    }

    private static void writeData(TaskList tasklist) throws IOException {
        FileWriter tempFile = new FileWriter(tempPath.toString(), true);
        for (int i = 0; i < tasklist.count; i++) {
            Task task = tasklist.getTask(i);
            if (task instanceof Todo todo) {
                tempFile.write(String.format("T | %d | %s\n",
                        (todo.isDone()) ? 1 : 0,
                        todo.getDesc()));
            } else if (task instanceof Deadline deadline) {
                tempFile.write(String.format("D | %d | %s | %s\n",
                        (deadline.isDone()) ? 1 : 0,
                        deadline.getDesc(),
                        deadline.getBy()));
            } else if (task instanceof Event event) {
                tempFile.write(String.format("E | %d | %s | %s | %s\n",
                        (event.isDone()) ? 1 : 0,
                        event.getDesc(),
                        event.getStart(),
                        event.getEnd()));
            }
        }
        tempFile.close();
    }

    private static void addTodoTask(String[] task, TaskList tasklist) {
        String isDone = task[1], desc = task[2];
        Todo todo = new Todo(desc);
        markTaskIfDone(todo, isDone);
        tasklist.addTask(todo);
    }

    private static void addDeadlineTask(String[] task, TaskList tasklist) {
        assert isValidDateString(task[3]) : "the date should be in valid format";
        String isDone = task[1], desc = task[2];
        LocalDate by = LocalDate.parse(task[3]);
        Deadline deadline = new Deadline(desc, by);
        markTaskIfDone(deadline, isDone);
        tasklist.addTask(deadline);
    }

    private static void addEventTask(String[] task, TaskList tasklist) {
        assert isValidDateTimeString(task[3]) : "the datetime should be in valid format";
        assert isValidDateTimeString(task[4]) : "the datetime should be in valid format";
        String isDone = task[1], desc = task[2];
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(task[3], formatter),
                end = LocalDateTime.parse(task[4], formatter);
        Event event = new Event(desc, start, end);
        markTaskIfDone(event, isDone);
        tasklist.addTask(event);
    }

    private static void markTaskIfDone(Task task, String isDone) {
        if (isDone.equals("1")) {
            task.markDone();
        }
    }

    private static boolean isValidDateString(String input) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidDateTimeString(String input) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
