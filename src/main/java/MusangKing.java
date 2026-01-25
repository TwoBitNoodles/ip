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
            String userInput = sc.nextLine(); // reads user input
            switch (userInput.toLowerCase()) {
                case "bye":
                    print("Bye! Come back, okay?\n");
                    flag = false; // exits chat loop
                    break;
                case "list":
                    print(taskList.toString());
                    break;
                default:
                    print("Added: " + userInput + "\n");
                    taskList.addTask(new Task(userInput)); // stores the new task
                    break;
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
