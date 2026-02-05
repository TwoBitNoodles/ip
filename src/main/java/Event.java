public class Event extends Task {

    public final String start;
    public final String end;

    /**
     * Public constructor initializes an Event task with a
     * given description, when the task starts and when the task ends.
     * @param desc  : the task description.
     * @param start : when the task starts.
     * @param end   : when the task ends
     */
    public Event(String desc, String start, String end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    /**
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                (this.isDone) ? "X" : " ",
                this.desc,
                this.start,
                this.end);
    }
}
