public class Deadline extends Task {

    private final String dueDate;

    /*
     * Public constructor that initializes the task description
     * as determined by the user.
     * @param name the task description.
     */
    public Deadline(String desc, String dueDate) {
        this.desc = desc;
        this.dueDate = dueDate;
    }

    /*
     * The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                (this.isDone) ? "X" : " ",
                this.desc,
                this.dueDate);
    }
}
