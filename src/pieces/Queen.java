package pieces;

import board.boxes.RealBox;
import board.MyChessBoard;
import board.PlayerPieces;
import moves.BoxMoves;

import java.awt.*;

import static utility.Util.numberIsInBoard;

public class Queen extends Piece {
    public Queen (Color color){
        super(color,PieceName.QUEEN);
    }

    public BoxMoves getPossibleMoves (RealBox currentRealBox, RealBox[][] board) {
        BoxMoves boxMoves = new BoxMoves(currentRealBox);
        int i = currentRealBox.getRow();
        int j = currentRealBox.getCol();
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

    public static void main(String[] args) throws Exception {
        MyChessBoard cb = new MyChessBoard();
        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);
        //cb.addPiecesInStarterPosition(whitePieces,blackPieces);
        /*cb.addPieceToBoard(4,4,whitePieces.getPiece(PieceName.BISHOP,0));
        cb.addPieceToBoard(3,3,whitePieces.getPiece(PieceName.PAWN,0));
        BoxMoves boxMoves = whitePieces.getPiece(PieceName.BISHOP,0).getPossibleMoves(cb.getBoard()[4][4],cb.getBoard());
        System.out.println("Moves of the box" + "(" +4 +"," +4+"):" +
                "\n" + whitePieces.getPiece(PieceName.BISHOP,0)
                .printPossibleMoves(boxMoves));*/
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                BoxMoves boxMoves = whitePieces.getPiece(PieceName.QUEEN,0).getPossibleMoves(cb.getBoard()[i][j],cb.getBoard());
                System.out.println("Moves of the box" + "(" +i +"," +j+"):" +
                        "\n" + whitePieces.getPiece(PieceName.QUEEN,0)
                        .printPossibleMoves(boxMoves));
            }
        }
    }

}
