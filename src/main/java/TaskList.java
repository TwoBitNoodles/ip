import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> tasks;

    /*
     * Constructor initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /*
     * Add a task to the list of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /*
     * String representation of the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i = 1;
        for (Task task : this.tasks) {
            s.append(String.format("%d. %s\n", i++, task));
        }
        return s.toString();
    }
}
