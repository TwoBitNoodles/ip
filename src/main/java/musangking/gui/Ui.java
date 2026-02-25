package musangking.gui;

public class Ui {

    public static final DisplayMessage GREETING = new GreetingMessage();
    public static final DisplayMessage GOODBYE = new GoodbyeMessage();
    public static final DisplayMessage HELP = new HelpMessage();

    public static class GreetingMessage extends DisplayMessage {
        private GreetingMessage() {
            super("I AM THE MIGHTY MUSANGKING! " +
                    "With my help, you can achieve ALL your goals! " +
                    "Just tell me what you want to do.");
        }
    }

    public static class GoodbyeMessage extends DisplayMessage {
        private GoodbyeMessage() {
            super("Good riddance! Time for me to relax... " +
                  "...You'll come back though, right?");
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

    public static class UpdateTaskMessage extends DisplayMessage {
        public UpdateTaskMessage(String task) {
            super(String.format("""
                    What do you need to change this task for? Tsk... so troublesome... here is the updated task:
                    %s
                    """, task));
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
            super("todo <desc>:\n" +
                    "creates a new todo-type task with the description <desc>.\n" +
                    "\n" +
                    "deadline <desc> /by <by>:\n" +
                    "creates a new deadline-type task with the " +
                    "description <desc>, due by <by>. <by> must be in the following format: YYYY-MM-DD\n" +
                    "\n" +
                    "event <desc> /from <start> /to <end>:\n" +
                    "creates a new event-type task with the " +
                    "description <desc>, that starts at <start> " +
                    "and ends at <end>. <start> and <end> must be in " +
                    "the following format: YYYY-MM-DD HH:MM\n" +
                    "\n" +
                    "edit <i> <field> /change <change>:\n" +
                    "updates the <field> field of the <i>th task with <change>.\n" +
                    "\n" +
                    "list:\n" +
                    "displays the current list of tasks.\n" +
                    "\n" +
                    "find <key>:\n" +
                    "lists tasks with descriptions containing <key>.\n" +
                    "\n" +
                    "mark <i>:\n" +
                    "marks the <i>th task as done.\n" +
                    "\n" +
                    "unmark <i>:\n" +
                    "marks the <i>th task as not done yet.\n" +
                    "\n" +
                    "delete <i>:\n" +
                    "deletes the <i>th task.\n" +
                    "\n" +
                    "help:\n" +
                    "displays a list of valid commands and what they do.");
        }
    }
}
