package pieces;

import board.Box;
import moves.BoxMoves;

import java.awt.*;

import static utility.Util.numberIsInBoard;

public class Bishop extends Piece{
    public Bishop (Color color){
        super(color,PieceName.BISHOP);
    }

    public BoxMoves getPossibleMoves (Box currentBox,Box[][] board) {
        BoxMoves boxMoves = new BoxMoves();
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            int n = 1;
            while(numberIsInBoard(i+n*k)&&numberIsInBoard(j-n*k)){
                boxMoves.addMove(i+n*k,j-n*k);
                if(board[i+n*k][j-n*k].getCurrentPiece()!=null) break;
                n++;
            }
            n=0;
            while(numberIsInBoard(i+n*k)&&numberIsInBoard(j+n*k)){
                boxMoves.addMove(i+n*k,j+n*k);
                if(board[i+n*k][j+n*k].getCurrentPiece()!=null) break;
                n++;
            }
        }
        return boxMoves;
    }

}
