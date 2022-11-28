package tests.player.aiPlayer;

import board.MyChessBoard;
import board.boxes.FakeBox;
import board.boxes.RealBox;
import moves.FakeMove;
import moves.RealMove;
import org.junit.jupiter.api.AfterEach;
import org.testng.annotations.Test;
import pieces.Pawn;
import player.AiPlayer;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TestAiPlayer {
    JFrame jFrame = new JFrame();

    private MyChessBoard cb = new MyChessBoard(jFrame);

    private RealBox [][] board = cb.getBoard();

    private AiPlayer aiPlayer = new AiPlayer (Color.BLACK,  board,  jFrame);

    @AfterEach
    public void clearBoard( ) {
        Arrays.stream(board).forEach(row -> {
            Arrays.stream(row).forEach(box -> box.setCurrentPiece(null));
        });
    }

    @Test
    public void testCreateFakeBoardFromBoard () {
        board[0][0].setCurrentPiece(new Pawn(Color.BLACK));
        FakeBox [][] fakeBoard = aiPlayer.createFakeBoardFromBoard(board);
        System.out.println(fakeBoard);
        Arrays.stream(fakeBoard).forEach(row -> {
            Arrays.stream(row).forEach(box -> System.out.println(box));
        });
    }

    @Test
    public void testGetPossibleMoves_01 () {
        board[0][0].setCurrentPiece(new Pawn(Color.BLACK));
        FakeBox [][] fakeBoard = aiPlayer.createFakeBoardFromBoard(board);
        List<FakeMove> possibleMoves = aiPlayer.getPossibleMoves(fakeBoard);
        System.out.println("Possible moves" + possibleMoves);
    }


    @Test
    public void testGetPossibleMoves_02 () {
        board[0][0].setCurrentPiece(new Pawn(Color.BLACK));
        board[1][0].setCurrentPiece(new Pawn(Color.BLACK));
        FakeBox [][] fakeBoard = aiPlayer.createFakeBoardFromBoard(board);
        List<FakeMove> possibleMoves = aiPlayer.getPossibleMoves(fakeBoard);
        System.out.println("Possible moves" + possibleMoves);
    }

    @Test
    public void testCreateRealMoveFromFakeMove () {
        FakeBox startBox = aiPlayer.createFakeBoxFromBox(board[0][0]);
        startBox.addPiece(new Pawn(Color.BLACK));
        FakeBox endBox = aiPlayer.createFakeBoxFromBox(board[1][0]);
        endBox.addPiece(new Pawn(Color.WHITE));
        FakeMove fakeMove = new FakeMove(startBox,endBox);
        System.out.println("FakeMove: \n" + fakeMove);
        RealMove realMove = aiPlayer.createRealMoveFromFakeMove(fakeMove,board);
        System.out.println("Real Move: \n" + realMove);
    }



}
