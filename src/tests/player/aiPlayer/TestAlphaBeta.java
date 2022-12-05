package tests.player.aiPlayer;

import board.ChessBoard;
import board.boxes.RealBox;
import pieces.Pawn;
import pieces.Piece;
import player.AiPlayer;

import javax.swing.*;
import java.awt.*;

public class TestAlphaBeta {

    public static void main(String[] args) throws InterruptedException {

        JFrame f = new JFrame("ChessChamp");
        ChessBoard cb = new ChessBoard(f);
        RealBox[][] board = cb.getBoard();
        f.add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);

        Piece blackPawn = new Pawn(Color.BLACK);
        blackPawn.setStrength(true);
        Piece whitePawn = new Pawn(Color.WHITE);
        whitePawn.setStrength(false);

        board[0][0].addPiece(blackPawn);
        board[0][0].addPieceGUI();

        board[1][1].addPiece(whitePawn);
        board[1][1].addPieceGUI();
        f.setVisible(true);

        AiPlayer aiPlayer = new AiPlayer(Color.BLACK,board);
        Thread.sleep(2000);
        aiPlayer.makeMove(board);
    }


}
