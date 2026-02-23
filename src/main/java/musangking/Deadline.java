package musangking;
import java.time.LocalDate;

public class Deadline extends Task {

    private LocalDate by;

    /**
     * Public constructor initializes a MusangKing.Deadline task with a
     * given description and when the task is due.
     * @param desc : the task description.
     * @param by   : when the task is due.
     */
    public Deadline(String desc, LocalDate by) {
        this.setDesc(desc);
        this.by = by;
        assert !this.isDone() : "tasks should be unmarked when initialised";
    }

    /**
     * @return : string representation of when the deadline is due.
     */
    public String getBy() {
        return this.by.toString();
    }

    /**
     * Modifies when the deadline is due.
     * @param by : new due date.
     */
    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * @return : string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                (this.isDone()) ? "X" : " ",
                this.getDesc(),
                this.by);
    }
}
