package musangking;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testGetBy() {
        assertEquals("2026-04-19",
                new Deadline("desc",
                        LocalDate.parse("2026-04-19")).getBy());
    }

    @Test
    public void testMarkAndUnmarkDone() {
        Deadline deadline =new Deadline("desc",
                LocalDate.parse("2026-04-19"));
        deadline.markDone();
        assertTrue(deadline.isDone());
        deadline.unmarkDone();
        assertFalse(deadline.isDone());
    }

    @Test
    public void testToString() {
        Deadline deadline =new Deadline("desc",
                LocalDate.parse("2026-04-19"));
        deadline.markDone();
        assertEquals("[D][X] desc (by: 2026-04-19)", deadline.toString());
        deadline.unmarkDone();
        assertEquals("[D][ ] desc (by: 2026-04-19)", deadline.toString());
    }
}
