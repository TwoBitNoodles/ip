package MusangKing;
import java.time.LocalDate;

public class Deadline extends Task {

    private final LocalDate by;

    /**
     * Public constructor initializes a MusangKing.Deadline task with a
     * given description and when the task is due.
     * @param desc : the task description.
     * @param by   : when the task is due.
     */
    public Deadline(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    public String getBy() {
        return this.by.toString();
    }

    /**
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                (this.isDone) ? "X" : " ",
                this.desc,
                this.by);
    }
}
