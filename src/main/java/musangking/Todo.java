package musangking;

public class Todo extends Task {

    /**
     * Public constructor initializes a MusangKing.Todo task with a given description.
     * @param desc : the task description.
     */
    public Todo(String desc) {
        this.setDesc(desc);
        assert !this.isDone() : "tasks should be unmarked when initialised";
    }

    /**
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                (this.isDone()) ? "X" : " ",
                this.getDesc());
    }
}
