package MusangKing;

import java.util.Scanner;
import java.io.IOException;

public class MusangKing {

    public static boolean flag = true;

    public static void main(String[] args) {
        /*
         * Initialize MusangKing.TaskList object.
         * Initialize FileManager to check for save files
         * and update tasklist accordingly.
         */
        TaskList taskList = new TaskList();
        DisplayMessage msg;
        try {
            msg = Storage.initialiseFileManager(taskList);
            System.out.println(msg);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        /*
         * Chat starts with a greeting message.
         */
        System.out.println(Ui.GREETING);

        /*
         * Initialize MusangKing.Parser object to enable the chat loop.
         */
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        //
        while (flag) {
            try {
                msg = parser.parse(taskList, sc.nextLine());
                System.out.println(msg);
            } catch (MusangKingException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Storage.updateFile(taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
