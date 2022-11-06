package pieces;

import board.Box;
import moves.BoxMoves;
import moves.Move;
import utility.Util;

import java.awt.*;

import static utility.Util.numberIsInBoard;

public class King extends Piece{
    public King(Color color) {
        super(color,PieceName.KING);
    }

    public BoxMoves getPossibleMoves (Box currentBox) {
        BoxMoves boxMoves = new BoxMoves();
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            if (numberIsInBoard(i + k)) {
                boxMoves.addMove(i + k, j);
                if (numberIsInBoard(j - k)) boxMoves.addMove(i + k, j - k);
                if (numberIsInBoard(j + k)) boxMoves.addMove(i + k, j + k);
            }
            if (numberIsInBoard(j + k)) boxMoves.addMove(i, j + k);
        }
        return boxMoves;
    }


}
