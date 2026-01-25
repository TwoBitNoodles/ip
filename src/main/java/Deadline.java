public class Deadline extends Task {

    /*
     * Public constructor that initializes the task description
     * as determined by the user.
     * @param name the task description.
     */
    public Deadline(String desc) {
        this.desc = desc;
    }

    /*
     * The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s",
                (this.isDone) ? "X" : " ",
                this.desc);
    }
}
