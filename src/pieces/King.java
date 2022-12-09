package pieces;

import board.boxes.IBox;
import moves.BoxMoves;
import java.awt.*;



public class King extends Piece{
    public King(Color color) {
        super(color,PieceName.KING);
    }

    public BoxMoves getPossibleMoves (IBox currentBox, IBox[][]board) {
        BoxMoves boxMoves = new BoxMoves(currentBox);
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            if (numberIsInBoard(i + k)) {
                boxMoves.addMove(i + k, j,board);
                if (numberIsInBoard(j - k)) boxMoves.addMove(i + k, j - k,board);
                if (numberIsInBoard(j + k)) boxMoves.addMove(i + k, j + k,board);
            }
            if (numberIsInBoard(j + k)) boxMoves.addMove(i, j + k,board);
        }
        return boxMoves;
    }


    @Override
    public King clone() {
        King king = new King(this.getColor());
        king.setStrength(this.getStrength()>0);
        return king;
    }


}
