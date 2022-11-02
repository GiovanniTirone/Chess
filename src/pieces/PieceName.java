package pieces;

public enum PieceName {
    PAWN("\u2659"),
    BISHOP("\u2657"),
    KNIGHT("\u2658"),
    ROCK("\u2656"),
    QUEEN("\u2655"),
    KING("\u2654");

    private final String label;

    PieceName (String label) {this.label = label;}


}
