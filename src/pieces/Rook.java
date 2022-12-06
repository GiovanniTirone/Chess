package pieces;

import board.boxes.IBox;
import moves.BoxMoves;
import java.awt.*;



public class Rook extends Piece{
    public Rook (Color color){
        super(color,PieceName.ROOK);
    }

    public BoxMoves getPossibleMoves (IBox currentRealBox, IBox[][] board) {
        BoxMoves boxMoves = new BoxMoves(currentRealBox);
        int i = currentRealBox.getRow();
        int j = currentRealBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            int n = 1;
            while(numberIsInBoard(i+n*k)){
                boxMoves.addMove(i+n*k,j,board);
                if(board[i+n*k][j].getCurrentPiece()!=null) break;
                n++;
            }
            n=1;
            while(numberIsInBoard(j+n*k)){
                boxMoves.addMove(i,j+n*k,board);
                if(board[i][j+n*k].getCurrentPiece()!=null) break;
                n++;
            }
        }
        return boxMoves;
    }


}
