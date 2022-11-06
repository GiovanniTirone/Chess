package pieces;

import board.Box;
import moves.BoxMoves;
import java.awt.*;
import static utility.Util.numberIsInBoard;

public class Knight extends  Piece{
    public Knight (Color color){
        super(color,PieceName.KNIGHT);
    }

    public BoxMoves getPossibleMoves (Box currentBox) {
        BoxMoves boxMoves = new BoxMoves();
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        addKightMove(i,j,boxMoves);
        addKightMove(j,i,boxMoves);
        return boxMoves;
    }

    private BoxMoves addKightMove (int i, int j,BoxMoves boxMoves) {
        for (int k = -1; k < 2; k += 2) {
            for(int n=-2; n<3; n+=4) {
                if (numberIsInBoardKnight(i + k) && numberIsInBoardKnight(j + n))
                    boxMoves.addMove(i + k, j + n);
            }
        }
        return boxMoves;
    }

    private boolean numberIsInBoardKnight (int number) {
        return !numberIsInBoard(number) || number==-2 || number==-9 ? false : true;
    }

}
