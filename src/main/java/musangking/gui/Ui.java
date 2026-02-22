package musangking.gui;

public class Ui {

    public static final DisplayMessage GREETING = new GreetingMessage();
    public static final DisplayMessage GOODBYE = new GoodbyeMessage();
    public static final DisplayMessage HELP = new HelpMessage();

    public static class GreetingMessage extends DisplayMessage {
        private GreetingMessage() {
            super("""
                    I AM THE MIGHTY MUSANGKING!
                    With my help, you can achieve ALL your goals!
                    Just tell me what you want to do.
                    """);
        }
    }

    public static class GoodbyeMessage extends DisplayMessage {
        private GoodbyeMessage() {
            super("""
                    Good riddance! Time for me to relax...
                    ...You'll come back though, right?
                    """);
        }
    }

    public static class TaskListMessage extends DisplayMessage {
        public TaskListMessage(String taskList) {
            super(String.format("""
                    OKOK here are ALLL your tasks:
                    %s
                    """, taskList));
        }
    }

    public static class MarkTaskMessage extends DisplayMessage {
        public MarkTaskMessage(String task) {
            super(String.format("""
                    It's about time! What took you so long?
                    %s
                    """, task));
        }
    }

    public static class UnmarkTaskMessage extends DisplayMessage {
        public UnmarkTaskMessage(String task) {
            super(String.format("""
                    Huh? Still not done with this one?
                    %s
                    """, task));
        }
    }

    public static class NewTaskMessage extends DisplayMessage {
        public NewTaskMessage(String task, int count) {
            super(String.format("""
                    Another task? Okay fine, I'll add it:
                    %s
                    That makes a total of %d tasks, you better finish them quickly!
                    """, task, count));
        }
    }

    public static class DeleteTaskMessage extends DisplayMessage {
        public DeleteTaskMessage(String task, int count) {
            super(String.format("""
                    This task is gone, forever. (A long time).
                    %s
                    You still have %d tasks. Don't slack off!
                    """, task, count));
        }
    }

    public static class FindTaskMessage extends DisplayMessage {
        public FindTaskMessage(String matchingTasks) {
            super(String.format("""
                    Are any of these what you're looking for?
                    %s
                    """, matchingTasks));
        }
    }

    public static class HelpMessage extends DisplayMessage {
        private HelpMessage() {
            super("""
                    todo <desc>
                                : creates a new todo-type task with the
                                  description <desc>.
                    deadline <desc> /by <by>
                                : creates a new deadline-type with the
                                  description <desc>, due by <by>.
                    event <desc> /from <start> /to <end>
                                : creates a new event-type task with the
                                  description <desc>, that starts at <start>
                                  and ends at <end>.
                    list        : displays the current list of tasks.
                    find <key>  : lists tasks with descriptions containing <key>.
                    mark <i>    : marks the <i>th task as done.
                    unmark <i>  : marks the <i>th task as not done yet.
                    delete <i>  : deletes the <i>th task.
                    help        : displays a valid list of commands and
                                  what they do.
                    """);
        }
    }
}
