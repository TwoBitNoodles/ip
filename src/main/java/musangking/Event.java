package musangking;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Public constructor initializes an MusangKing.Event task with a
     * given description, when the task starts and when the task ends.
     * @param desc  : the task description.
     * @param start : when the task starts.
     * @param end   : when the task ends
     */
    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    /**
     * @return : the string representation of when the event begins.
     */
    public String getStart() {
        return this.start.format(this.formatter);
    }

    /**
     * @return : the string representation of when the event ends.
     */
    public String getEnd() {
        return this.end.format(this.formatter);
    }

    /**
     * @return : the string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                (this.isDone) ? "X" : " ",
                this.desc,
                this.getStart(),
                this.getEnd());
    }
}
