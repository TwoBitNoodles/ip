package musangking;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testGetStartAndEnd() {
        Event event = new Event("task",
                LocalDateTime.parse("2026-04-19T14:00"),
                LocalDateTime.parse("2026-04-19T18:00"));
        assertEquals("2026-04-19 14:00", event.getStart());
        assertEquals("2026-04-19 18:00", event.getEnd());
    }

    @Test
    public void testMarkAndUnmarkDone() {
        Event event = new Event("task",
                LocalDateTime.parse("2026-04-19T14:00"),
                LocalDateTime.parse("2026-04-19T18:00"));
        event.markDone();
        assertTrue(event.isDone());
        event.unmarkDone();
        assertFalse(event.isDone());
    }

    @Test
    public void testToString() {
        Event event = new Event("task",
                LocalDateTime.parse("2026-04-19T14:00"),
                LocalDateTime.parse("2026-04-19T18:00"));
        event.markDone();
        assertEquals(
                "[E][X] task (from: 2026-04-19 14:00 to: 2026-04-19 18:00)",
                event.toString());
        event.unmarkDone();
        assertEquals(
                "[E][ ] task (from: 2026-04-19 14:00 to: 2026-04-19 18:00)",
                event.toString());
    }
}
