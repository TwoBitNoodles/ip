import java.util.Scanner;

public class MusangKing {

    public static boolean flag = true;

    public static void main(String[] args) {
        /*
         * Chat starts with a greeting message.
         */
        Parser.print("""
                Hello! I'm MusangKing :)
                What can I do for you?
                """);

        /*
         * Initialize Parser object to enable the chat loop.
         */
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        while (flag) {
            parser.parse(sc.nextLine());
        }
    }
}
