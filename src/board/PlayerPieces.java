package board;
import lombok.Data;
import pieces.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


@Data
public class PlayerPieces extends HashMap <PieceName,ArrayList<Piece>> {
    private Color color ;

    public PlayerPieces (Color color){
        super();
        this.color=color;

        for(PieceName pieceName : PieceName.values()) {
            switch (pieceName) {
                case PAWN:
                    this.put(pieceName, new ArrayList<>());
                    for (int i = 0; i < 8; i++) {
                        this.get(pieceName).add(new Pawn(color));
                    }
                    break;
                case KING:
                    this.put(pieceName, new ArrayList<>());
                    this.get(pieceName).add(new King(color));
                    break;
                case QUEEN:
                    this.put(pieceName, new ArrayList<>());
                    this.get(pieceName).add(new Queen(color));
                    break;
                case BISHOP:
                    this.put(pieceName, new ArrayList<>());
                    for(int i=0; i<2; i++) {
                        this.get(pieceName).add(new Bishop(color));
                    }
                    break;
                case ROOK:
                    this.put(pieceName, new ArrayList<>());
                    for(int i=0; i<2; i++) {
                        this.get(pieceName).add(new Rook(color));
                    }
                    break;
                case KNIGHT:
                    this.put(pieceName, new ArrayList<>());
                    for(int i=0; i<2; i++) {
                        this.get(pieceName).add(new Knight(color));
                    }
                    break;
            }
        }


    }

    public void setStrengths (boolean maximizingPlayer) {
        this.values().stream()
                .forEach(piecesList -> piecesList.stream().forEach(piece -> piece.setStrength(maximizingPlayer)));
    }


    public Piece getPiece(PieceName pieceName, int number){
        return this.get(pieceName).get(number);
    }


    public JLabel getPieceJLabel (PieceName pieceName, int number){
        return this.get(pieceName).get(number).getJLabel();
    }

}
