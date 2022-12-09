package pieces;

import board.boxes.IBox;
import moves.BoxMoves;
import java.awt.*;



public class Queen extends Piece {
    public Queen (Color color){
        super(color,PieceName.QUEEN);
    }

    public BoxMoves getPossibleMoves (IBox currentBox, IBox[][] board) {
        BoxMoves boxMoves = new BoxMoves(currentBox);
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            int n = 1;
            while(numberIsInBoard(i+n*k)&&numberIsInBoard(j-n*k)){
                boxMoves.addMove(i+n*k,j-n*k,board);
                if(board[i+n*k][j-n*k].getCurrentPiece()!=null) break;
                n++;
            }
            n=1;
            while(numberIsInBoard(i+n*k)&&numberIsInBoard(j+n*k)){
                boxMoves.addMove(i+n*k,j+n*k,board);
                if(board[i+n*k][j+n*k].getCurrentPiece()!=null) break;
                n++;
            }
            n=1;
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

    @Override
    public Queen clone() {
        Queen queen = new Queen(this.getColor());
        queen.setStrength(this.getStrength()>0);
        return queen;
    }


}
