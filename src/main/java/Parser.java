public class Parser {

    private final TaskList taskList = new TaskList();

    /**
     * Takes in user input and carries out the relevant
     * operations and appropriate response.
     * @param input : the user's input.
     * @return      : DisplayMessage containing the response to be displayed.
     */
    public DisplayMessage parse(String input) {
        // remove trailing whitespace and
        // replace multiple whitespaces with a single whitespace.
        input = input.strip().replaceAll("\\s+", " ");

        if (input.equalsIgnoreCase("bye")) {
            return byeResponse();

        } else if (input.equalsIgnoreCase("list")) {
            return listResponse();

        } else if (input.toLowerCase().matches("^mark\\b.*$")) {
            return markResponse(input.substring(4).strip());

        } else if (input.toLowerCase().matches("^unmark\\b.*$")) {
            return unmarkResponse(input.substring(6).strip());

        } else if (input.toLowerCase().matches("^todo\\b.*$")) {
            return createTodo(input.substring(4).strip());

        } else if (input.toLowerCase().matches("^deadline\\b.*$")) {
            return createDeadline(input.substring(8).strip());

        } else if (input.toLowerCase().matches("^event\\b.*$")) {
            return createEvent(input.substring(5).strip());

        } else if ((input.toLowerCase().matches("^delete\\b.*$"))) {
            return deleteTaskResponse(input.substring(6).strip());

        } else if (input.equalsIgnoreCase("help")) {
            return Messages.HELP;

        } else {
            throw Exceptions.INVALID_COMMAND;
        }
    }

    /**
     * Exits the chat loop.
     * @return GOODBYE message.
     */
    private DisplayMessage byeResponse() {
        MusangKing.flag = false;
        return Messages.GOODBYE;
    }

    /**
     * @return : DisplayMessage with the string representation
     *           of the list of tasks.
     */
    private DisplayMessage listResponse() {
        return new Messages.TaskListMessage(this.taskList.toString());
    }

    /**
     * Marks the given task as done.
     * @param input : the task to be marked.
     * @return      : DisplayMessage with the string representation
     *                of the marked task.
     */
    private DisplayMessage markResponse(String input) {
        if (input.isEmpty()) {
            throw new Exceptions.MissingFieldException(
                    "the task you want to mark",
                    "mark it"
            );
        }
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo < 1 || taskNo > taskList.count) {
                throw Exceptions.TASK_OUT_OF_BOUNDS;
            }
            String task = this.taskList.markTaskDone(taskNo);
            return new Messages.MarkTaskMessage(task);
        } catch (NumberFormatException e) {
            throw new Exceptions.InvalidInputException(
                    "task number",
                    "an integer"
            );
        }
    }

    /**
     * Marks the given task as not done yet.
     * @param input : the task to be unmarked.
     * @return      : DisplayMessage with the string representation
     *                of the unmarked task.
     */
    private DisplayMessage unmarkResponse(String input) {
        if (input.isEmpty()) {
            throw new Exceptions.MissingFieldException(
                    "the task you want to unmark",
                    "unmark it"
            );
        }
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo < 1 || taskNo > taskList.count) {
                throw Exceptions.TASK_OUT_OF_BOUNDS;
            }
            String task = this.taskList.unmarkTaskDone(taskNo);
            return new Messages.UnmarkTaskMessage(task);
        } catch (NumberFormatException e) {
            throw new Exceptions.InvalidInputException(
                    "task number",
                    "an integer"
            );
        }
    }

    /**
     * Creates a new Todo task.
     * @param input : a description of the new Todo task.
     * @return      : DisplayMessage object.
     */
    private DisplayMessage createTodo(String input) {
        if (input.isEmpty()) {
            throw new Exceptions.MissingFieldException(
                    "your task description",
                    "add your todo task to the list"
            );
        }
        Task newTask = new Todo(input);
        return addTaskResponse(newTask);
    }

    /**
     * Creates a new Deadline task.
     * @param input : a description of the new Deadline task, followed
     *                by when it is due, in the following format: <desc> /by <by>.
     * @return      : DisplayMessage object.
     */
    private DisplayMessage createDeadline(String input) {
        if (input.isEmpty()) {
            throw new Exceptions.MissingFieldException(
                    "your task description",
                    "add your deadline task to the list"
            );
        } else if (!input.contains(" /by ")) {
            throw new Exceptions.MissingFieldException(
                    "when your task is due",
                    "add your deadline task to the list"
            );
        }
        String[] args = input.split(" /by "); // assume for now the input is valid
        Task newTask = new Deadline(args[0], args[1]);
        return addTaskResponse(newTask);
    }

    /**
     * Creates a new Event task.
     * @param input : a description of the new Event task, followed by when it starts
     *                and when it ends, in the following format:
     *                <desc> /from <start> /to <end>.
     * @return      : DisplayMessage object.
     */
    private DisplayMessage createEvent(String input) {
        if (input.isEmpty()) {
            throw new Exceptions.MissingFieldException(
                    "your task description",
                    "add your event task to the list"
            );
        } else if (!input.contains(" /from ")) {
            throw new Exceptions.MissingFieldException(
                    "when your task begins",
                    "add your event task to the list"
            );
        } else if (!input.contains(" /to ")) {
            throw new Exceptions.MissingFieldException(
                    "when your task ends",
                    "add your event task to the list"
            );
        }
        String[] args = input.split(" /from | /to ");
        Task newTask = new Event(args[0], args[1], args[2]);
        return addTaskResponse(newTask);
    }

    /**
     * Adds a newly created task to the task list.
     * @param task : a newly created task.
     * @return     : DisplayMessage with the string representation
     *               of the newly added task and the new total number
     *               of tasks in the task list.
     */
    private DisplayMessage addTaskResponse(Task task) {
        String newTask = this.taskList.addTask(task);
        return new Messages.NewTaskMessage(newTask, this.taskList.count);
    }

    /**
     * Deletes a task from the task list
     * @param input : the task to be deleted.
     * @return      : DisplayMessage with the string representation
     *                of the task to be deleted.
     */
    private DisplayMessage deleteTaskResponse(String input) {
        if (input.isEmpty()) {
            throw new Exceptions.MissingFieldException(
                    "the task you want to delete",
                    "delete it"
            );
        }
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo < 1 || taskNo > taskList.count) {
                throw Exceptions.TASK_OUT_OF_BOUNDS;
            }
            String task = this.taskList.deleteTask(taskNo);
            return new Messages.DeleteTaskMessage(task, this.taskList.count);
        } catch (NumberFormatException e) {
            throw new Exceptions.InvalidInputException(
                    "task number",
                    "an integer"
            );
        }
    }
}
