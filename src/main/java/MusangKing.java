import java.util.Scanner;

public class MusangKing {

    public static boolean flag = true;

    public static void main(String[] args) {
        /*
         * Chat starts with a greeting message.
         */
        System.out.println(Messages.GREETING);

        /*
         * Initialize Parser object to enable the chat loop.
         */
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        while (flag) {
            DisplayMessage msg;
            try {
                msg = parser.parse(sc.nextLine());
                System.out.println(msg);
            } catch (MusangKingException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
