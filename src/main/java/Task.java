public class Task {
    private final String name;
    private boolean isDone = false;
    private final TaskType type;

    /*
     * Public constructor that initializes the name of the task
     * as determined by the user.
     * @param name the task name / description.
     */
    public Task(String name, TaskType type) {
        this.name = name;
        this.type = type;
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
        return String.format("[%s][%s] %s",
                (this.type.equals(TaskType.TODO)) ? "T"
                        : (this.type.equals(TaskType.DEADLINE)) ? "D" : "E",
                (this.isDone) ? "X" : " ",
                this.name);
    }
}