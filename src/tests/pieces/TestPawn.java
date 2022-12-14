package tests.pieces;

import board.ChessBoard;
import board.boxes.RealBox;
import moves.BoxMoves;
import org.testng.annotations.Test;
import pieces.Pawn;
import pieces.Piece;
import player.AiPlayer;
import javax.swing.*;
import java.awt.*;

public class TestPawn {

    JFrame jFrame = new JFrame();

    private ChessBoard cb = new ChessBoard(jFrame);

    private RealBox[][] board = cb.getBoard();

    private AiPlayer aiPlayer = new AiPlayer (Color.BLACK,  board);


    @Test
    public void testGetPossibleMoves_01 ( ) {
        Piece pawn = new Pawn(Color.BLACK);
        board[0][0].setCurrentPiece(pawn);
        BoxMoves possibleMoves = pawn.getPossibleMoves(board[0][0],board);
        System.out.println(possibleMoves);
        possibleMoves.forEach(fakeMove -> System.out.println(fakeMove));
    }

    @Test
    public void testGetPossibleMoves_02 ( ) {
        Piece pawn = new Pawn(Color.BLACK);
        Piece pawn2 = new Pawn(Color.BLACK);
        board[0][0].setCurrentPiece(pawn);
        board[1][0].setCurrentPiece(pawn2);
        BoxMoves possibleMoves = pawn.getPossibleMoves(board[0][0],board);
        System.out.println(possibleMoves);
        possibleMoves.forEach(fakeMove -> System.out.println(fakeMove));
    }
}
