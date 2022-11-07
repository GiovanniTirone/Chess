package pieces;


public enum PieceName {
    PAWN("\u2659",10),
    BISHOP("\u2657",30 ),
    KNIGHT("\u2658",30),
    ROOK("\u2656",50),
    QUEEN("\u2655",90),
    KING("\u2654",900);


    private final String unicodeChar;

    private final int strenght;

    PieceName (String label, int strenght) {
        this.unicodeChar = label;
        this.strenght = strenght;
    }

    public String getUnicodeChar() {
        return this.unicodeChar;
    }

    public int getStrenght() {
        return strenght;
    }
}
