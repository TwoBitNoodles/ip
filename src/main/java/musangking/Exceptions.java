package musangking;

public class Exceptions {

    public static final MusangKingException INVALID_COMMAND = new InvalidCommandException();
    public static final MusangKingException TASK_OUT_OF_BOUNDS = new TaskOutOfBoundsException();
    public static final MusangKingException DATA_CORRUPTION = new DataCorruptionException();
    public static final MusangKingException INVALID_DATE_FORMAT = new InvalidDateFormatException();
    public static final MusangKingException ILLEGAL_ARGUMENT = new IllegalArgumentException();

    public static class InvalidCommandException extends MusangKingException {
        private InvalidCommandException() {
            super("Huh? I don't know what you want me to do! " +
                  "To view all the commands I understand, type \"help\".");
        }
    }

    public static class MissingFieldException extends MusangKingException {
        public MissingFieldException(String info, String cmd) {
            super(String.format("If you don't tell me %s, " +
                                "I can't %s!", info, cmd));
        }
    }

    public static class InvalidInputException extends MusangKingException {
        public InvalidInputException(String param, String type) {
            super(String.format("The %s is supposed to be %s!", param, type));
        }
    }

    public static class TaskOutOfBoundsException extends MusangKingException {
        private TaskOutOfBoundsException() {
            super("What? Please enter a valid task number!");
        }
    }

    public static class DataCorruptionException extends MusangKingException {
        private DataCorruptionException() {
            super("The save file from the previous session is corrupted. " +
                  "Creating new save file...");
        }
    }

    public static class InvalidDateFormatException extends MusangKingException {
        private InvalidDateFormatException() {
            super("""
                    Do you not know how to write the date? I'll show you:
                    For deadlines, you need to enter it as YYYY-MM-DD.
                    For events, you need to enter it as YYYY-MM-DD HH:MM.
                    And I only take REAL dates!
                    """);
        }
    }

    private static class IllegalArgumentException extends MusangKingException {
        private IllegalArgumentException() {
            super("You little... I see what you're trying to do! " +
                  "Can't you enter the correct number of arguments? " +
                  "And in the right order, please. Don't try to test me!");
        }
    }

    public static class InvalidTaskFieldException extends MusangKingException {
        public InvalidTaskFieldException(String taskType, String field) {
            super(String.format("%s tasks don't have a %s field!", taskType, field));
        }
    }
}
