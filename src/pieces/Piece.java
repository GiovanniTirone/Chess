package pieces;
import board.Box;
import lombok.Data;
import moves.BoxMoves;
import moves.EndingMove;

import javax.swing.*;
import java.awt.*;


@Data
public abstract class Piece {
    private Color color;
    private PieceName pieceName;
    private JLabel jLabel;
    private boolean live;


   private BoxMoves[][] possibleMoves;
    public Piece (Color color,PieceName pieceName) {
        this.color = color;
        this.pieceName = pieceName;
        this.live = true;
        this.possibleMoves = new BoxMoves[8][8];
       /* for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                this.possibleMoves[i][j]=new BoxMoves();
            }
        }*/

       // this.jLabel =  PieceGUI.getJLabelFromUnicodeChar(getUnicodeChar(),Color.BLACK, Color.DARK_GRAY,false);
    }

    public String getUnicodeChar () {
        return this.pieceName.getUnicodeChar();
    }

   /* public void addPossibleMove (int starter_row, int starter_col, int possible_row,int possible_col){
        possibleMoves[starter_row][starter_col].addMove(possible_row,possible_col);
    }*/


    public abstract BoxMoves getPossibleMoves (Box currentBox,Box [][] board);

    public int getStrength () {
        return this.pieceName.getStrenght();
    }

    public String printPossibleMoves (BoxMoves boxMoves) {
        String str = "";
        for(EndingMove endingMove : boxMoves){
            str+="[" + endingMove.getRow() +","+ endingMove.getCol() +"]";
        }
        return str;
    }

}
