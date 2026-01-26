public class Parser {

    private final TaskList taskList = new TaskList();

    /**
     * This is the main method that parses user input.
     * Given user input, it displays an appropriate response and
     * carries out the relevant operations.
     * @param input : the user's input.
     */
    public void parse(String input) {
        input = input.strip().replaceAll("\\s+", " ");
        if (input.toLowerCase().contains("bye")) {
            byeResponse();

        } else if (input.equalsIgnoreCase("list")) {
            listResponse();

        } else if (input.toLowerCase().startsWith("mark ")) {
            markResponse(input.substring(5));

        } else if (input.toLowerCase().startsWith("unmark ")) {
            unmarkResponse(input.substring(7));

        } else if (input.toLowerCase().startsWith("todo ")) {
            createTodo(input.substring(5));

        } else if (input.toLowerCase().startsWith("deadline ")) {
            createDeadline(input.substring(9));

        } else if (input.toLowerCase().startsWith("event ")) {
            createEvent(input.substring(6));

        } else {
            noMatchingCommandResponse();
        }
    }

    /**
     * Displays a goodbye message and exits the chat loop.
     */
    private void byeResponse() {
        print("Bye! Come back, okay?\n");
        MusangKing.flag = false;
    }

    /**
     * Displays the string representation of the current list of tasks.
     */
    private void listResponse() {
        print(this.taskList.toString());
    }

    /**
     * Marks the given task as done.
     */
    private void markResponse(String input) {
        int taskNo = Integer.parseInt(input);
        print("Yay! One less task to do!\n" +
                "  " + this.taskList.markTaskDone(taskNo) + "\n");
    }

    /**
     * Marks the given task as not done yet.
     */
    private void unmarkResponse(String input) {
        int taskNo = Integer.parseInt(input);
        print("Aw man... back to the task list...\n" +
                "  " + this.taskList.unmarkTaskDone(taskNo) + "\n");
    }

    /**
     * Creates a new Todo task.
     * @param input : a description of the new Todo task.
     */
    private void createTodo(String input) {
        Task newTask = new Todo(input); // assume for now the input is valid
        addTaskResponse(newTask);
    }

    /**
     * Creates a new Deadline task.
     * @param input : a description of the new Deadline task,
     *              followed by when it is due, in the following format: <desc> /by <by>.
     */
    private void createDeadline(String input) {
        String[] args = input.split(" /by "); // assume for now the input is valid
        Task newTask = new Deadline(args[0], args[1]);
        addTaskResponse(newTask);
    }

    /**
     * Creates a new Event task.
     * @param input : a description of the new Event task, followed by when it starts
     *              and when it ends, in the following format: <desc> /from <start> /to <end>.
     */
    private void createEvent(String input) {
        String[] args = input.split(" /from | /to "); // assume for now the input is valid
        Task newTask = new Event(args[0], args[1], args[2]);
        addTaskResponse(newTask);
    }

    /**
     * Adds a newly created task to the task list and
     * displays its string representation once added.
     * @param task : a newly created task.
     */
    private void addTaskResponse(Task task) {
        this.taskList.addTask(task);
        print(String.format("""
                Oookay! I've added this task:
                %s
                That makes %d tasks in the list!
                """,
                task, taskList.count));
    }

    /**
     * Displays an error message in the event that user input does
     * not match any of the known commands.
     */
    private void noMatchingCommandResponse() {
        print("Hmm? I'm not sure what you want me to do...\n");
    }

    /**
     * Static helper function simplifies printing messages
     * between horizontal lines.
     * @param msg : the message to be sandwiched between horizontal lines.
     */
    public static void print(String msg) {
        String line = "_".repeat(60) + "\n";
        System.out.println(line + msg + line);
    }
}
