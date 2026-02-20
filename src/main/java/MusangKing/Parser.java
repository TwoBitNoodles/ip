package MusangKing;

import java.time.LocalDate;
import java.util.List;

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
        MusangKing.flag = false;
        return Ui.GOODBYE;
    }

    /**
     * @return : MusangKing.DisplayMessage with the string representation
     *           of the list of tasks.
     */
    private DisplayMessage listResponse(TaskList taskList) {
        return new Ui.TaskListMessage(taskList.toString());
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

        if (!isValidDate(args[1])) {
            throw Exceptions.INVALID_DATE_FORMAT;
        }//

        Task newTask = new Deadline(args[0], args[1]);
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
        Task newTask = new Event(args[0], args[1], args[2]);
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

    private boolean isValidDate(String date) {
        if (date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8, 10));
            if (year >= LocalDate.now().getYear()) {
                if (month >= 1 && month <= 12) {
                    if (List.of(1, 3, 5, 7, 8, 10, 12).contains(month)) {
                        return day >= 1 && day <= 31;
                    } else if (List.of(4, 6, 9, 11).contains(month)) {
                        return day >= 1 && day <= 30;
                    } else if (month == 2) {
                        return day >= 1 && day <= 28;
                    }
                }
            }
        }
        return false;
    }
}
