package pieces;
import board.boxes.IBox;
import moves.BoxMoves;
import java.awt.*;


public class Pawn extends Piece{

    public Pawn (Color color){
        super(color,PieceName.PAWN);
    }

    public BoxMoves getPossibleMoves (IBox currentBox, IBox[][] board){
        BoxMoves boxMoves = new BoxMoves(currentBox);
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        if(this.getColor() == Color.BLACK){
            if(i!=7) boxMoves.addMove(i+1,j,board);
        }else{
            if(i!=0) boxMoves.addMove(i-1,j,board);
        }
        addKillingMoves(currentBox,board,boxMoves);
        return boxMoves;
    }

    public void addKillingMoves (IBox currentBox, IBox[][] board, BoxMoves boxMoves) {
        int k = this.getColor() == Color.BLACK ? 1 : -1 ;
        int i = currentBox.getRow();
        int j = currentBox.getCol();
        if(numberIsInBoard(i+k)){
            if(numberIsInBoard(j+1)) {
                Piece targetPiece = board[i+k][j+1].getCurrentPiece();
                if(targetPiece !=null && targetPiece.getColor()!=getColor())
                    boxMoves.addMove(i+k,j+1,board);
            }
            if(numberIsInBoard(j-1)) {
                Piece targetPiece = board[i+k][j-1].getCurrentPiece();
                if(targetPiece !=null && targetPiece.getColor()!=getColor())
                    boxMoves.addMove(i+k,j-1,board);
            }
        }
    }


}
