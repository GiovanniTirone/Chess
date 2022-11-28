package pieces;


public enum PieceName {
    PAWN("\u2659",10),
    BISHOP("\u2657",30 ),
    KNIGHT("\u2658",30),
    ROOK("\u2656",50),
    QUEEN("\u2655",90),
    KING("\u2654",900);


    private final String unicodeChar;

    private final int strength;

    PieceName (String label, int strength) {
        this.unicodeChar = label;
        this.strength = strength;
    }

    public String getUnicodeChar() {
        return this.unicodeChar;
    }

    public int getStrength() {
        return strength;
    }
    

}
