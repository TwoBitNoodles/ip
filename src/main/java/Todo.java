public class Todo extends Task {

    /*
     * Public constructor that initializes the task description
     * as determined by the user.
     * @param name the task description.
     */
    public Todo(String desc) {
        this.desc = desc;
    }

    /*
     * The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                (this.isDone) ? "X" : " ",
                this.desc);
    }
}
