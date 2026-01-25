public class Event extends Task {

    private final String start;
    private final String end;

    /*
     * Public constructor that initializes the task description
     * as determined by the user.
     * @param name the task description.
     */
    public Event(String desc, String start, String end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    /*
     * The string representation of the task.
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
