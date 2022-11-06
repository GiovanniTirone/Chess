package pieces;
import board.Box;
import moves.BoxMoves;
import moves.PossibleMoves;
import utility.Util;

import java.awt.*;

import static utility.Util.mod9;
import static utility.Util.numberIsInBoard;

public class Pawn extends Piece{

    public Pawn (Color color){
        super(color,PieceName.PAWN);
    }

    public BoxMoves getPossibleMoves (Box currentBox){
        BoxMoves boxMoves = new BoxMoves();
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        if(this.getColor() == Color.BLACK){
            if(i!=7) boxMoves.addMove(i+1,j);
        }else{
            if(i!=0) boxMoves.addMove(i-1,j);
        }
        return boxMoves;
    }

    public BoxMoves killingMoves (Box currentBox) {
        BoxMoves boxMoves = new BoxMoves();
        int k = this.getColor() == Color.BLACK ? 1 : -1 ;
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        if(numberIsInBoard(i+k)){
            if(numberIsInBoard(j+1)) boxMoves.addMove(i+k,j+1);
            if(numberIsInBoard(j-1)) boxMoves.addMove(i+k,j-1);
        }
        return boxMoves;
    }

}
