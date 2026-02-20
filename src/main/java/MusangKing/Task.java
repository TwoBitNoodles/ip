package MusangKing;

public abstract class Task {

    protected String desc;
    protected boolean isDone = false;

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

    @Override
    public abstract String toString();
}