package pieces;

import board.Box;
import moves.BoxMoves;

import java.awt.*;

import static utility.Util.numberIsInBoard;

public class Rook extends Piece{
    public Rook (Color color){
        super(color,PieceName.ROOK);
    }

    public BoxMoves getPossibleMoves (Box currentBox, Box[][] board) {
        BoxMoves boxMoves = new BoxMoves();
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            int n = 1;
            while(numberIsInBoard(i+n*k)){
                boxMoves.addMove(i+n*k,j);
                if(board[i+n*k][j].getCurrentPiece()!=null) break;
                n++;
            }
            n=0;
            while(numberIsInBoard(j+n*k)){
                boxMoves.addMove(i,j+n*k);
                if(board[i][j+n*k].getCurrentPiece()!=null) break;
                n++;
            }
        }
        return boxMoves;
    }


}
