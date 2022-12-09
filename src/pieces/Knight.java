package pieces;

import board.boxes.IBox;
import moves.BoxMoves;
import java.awt.*;




public class Knight extends  Piece{
    public Knight (Color color){
        super(color,PieceName.KNIGHT);
    }

    public BoxMoves getPossibleMoves (IBox currentBox, IBox[][]board) {
        BoxMoves boxMoves = new BoxMoves(currentBox);
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            for (int n = -2; n < 3; n += 4) {
                addKnightMove(i, j, k, n, boxMoves, board);
                addKnightMove(i, j, n, k, boxMoves, board);
            }
        }
        return boxMoves;
    }



    private void addKnightMove (int i, int j, int n, int k, BoxMoves boxMoves, IBox[][]board) {
        if (numberIsInBoardKnight(i + k) && numberIsInBoardKnight(j + n))
            boxMoves.addMove(i + k, j + n, board);
    }


    private boolean numberIsInBoardKnight (int number) {
        return !numberIsInBoard(number) || number==-2 || number==9 ? false : true;
    }

    @Override
    public Knight clone() {
        Knight knight = new Knight(this.getColor());
        knight.setStrength(this.getStrength()>0);
        return knight;
    }


}
