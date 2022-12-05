package tests.player.humanPlayer;

import board.ChessBoard;
import board.boxes.RealBox;
import pieces.Pawn;
import player.HumanPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class TestTwoMovesGUI {

    public static void main(String[] args) {

        JFrame f = new JFrame("ChessChamp");
        ChessBoard cb = new ChessBoard(f);
        RealBox[][] board = cb.getBoard();
        f.add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);

        board[0][0].addPiece(new Pawn(Color.BLACK));
        board[0][0].addPieceGUI();
        System.out.println(board[0][0]);
        board[0][0].getCurrentPiece().printAllDetails();
        f.setVisible(true);

        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setBoard(cb.getBoard())));

        cb.addBoxListeners();

        HumanPlayer humanPlayer = new HumanPlayer(Color.BLACK,board);


        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(humanPlayer.getIsPressedListener())));

        CompletableFuture
                .runAsync(humanPlayer.getWaitFillTheMove())
                .thenRun(humanPlayer.getMakeRealMove())
                .thenRun(cb::clearPressedDatas)
                .thenRun(humanPlayer.getWaitFillTheMove())
                .thenRun(humanPlayer.getMakeRealMove());

    }
}
