import java.util.Scanner;

public class MusangKing {
    public static void main(String[] args) {
        /*
         * Chat starts with a greeting message.
         */
        String line = "_".repeat(60) + "\n";
        System.out.println(line +
                "Hello! I'm MusangKing :)\n" +
                "What can I do for you?\n" +
                line
        );

        /*
         * Initialize Scanner object and boolean flag
         * to enable the chat loop.
         */
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            String userInput = sc.nextLine(); // reads user input
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(line +
                        "Bye! Come back, okay?\n" +
                        line
                );
                flag = false;
            } else {
                System.out.println(line +
                        userInput + " ;p\n"+
                        line
                );
            }
        }
    }
}
