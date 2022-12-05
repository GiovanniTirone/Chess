package pieces;
import board.boxes.IBox;
import board.ChessBoard;
import board.PlayerPieces;
import moves.BoxMoves;

import javax.swing.*;
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

    public static void main(String[] args) throws Exception {
        ChessBoard cb = new ChessBoard(new JFrame());
        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);
        /*cb.addPiecesInStarterPosition(whitePieces,blackPieces);
        cb.addPieceToBoard(4,4,whitePieces.getPiece(PieceName.BISHOP,0));
        cb.addPieceToBoard(3,3,whitePieces.getPiece(PieceName.PAWN,0));
        BoxMoves boxMoves = whitePieces.getPiece(PieceName.BISHOP,0).getPossibleMoves(cb.getBoard()[4][4],cb.getBoard());
        System.out.println("Moves of the box" + "(" +4 +"," +4+"):" +
                "\n" + whitePieces.getPiece(PieceName.BISHOP,0)
                .printPossibleMoves(boxMoves));*/
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                BoxMoves boxMoves = whitePieces.getPiece(PieceName.PAWN,0).getPossibleMoves(cb.getBoard()[i][j],cb.getBoard());
                System.out.println("Moves of the box" + "(" +i +"," +j+"):" +
                        "\n" + whitePieces.getPiece(PieceName.PAWN,0)
                        .printPossibleMoves(boxMoves));
            }
        }
        /*
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Pawn pawn = (Pawn)  whitePieces.getPiece(PieceName.PAWN,0);
                BoxMoves boxMoves = pawn.getKillingMoves(cb.getBoard()[i][j],cb.getBoard(),new BoxMoves(cb.getBoard()[i][j]));
                System.out.println("Moves of the box" + "(" +i +"," +j+"):" +
                        "\n" + whitePieces.getPiece(PieceName.PAWN,0)
                        .printPossibleMoves(boxMoves));
            }
        }*/
    }

}
