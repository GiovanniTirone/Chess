package tests.player.humanPlayer;

import board.MyChessBoard;
import board.boxes.RealBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pieces.Pawn;
import player.AiPlayer;
import player.HumanPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TestHumanPlayer {


    JFrame jFrame = new JFrame();

    private MyChessBoard cb = new MyChessBoard(jFrame);

    private RealBox[][] board = cb.getBoard();

    private HumanPlayer humanPlayer = new HumanPlayer (Color.BLACK,  board);

    @AfterEach
    public void clearBoard( ) {
        Arrays.stream(board).forEach(row -> {
            Arrays.stream(row).forEach(box -> box.setCurrentPiece(null));
        });
    }

    private void printBoard ( ) {
        Arrays.stream(board).forEach(row -> {
            Arrays.stream(row).forEach(box -> System.out.println(box));
        });
    }

    @Test
    public void makeTwoMoves () {
        RealBox startBox = board[0][0];
        RealBox middleBox = board[1][0];
        RealBox endBox = board[1][1];
        startBox.addPiece(new Pawn(Color.BLACK));
        System.out.println(startBox);
        System.out.println(middleBox);
        humanPlayer.getNextMove().setStart(startBox);
        humanPlayer.getNextMove().setEnd(middleBox);
        humanPlayer.getNextMove().makeMove();
        System.out.println(startBox);
        System.out.println(middleBox);
        humanPlayer.getNextMove().setStart(middleBox);
        humanPlayer.getNextMove().setEnd(endBox);
        humanPlayer.getNextMove().makeMove();
        System.out.println(middleBox);
        System.out.println(endBox);
    }

}
