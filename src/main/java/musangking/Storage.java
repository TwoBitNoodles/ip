package musangking;

import musangking.gui.DisplayMessage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            return false;
        }
        return true;
    }

    private static boolean fileIsCorrupted() throws IOException {
        if (!Files.isRegularFile(filePath) || !Files.isReadable(filePath)) {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
            return false;
        }
        return true;
    }

    private static void initialiseTaskList(TaskList tasklist) throws IOException {
        List<String[]> lines = readSaveData();
        for (String[] task : lines) {
            String isDone = task[1];
            String desc = task[2];
            Task newTask;
            switch (task[0]) {
                case "T":
                    newTask = new Todo(desc);
                    if (isDone.equals("1")) {
                        newTask.markDone();
                    }
                    tasklist.addTask(newTask);
                    break;
                case "D":
                    LocalDate by = LocalDate.parse(task[3]);
                    newTask = new Deadline(desc, by);
                    if (isDone.equals("1")) {
                        newTask.markDone();
                    }
                    tasklist.addTask(newTask);
                    break;
                case "E":
                    LocalDateTime startDateTime = LocalDateTime.parse(task[3]);
                    LocalDateTime endDateTime = LocalDateTime.parse(task[4]);
                    newTask = new Event(desc, startDateTime, endDateTime);
                    if (isDone.equals("1")) {
                        newTask.markDone();
                    }
                    tasklist.addTask(newTask);
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
                        (todo.isDone) ? 1 : 0,
                        todo.desc));
            } else if (task instanceof Deadline deadline) {
                tempFile.write(String.format("D | %d | %s | %s\n",
                        (deadline.isDone) ? 1 : 0,
                        deadline.desc,
                        deadline.getBy()));
            } else if (task instanceof Event event) {
                tempFile.write(String.format("E | %d | %s | %s | %s\n",
                        (event.isDone) ? 1 : 0,
                        event.desc,
                        event.getStart(),
                        event.getEnd()));
            }
        }
        tempFile.close();
    }
}
