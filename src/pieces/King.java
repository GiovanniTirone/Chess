package pieces;

import board.boxes.IBox;
import board.boxes.RealBox;
import board.MyChessBoard;
import board.PlayerPieces;
import moves.BoxMoves;

import java.awt.*;

import static utility.Util.numberIsInBoard;

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

    public static void main(String[] args) throws Exception {
        MyChessBoard cb = new MyChessBoard();
        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);
        //cb.addPiecesInStarterPosition(whitePieces,blackPieces);
        cb.addPieceToBoard(4,4,whitePieces.getPiece(PieceName.KING,0));
        cb.addPieceToBoard(3,3,whitePieces.getPiece(PieceName.PAWN,0));
        BoxMoves boxMoves = whitePieces.getPiece(PieceName.KING,0).getPossibleMoves(cb.getBoard()[4][4],cb.getBoard());
        System.out.println("Moves of the box" + "(" +4 +"," +4+"):" +
                "\n" + whitePieces.getPiece(PieceName.KING,0)
                .printPossibleMoves(boxMoves));
        /*for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                BoxMoves boxMoves = whitePieces.getPiece(PieceName.KING,0).getPossibleMoves(cb.getBoard()[i][j],cb.getBoard());
                System.out.println("Moves of the box" + "(" +i +"," +j+"):" +
                        "\n" + whitePieces.getPiece(PieceName.KING,0)
                        .printPossibleMoves(boxMoves));
            }
        }*/
    }


}
