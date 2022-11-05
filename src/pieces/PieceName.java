package pieces;


import java.util.List;

public enum PieceName {
    PAWN("\u2659"),
    BISHOP("\u2657"),
    KNIGHT("\u2658"),
    ROOK("\u2656"),
    QUEEN("\u2655"),
    KING("\u2654");


    private final String unicodeChar;


    PieceName (String label) {
        this.unicodeChar = label;
    }

    public String getUnicodeChar() {
        return this.unicodeChar;
    }





}
