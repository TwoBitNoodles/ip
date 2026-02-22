package musangking.gui;

import musangking.*;

import java.util.Scanner;
import java.io.IOException;

public class MusangKing {

    private TaskList tasklist = new TaskList();
    private Parser parser = new Parser();


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

        try {
            Storage.updateFile(taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String initialiseData() {
        try {
            DisplayMessage msg = Storage.initialiseFileManager(this.tasklist);
            return msg.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            DisplayMessage msg = parser.parse(this.tasklist, input);
            return msg.toString();
        } catch (MusangKingException e) {
            return e.getMessage();
        }
    }

    public void finaliseData() {
        try {
            Storage.updateFile(this.tasklist);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
