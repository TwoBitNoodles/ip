package musangking.gui;

public class DisplayMessage {
    protected final String msg;

    public DisplayMessage(String msg) {
        this.msg = msg;
    }

    /**
     * String representation of the message displayed to the user,
     * sandwiched between horizontal lines.
     */
    @Override
    public String toString() {
        return this.msg;
    }
}
