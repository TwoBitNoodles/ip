public class Event extends Task {

    /*
     * Public constructor that initializes the task description
     * as determined by the user.
     * @param name the task description.
     */
    public Event(String desc) {
        this.desc = desc;
    }

    /*
     * The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s",
                (this.isDone) ? "X" : " ",
                this.desc);
    }
}
