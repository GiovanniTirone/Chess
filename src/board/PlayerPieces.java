package board;
import lombok.Data;
import pieces.Piece;
import pieces.PieceName;
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
                        this.get(pieceName).add(new Piece(color, pieceName));
                    }
                    break;
                case KING: case QUEEN:
                    this.put(pieceName, new ArrayList<>());
                    this.get(pieceName).add(new Piece(color,pieceName));
                    break;
                default:
                    this.put(pieceName, new ArrayList<>());
                    for(int i=0; i<2; i++) {
                        this.get(pieceName).add(new Piece(color,pieceName));
                    }
            }
        }

    }

    /*
    public Piece getPiece (PieceName pieceName) throws Exception {
        if(pieceName != PieceName.KING || pieceName != PieceName.QUEEN) throw new Exception("This method works only for KIKG and QUEEN");
        return this.get(pieceName).get(0);
    };*/

    public Piece getPiece(PieceName pieceName, int number){
        return this.get(pieceName).get(number);
    }

    /*
    public JLabel getPieceJLabel (PieceName pieceName) throws Exception {
        if(pieceName != PieceName.KING || pieceName != PieceName.QUEEN) throw new Exception("This method works only for KIKG and QUEEN");
        return this.get(pieceName).get(0).getJLabel();
    };*/

    public JLabel getPieceJLabel (PieceName pieceName, int number){
        return this.get(pieceName).get(number).getJLabel();
    }

}
