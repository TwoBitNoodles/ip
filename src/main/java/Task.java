public class Task {
    private final String name;

    /*
     * Public constructor that initializes the name of the task
     * as determined by the user.
     * @param name the task name / description.
     */
    public Task(String name) {
        this.name = name;
    }

    /*
     * The string representation of the task.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
