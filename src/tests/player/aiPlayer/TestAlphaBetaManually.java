package tests.player.aiPlayer;

import board.MyChessBoard;
import board.boxes.FakeBox;
import board.boxes.RealBox;
import moves.FakeMove;
import pieces.Pawn;
import pieces.Piece;
import player.AiPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestAlphaBetaManually {

    public static void main(String[] args) throws InterruptedException {

        JFrame f = new JFrame("ChessChamp");
        MyChessBoard cb = new MyChessBoard(f);
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

        AiPlayer aiPlayer = new AiPlayer(Color.BLACK,board,f);
        FakeBox [][] fakeBoard = aiPlayer.createFakeBoardFromBoard(board);

        List<FakeMove>  possibleMoves = aiPlayer.getPossibleMoves(fakeBoard);

        for(FakeMove move : possibleMoves) {
            System.out.println("-----------------------------------------");
            System.out.println("Make the move: " + move);
            move.makeMove();
            print4Boxes(fakeBoard);
            Thread.sleep(5000);
            System.err.println("Undo the move");
            move.undo();
            print4Boxes(fakeBoard);
            System.out.println("-----------------------------------------");
        }



        /*Thread.sleep(2000);
        aiPlayer.makeMove(board);*/
    }


    private static void print4Boxes (FakeBox [][] fakeBoard) {
        for(int i=0; i<2; i++){
            for(int j=0;j<2;j++){
                System.out.println(fakeBoard[i][j]);
            }
        }
    }

}
