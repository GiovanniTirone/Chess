package tests.board.boxes.realBox;

import board.MyChessBoard;
import board.boxes.RealBox;
import pieces.Pawn;

import javax.swing.*;
import java.awt.*;

public class TestAddPiece {

    public static void main(String[] args) {

        JFrame f = new JFrame("ChessChamp");
        MyChessBoard cb = new MyChessBoard(f);
        RealBox [][] board = cb.getBoard();
        f.add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);

        board[0][0].addPiece(new Pawn(Color.BLACK));
        System.out.println(board[0][0]);
        board[0][0].getCurrentPiece().printAllDetails();
        f.setVisible(true);
    }

}
