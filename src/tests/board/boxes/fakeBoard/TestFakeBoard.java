package tests.board.boxes.fakeBoard;

import board.MyChessBoard;
import board.boxes.FakeBox;
import board.boxes.RealBox;
import moves.FakeMove;
import pieces.Pawn;
import pieces.Piece;
import player.AiPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TestFakeBoard {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        MyChessBoard cb = new MyChessBoard(jFrame);

        RealBox [][] board =   cb.getBoard();
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.BLACK);
        piece1.setStrength(false);
        piece2.setStrength(true);
        board[1][1].addPiece(piece1);
        board[0][0].addPiece(piece2);
        //cb.printBoard();

        AiPlayer player = new AiPlayer(Color.BLACK,board,jFrame);

        FakeBox [][] fakeBoard = player.createFakeBoardFromBoard(board);
        System.out.println("-------------FAKE BOARD BEFORE MOVING---------------");
        //printFakeBoard(fakeBoard);
        System.out.println(player.evaluateBoard(fakeBoard));


        FakeMove killingMove = new FakeMove(fakeBoard[1][1],fakeBoard[0][0]);
        killingMove.makeMove();
        System.out.println("-------------FAKE BOARD AFTER MOVING---------------");
        System.out.println(player.evaluateBoard(fakeBoard));
       // printFakeBoard(fakeBoard);



    }

    public static void printFakeBoard(FakeBox [][] fakeBoard) {
        Arrays.stream(fakeBoard).forEach(row -> Arrays.stream(row).forEach(box -> System.out.println(box)));
    }




}
