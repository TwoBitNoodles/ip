package musangking;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> tasks;
    public int count;

    /**
     * Public constructor initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Add a task to the list of tasks.
     * @param task : the new task to be added.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        this.count++;
        return task.toString();
    }

    /**
     * Mark a task as done.
     * @param taskNo : specifies the task that should be marked as done.
     * @return the string representation of the task after being marked done.
     */
    public String markTaskDone(int taskNo) {
        Task markedTask = this.tasks.get(taskNo-1);
        markedTask.markDone();
        return markedTask.toString();
    }

    /**
     * Mark a task as not done yet.
     * @param taskNo : specifies the task that should be marked as not done yet.
     * @return the string representation of the task after being unmarked.
     */
    public String unmarkTaskDone(int taskNo) {
        Task markedTask = this.tasks.get(taskNo-1);
        markedTask.unmarkDone();
        return markedTask.toString();
    }

    /**
     * Deletes a task based on the given task number.
     * @param taskNo : the index of the task to be deleted.
     * @return       : the string representation of the task to be deleted.
     */
    public String deleteTask(int taskNo) {
        Task deletedTask = this.tasks.get(taskNo-1);
        this.tasks.remove(taskNo-1);
        this.count--;
        return deletedTask.toString();
    }

    /**
     * @param key : keyword to search for.
     * @return    : the string representation of a list of matching tasks.
     */
    public String findTask(String key) {
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.desc.contains(key)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks.toString();
    }

    /**
     * @return the string representation of the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        int i = 1;
        for (Task task : this.tasks) {
            s.append(String.format("%d.%s\n", i++, task));
        }
        return s.toString();
    }
}
