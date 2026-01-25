import java.util.Scanner;

public class MusangKing {

    public static void main(String[] args) {
        /*
         * Chat starts with a greeting message.
         */
        print("""
                Hello! I'm MusangKing :)
                What can I do for you?
                """);

        /*
         * Initialize Scanner object, boolean flag and taskList
         * to enable the chat loop.
         */
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        TaskList taskList = new TaskList();

        while (flag) {
            String userInput = sc.nextLine().toLowerCase(); // reads user input

            if (userInput.equals("bye")) {
                print("Bye! Come back, okay?\n");
                flag = false; // exits chat loop

            } else if (userInput.equals("list")) {
                print(taskList.toString()); // prints the current list of tasks

            } else if (userInput.startsWith("mark")
                    || userInput.startsWith("unmark")) {

                String[] inputs = userInput.split(" "); // assume input is valid
                String action = inputs[0];
                int taskNo = Integer.parseInt(inputs[1]);

                if (action.equals("mark")) {
                    print("Yay! One less task to do!\n" +
                            "  " + taskList.markTaskDone(taskNo) + "\n");
                } else {
                    print("Aw man... back to the task list...\n" +
                            "  " + taskList.unmarkTaskDone(taskNo) + "\n");
                }

            } else { // assume the input is valid to add a task of specified type

                Task newTask;
                if (userInput.startsWith("todo")) {
                    userInput = userInput.substring(5);
                    newTask = new Todo(userInput);
                } else if (userInput.startsWith("deadline")) {
                    userInput = userInput.substring(9);
                    newTask = new Deadline(userInput);
                } else {
                    userInput = userInput.substring(6);
                    newTask = new Event(userInput);
                }
                taskList.addTask(newTask); // stores the new task
                print(String.format("""
                                Ookay! I've added this task:
                                 %s
                                Now you have %d tasks in the list.
                                """,
                        userInput, taskList.count));
            }
        }
    }

    /*
     * Helper function simplifies printing messages
     * between horizontal lines.
     */
    public static void print(String msg) {
        String line = "_".repeat(60) + "\n";
        System.out.println(line + msg + line);
    }
}
