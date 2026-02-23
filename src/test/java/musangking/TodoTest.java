package musangking;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testMarkAndUnmarkDone() {
        Todo todo = new Todo("desc");
        todo.markDone();
        assertTrue(todo.isDone());
        todo.unmarkDone();
        assertFalse(todo.isDone());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("desc");
        todo.markDone();
        assertEquals("[T][X] desc", todo.toString());
        todo.unmarkDone();
        assertEquals("[T][ ] desc", todo.toString());
    }
}
