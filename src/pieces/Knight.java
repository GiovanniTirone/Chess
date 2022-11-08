package pieces;

import board.RealBox;
import board.MyChessBoard;
import board.PlayerPieces;
import moves.BoxMoves;
import java.awt.*;
import static utility.Util.numberIsInBoard;

public class Knight extends  Piece{
    public Knight (Color color){
        super(color,PieceName.KNIGHT);
    }

    public BoxMoves getPossibleMoves (RealBox currentRealBox, RealBox[][]board) {
        BoxMoves boxMoves = new BoxMoves(currentRealBox);
        int i = currentRealBox.getRow();
        int j = currentRealBox.getCol();
        for (int k = -1; k < 2; k += 2) {
            for (int n = -2; n < 3; n += 4) {
                addKnightMove(i, j, k, n, boxMoves, board);
                addKnightMove(i, j, n, k, boxMoves, board);
            }
        }
        return boxMoves;
    }

    private void addKnightMove (int i, int j, int n, int k, BoxMoves boxMoves, RealBox[][]board) {
        if (numberIsInBoardKnight(i + k) && numberIsInBoardKnight(j + n))
            boxMoves.addMove(i + k, j + n, board);
    }

    /*
    public BoxMoves getPossibleMoves (Box currentBox,Box[][]board) {
        BoxMoves boxMoves = new BoxMoves(currentBox);
        int i = currentBox.getRow();
        int j = currentBox.getCol();

        addKnightMove(i,j,boxMoves,board,false);
        addKnightMove(i,j,boxMoves,board,true);
        return boxMoves;
    }

    private BoxMoves addKnightMove (int i, int j,BoxMoves boxMoves,Box[][]board,boolean invert) {
        for (int k = -1; k < 2; k += 2) {
            for(int n=-2; n<3; n+=4) {
                if(invert){
                    if (numberIsInBoardKnight(i + n) && numberIsInBoardKnight(j + k))
                        boxMoves.addMove(i + n, j + k,board);
                }else {
                    if (numberIsInBoardKnight(i + k) && numberIsInBoardKnight(j + n))
                        boxMoves.addMove(i + k, j + n, board);
                }
            }
        }
        return boxMoves;
    }
    */


    private boolean numberIsInBoardKnight (int number) {
        return !numberIsInBoard(number) || number==-2 || number==9 ? false : true;
    }


    public static void main(String[] args) throws Exception {
        MyChessBoard cb = new MyChessBoard();
        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);
        //cb.addPiecesInStarterPosition(whitePieces,blackPieces);
        //cb.addPieceToBoard(4,4,whitePieces.getPiece(PieceName.KING,0));
        //cb.addPieceToBoard(3,3,whitePieces.getPiece(PieceName.PAWN,0));
        /*BoxMoves boxMoves = whitePieces.getPiece(PieceName.KING,0).getPossibleMoves(cb.getBoard()[4][4],cb.getBoard());
        System.out.println("Moves of the box" + "(" +4 +"," +4+"):" +
                "\n" + whitePieces.getPiece(PieceName.KING,0)
                .printPossibleMoves(boxMoves));*/
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                BoxMoves boxMoves = whitePieces.getPiece(PieceName.KNIGHT,0).getPossibleMoves(cb.getBoard()[i][j],cb.getBoard());
                System.out.println("Moves of the box" + "(" +i +"," +j+"):" +
                        "\n" + whitePieces.getPiece(PieceName.KNIGHT,0)
                        .printPossibleMoves(boxMoves));
            }
        }
    }


}
