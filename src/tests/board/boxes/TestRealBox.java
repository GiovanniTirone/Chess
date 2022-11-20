package tests.board.boxes;

import board.MyChessBoard;
import board.boxes.RealBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Pawn;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TestRealBox {

    JFrame jFrame = new JFrame();

    private MyChessBoard cb = new MyChessBoard(jFrame);

    private RealBox[][] board = cb.getBoard();


    @AfterEach
    public void clearBoard( ) {
        Arrays.stream(board).forEach(row -> {
            Arrays.stream(row).forEach(box -> box.setCurrentPiece(null));
        });
    }


    public void activateJFrame (RealBox realBox) throws InterruptedException {
        jFrame.add(realBox);
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
    public void testAddPiece () throws InterruptedException {


        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationByPlatform(true);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        jFrame.pack();
        // ensures the minimum size is enforced.
        jFrame.setMinimumSize(jFrame.getSize());
        jFrame.setVisible(true);
        RealBox realBox = new RealBox(0,0,jFrame);
        realBox.setBlack(false);
        realBox.addPiece(new Pawn(Color.BLACK));
        System.out.println(realBox);
    }


}
