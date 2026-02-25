package musangking;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        assert (taskNo > 0 && taskNo <= this.count) : "taskNo should be valid";
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
        assert (taskNo > 0 && taskNo <= this.count) : "taskNo should be valid";
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
        assert (taskNo > 0 && taskNo <= this.count) : "taskNo should be valid";
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
            if (task.getDesc().contains(key)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks.toString();
    }

    /**
     * Modifies the task description of a given task.
     * @param idx  : the index of the task to be updated.
     * @param desc : the new task description.
     */
    public void updateTaskDesc(int idx, String desc) {
        this.getTask(idx-1).setDesc(desc);
    }

    /**
     * Modifies the due date of a deadline task.
     * @param idx : the index of the task to be updated.
     * @param by  : the new due date.
     */
    public void updateDeadlineBy(int idx, LocalDate by) {
        assert this.getTask(idx-1) instanceof Deadline
                : "the task to be updated should be a deadline task";
        Deadline deadline = (Deadline) this.getTask(idx-1);
        deadline.setBy(by);
    }

    /**
     * Modifies the starting datetime of an event task.
     * @param idx   : the index of the task to be updated.
     * @param start : the new starting datetime.
     */
    public void updateEventStart(int idx, LocalDateTime start) {
        assert this.getTask(idx-1) instanceof Event
                : "the task to be updated should be a deadline task";
        Event event =  (Event) this.getTask(idx-1);
        event.setStart(start);
    }

    /**
     * Modifies the ending datetime of an event task.
     * @param idx : the index of the task to be updated.
     * @param end : the new ending datetime.
     */
    public void updateEventEnd(int idx, LocalDateTime end) {
        assert this.getTask(idx-1) instanceof Event
                : "the task to be updated should be a deadline task";
        Event event =  (Event) this.getTask(idx-1);
        event.setEnd(end);
    }

    /**
     * @return the string representation of the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        int i = 1;
        for (Task task : this.tasks) {
            s.append(String.format("%d. %s\n", i++, task));
        }
        return s.toString();
    }
}
