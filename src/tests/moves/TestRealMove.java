package tests.moves;

import board.MyChessBoard;
import board.boxes.RealBox;
import moves.RealMove;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Pawn;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class TestRealMove {

    private MyChessBoard cb = new MyChessBoard();

    private RealBox[][] board = cb.getBoard();

    JFrame jFrame = new JFrame();


    @AfterEach
    public void clearBoard( ) {
        Arrays.stream(board).forEach(row -> {
            Arrays.stream(row).forEach(box -> box.setCurrentPiece(null));
        });
    }

    @BeforeEach
    public void activateJFrame () throws InterruptedException {
        jFrame.add(cb.getGui());
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationByPlatform(true);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        jFrame.pack();
        // ensures the minimum size is enforced.
        jFrame.setMinimumSize(jFrame.getSize());
        jFrame.setVisible(true);
        Thread.sleep(2000);
    }


    @Test
    public void testMakeMove_01 () {
        board[0][0].addPiece(new Pawn(Color.BLACK));
        RealMove realMove =  new RealMove(board[0][0],board[0][1],jFrame);
        realMove.makeMove();
    }

}
