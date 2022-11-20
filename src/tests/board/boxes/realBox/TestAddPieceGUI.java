package tests.board.boxes.realBox;

import board.MyChessBoard;
import board.boxes.RealBox;
import pieces.Pawn;

import javax.swing.*;
import java.awt.*;

public class TestAddPieceGUI {

    public static void main(String[] args) {
        MyChessBoard cb = new MyChessBoard();
        RealBox [][] board = cb.getBoard();
        JFrame f = new JFrame("ChessChamp");
        f.add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);

        board[0][0].addPiece(new Pawn(Color.BLACK));
        System.out.println(board[0][0]);
        board[0][0].getCurrentPiece().printAllDetails();
        board[0][0].addPieceGUI();
        f.setVisible(true); // se non lo metto non si vede
    }

}
