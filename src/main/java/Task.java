public class Task {
    private final String name;
    private boolean isDone = false;

    /*
     * Public constructor that initializes the name of the task
     * as determined by the user.
     * @param name the task name / description.
     */
    public Task(String name) {
        this.name = name;
    }

    /*
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /*
     * Mark the task as not done yet.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /*
     * The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                (this.isDone) ? "X" : " ",
                this.name);
    }
}
