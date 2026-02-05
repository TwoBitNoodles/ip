public class Deadline extends Task {

    public final String by;

    /**
     * Public constructor initializes a Deadline task with a
     * given description and when the task is due.
     * @param desc : the task description.
     * @param by   : when the task is due.
     */
    public Deadline(String desc, String by) {
        this.desc = desc;
        this.by = by;
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
