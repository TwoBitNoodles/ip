package musangking;

public abstract class Task {

    private String desc;
    private boolean isDone = false;

    public String getDesc() {
        return this.desc;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done yet.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Set the task description.
     * @param desc : the task description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public abstract String toString();
}