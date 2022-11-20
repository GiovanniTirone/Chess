package tests.player.aiPlayer;

import board.MyChessBoard;
import board.boxes.RealBox;
import pieces.Pawn;
import player.AiPlayer;

import javax.swing.*;
import java.awt.*;

public class TestMakeMove {

    public static void main(String[] args) {

        JFrame f = new JFrame("ChessChamp");
        MyChessBoard cb = new MyChessBoard(f);
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

        AiPlayer aiPlayer = new AiPlayer(Color.BLACK,board,f);
        aiPlayer.makeMove(board);
    }


}
