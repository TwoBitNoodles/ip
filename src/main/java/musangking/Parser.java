package musangking;

import musangking.gui.DisplayMessage;
import musangking.gui.MusangKing;
import musangking.gui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Takes in user input and carries out the relevant
     * operations and appropriate response.
     * @param input : the user's input.
     * @return      : MusangKing.DisplayMessage containing the response to be displayed.
     */
    public DisplayMessage parse(TaskList taskList, String input) {
        // remove trailing whitespace and
        // replace multiple whitespaces with a single whitespace.
        input = input.strip().replaceAll("\\s+", " ");

        if (input.equalsIgnoreCase("bye")) {
            return byeResponse();

        } else if (input.equalsIgnoreCase("list")) {
            return listResponse(taskList);

        } else if (input.toLowerCase().matches("^find\\b.*$")) {
            return findResponse(taskList, input.substring(4).strip());

        } else if (input.toLowerCase().matches("^mark\\b.*$")) {
            return markResponse(taskList, input.substring(4).strip());

        } else if (input.toLowerCase().matches("^unmark\\b.*$")) {
            return unmarkResponse(taskList, input.substring(6).strip());

        } else if (input.toLowerCase().matches("^todo\\b.*$")) {
            return createTodo(taskList, input.substring(4).strip());

        } else if (input.toLowerCase().matches("^deadline\\b.*$")) {
            return createDeadline(taskList, input.substring(8).strip());

        } else if (input.toLowerCase().matches("^event\\b.*$")) {
            return createEvent(taskList, input.substring(5).strip());

        } else if ((input.toLowerCase().matches("^delete\\b.*$"))) {
            return deleteTaskResponse(taskList, input.substring(6).strip());

        } else if (input.equalsIgnoreCase("help")) {
            return Ui.HELP;

        } else {
            throw Exceptions.INVALID_COMMAND;
        }
    }

    /**
     * Exits the chat loop.
     * @return GOODBYE message.
     */
    private DisplayMessage byeResponse() {
        return Ui.GOODBYE;
    }

    /**
     * @return : MusangKing.DisplayMessage with the string representation
     *           of the list of tasks.
     */
    private DisplayMessage listResponse(TaskList taskList) {
        return new Ui.TaskListMessage(taskList.toString());
    }

    private DisplayMessage findResponse(TaskList taskList, String key) {
        return new Ui.FindTaskMessage(taskList.findTask(key));
    }
    /**
     * Marks the given task as done.
     * @param input : the task to be marked.
     * @return      : MusangKing.DisplayMessage with the string representation
     *                of the marked task.
     */
    private DisplayMessage markResponse(TaskList taskList, String input) {
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
            String task = taskList.markTaskDone(taskNo);
            return new Ui.MarkTaskMessage(task);
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
     * @return      : MusangKing.DisplayMessage with the string representation
     *                of the unmarked task.
     */
    private DisplayMessage unmarkResponse(TaskList taskList, String input) {
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
            String task = taskList.unmarkTaskDone(taskNo);
            return new Ui.UnmarkTaskMessage(task);
        } catch (NumberFormatException e) {
            throw new Exceptions.InvalidInputException(
                    "task number",
                    "an integer"
            );
        }
    }

    /**
     * Creates a new MusangKing.Todo task.
     * @param input : a description of the new MusangKing.Todo task.
     * @return      : MusangKing.DisplayMessage object.
     */
    private DisplayMessage createTodo(TaskList taskList, String input) {
        if (input.isEmpty()) {
            throw new Exceptions.MissingFieldException(
                    "your task description",
                    "add your todo task to the list"
            );
        }
        Task newTask = new Todo(input);
        return addTaskResponse(taskList, newTask);
    }

    /**
     * Creates a new MusangKing.Deadline task.
     * @param input : a description of the new MusangKing.Deadline task, followed
     *                by when it is due, in the following format: <desc> /by <by>.
     * @return      : MusangKing.DisplayMessage object.
     */
    private DisplayMessage createDeadline(TaskList taskList, String input) {
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
        LocalDate date = toLocalDate(args[1]);
        Task newTask = new Deadline(args[0], date);
        return addTaskResponse(taskList, newTask);
    }

    /**
     * Creates a new MusangKing.Event task.
     * @param input : a description of the new MusangKing.Event task, followed by when it starts
     *                and when it ends, in the following format:
     *                <desc> /from <start> /to <end>.
     * @return      : MusangKing.DisplayMessage object.
     */
    private DisplayMessage createEvent(TaskList taskList, String input) {
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
        LocalDateTime startDateTime = toLocalDateTime(args[1]);
        LocalDateTime endDateTime = toLocalDateTime(args[2]);
        Task newTask = new Event(args[0], startDateTime, endDateTime);
        return addTaskResponse(taskList, newTask);
    }

    /**
     * Adds a newly created task to the task list.
     * @param task : a newly created task.
     * @return     : MusangKing.DisplayMessage with the string representation
     *               of the newly added task and the new total number
     *               of tasks in the task list.
     */
    private DisplayMessage addTaskResponse(TaskList taskList, Task task) {
        String newTask = taskList.addTask(task);
        return new Ui.NewTaskMessage(newTask, taskList.count);
    }

    /**
     * Deletes a task from the task list
     * @param input : the task to be deleted.
     * @return      : MusangKing.DisplayMessage with the string representation
     *                of the task to be deleted.
     */
    private DisplayMessage deleteTaskResponse(TaskList taskList, String input) {
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
            String task = taskList.deleteTask(taskNo);
            return new Ui.DeleteTaskMessage(task, taskList.count);
        } catch (NumberFormatException e) {
            throw new Exceptions.InvalidInputException(
                    "task number",
                    "an integer"
            );
        }
    }

    /**
     * Converts a valid String into a LocalDate object.
     * @param dateString : the String representation of a date
     * @return           : LocalDate object
     */
    private LocalDate toLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw Exceptions.INVALID_DATE_FORMAT;
        }
    }

    /**
     * Converts a valid String into a LocalDate object.
     * @param datetimeString : the String representation of a date and time
     * @return               : LocalDateTime object
     */
    private LocalDateTime toLocalDateTime(String datetimeString) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(datetimeString, formatter);
        } catch (DateTimeParseException e) {
            throw Exceptions.INVALID_DATE_FORMAT;
        }
    }
}
