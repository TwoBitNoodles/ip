import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> tasks;
    public int count;

    /*
     * Public constructor initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /*
     * Add a task to the list of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.count++;
    }

    /*
     * Mark a task as done.
     * @param taskNo specifies the task that should be marked as done.
     * @return the string representation of the task after being marked done.
     */
    public String markTaskDone(int taskNo) {
        Task markedTask = this.tasks.get(taskNo-1);
        markedTask.markDone();
        return markedTask.toString();
    }

    /*
     * Mark a task as not done yet.
     * @param taskNo specifies the task that should be marked as not done yet.
     * @return the string representation of the task after being unmarked.
     */
    public String unmarkTaskDone(int taskNo) {
        Task markedTask = this.tasks.get(taskNo-1);
        markedTask.unmarkDone();
        return markedTask.toString();
    }

    /*
     * String representation of the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Here are the tasks in your list:\n");
        int i = 1;
        for (Task task : this.tasks) {
            s.append(String.format("%d.%s\n", i++, task));
        }
        return s.toString();
    }
}
