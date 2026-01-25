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
                    print("Aw man... back to the task list I guess...\n" +
                            "  " + taskList.unmarkTaskDone(taskNo) + "\n");
                }

            } else {
                print("Added: " + userInput + "\n");
                taskList.addTask(new Task(userInput)); // stores the new task
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
