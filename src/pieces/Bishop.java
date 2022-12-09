package pieces;
import board.boxes.IBox;
import moves.BoxMoves;
import java.awt.*;



public class Bishop extends Piece{
    public Bishop (Color color){
        super(color,PieceName.BISHOP);
    }

    public BoxMoves getPossibleMoves (IBox currentBox, IBox[][] board) {
        BoxMoves boxMoves = new BoxMoves(currentBox);
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            int n = 1;
            while (numberIsInBoard(i + n * k) && numberIsInBoard(j - n * k)) {
                boxMoves.addMove(i + n * k, j - n * k, board);
                if (board[i + n * k][j - n * k].getCurrentPiece() != null) break;
                n++;
            }
            n = 1;
            while (numberIsInBoard(i + n * k) && numberIsInBoard(j + n * k)) {
                boxMoves.addMove(i + n * k, j + n * k, board);
                if (board[i + n * k][j + n * k].getCurrentPiece() != null) break;
                n++;
            }
        }
        return boxMoves;
    }


    @Override
    public Bishop clone () {
        Bishop bishop = new Bishop(this.getColor());
        bishop.setStrength(this.getStrength() > 0);
        return bishop;
    }


}
