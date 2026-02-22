package musangking;  //same package as the class being tested

import musangking.gui.DisplayMessage;
import musangking.gui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private final Parser parser = new Parser();
    private final String line = "_".repeat(60) + "\n";

    @Test
    public void testParse_bye() {
        TaskList tasklist = new TaskList();
        String[] inputs = {"bye", "BYE", "bYe"};
        for (String input : inputs) {
            DisplayMessage dm = parser.parse(tasklist, input);
            assertEquals(Ui.GOODBYE, dm);
        }
    }

    @Test
    public void testParse_addTasks() {
        TaskList tasklist = new TaskList();
        DisplayMessage todoDm = parser.parse(tasklist, "todo desc");
        DisplayMessage deadlineDm = parser.parse(tasklist,
                "deadline desc /by 2026-04-19");
        DisplayMessage eventDm = parser.parse(tasklist,
                "event desc /from 2026-04-19 14:00 /to 2026-04-19 18:00");
        assertEquals("""
                    Another task? Okay fine, I'll add it:
                    [T][ ] desc
                    That makes a total of 1 tasks, you better finish them quickly!
                    """, todoDm.toString());
        assertEquals("""
                    Another task? Okay fine, I'll add it:
                    [D][ ] desc (by: 2026-04-19)
                    That makes a total of 2 tasks, you better finish them quickly!
                    """, deadlineDm.toString());
        assertEquals("""
                    Another task? Okay fine, I'll add it:
                    [E][ ] desc (from: 2026-04-19 14:00 to: 2026-04-19 18:00)
                    That makes a total of 3 tasks, you better finish them quickly!
                    """, eventDm.toString());
    }

    @Test
    public void parse_markAndUnmarkTask_invalidTaskNo_exceptionThrown() {
        TaskList tasklist = initialiseTaskList();
        String[] integerInputs = {
                "mark 0", "unmark 0",
                "mark 4", "unmark 4",
                "mark -1", "unmark -1",
        };
        String[] garbageInputs = {"mark garbage", "unmark garbage"};
        for (String input : integerInputs) {
            try {
                parser.parse(tasklist, input);
                fail();
            } catch (Exception e) {
                assertEquals(line + """
                        What? Please enter a valid task number!
                        """ + line, e.getMessage());
            }
        }
        for (String input : garbageInputs) {
            try {
                parser.parse(tasklist, input);
                fail();
            } catch (Exception e) {
                assertEquals(line + """
                        The task number is supposed to be an integer!
                        """ + line, e.getMessage());
            }
        }
    }

    private TaskList initialiseTaskList() {
        TaskList tasklist = new TaskList();
        Task[] tasks = {
                new Todo("desc"),
                new Deadline("desc", LocalDate.parse("2026-04-19")),
                new Event("desc",
                        LocalDateTime.parse("2026-04-19T14:00"),
                        LocalDateTime.parse("2026-04-19T18:00"))
        };
        for (Task task : tasks) {
            tasklist.addTask(task);
        }
        return tasklist;
    }
}
