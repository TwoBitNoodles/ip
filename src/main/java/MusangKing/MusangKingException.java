package MusangKing;

public class MusangKingException extends RuntimeException {
    public MusangKingException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        DisplayMessage DM = new DisplayMessage(super.getMessage());
        return DM.toString();
    }
}
