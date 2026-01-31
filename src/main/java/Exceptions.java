public class Exceptions {

    public static final MusangKingException INVALID_COMMAND = new InvalidCommandException();
    public static final MusangKingException TASK_OUT_OF_BOUNDS = new TaskOutOfBoundsException();

    public static class InvalidCommandException extends MusangKingException {
        private InvalidCommandException() {
            super("""
                    Huh? I don't know what you want me to do!
                    To view all the commands I understand, type "help".
                    """);
        }
    }

    public static class MissingFieldException extends MusangKingException {
        public MissingFieldException(String info, String cmd) {
            super(String.format("""
                    If you don't tell me %s,
                    I can't %s!
                    """, info, cmd));
        }
    }

    public static class InvalidInputException extends MusangKingException {
        public InvalidInputException(String param, String type) {
            super(String.format("""
                    The %s is supposed to be %s!
                    """, param, type));
        }
    }

    public static class TaskOutOfBoundsException extends MusangKingException {
        private TaskOutOfBoundsException() {
            super("""
                    What? Please enter a valid task number!
                    """);
        }
    }
}
