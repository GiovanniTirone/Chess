package player;

import board.ChessBoard;
import board.PlayerPieces;
import board.boxes.FakeBox;;
import board.boxes.RealBox;
import lombok.Data;
import moves.FakeMove;
import moves.Move;
import moves.RealMove;
import pieces.Piece;
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class AiPlayer extends Player {

    private final TypePlayer type = TypePlayer.AI;

    private RealBox [][] board;

    private int standardDepth;



    public AiPlayer (Color color, RealBox [][] board) {
        super(false, color);
        this.board = board;
        this.standardDepth = 2;
    }


    public boolean makeMove () {
        return makeMoveWithDepth(board,standardDepth);
    }


    public boolean makeMoveWithDepth (RealBox[][] board,int maxDepth) {  //Inserire alphaBeta anche qui???
        System.out.println("-------------------Make AI move-----------------------");
        FakeBox[][] fakeBoard = createFakeBoardFromBoard(board);
        //System.err.println("fakeboard" + fakeBoard);
        List<FakeMove> possibleMoves = getPossibleMoves(fakeBoard);
       // System.err.println(possibleMoves);
        int bestValue = Integer.MIN_VALUE;
        FakeMove bestMove = null;
        for(FakeMove move : possibleMoves){
            move.makeMove();
            int tempValue = alphaBeta(fakeBoard,maxDepth,true,Integer.MIN_VALUE,Integer.MAX_VALUE,false,false);
            move.undo();
            if(tempValue>bestValue) {
                bestValue = tempValue;
                bestMove = move;
            }
        }
        System.out.println("Best move: " + bestMove);
        return createRealMoveFromFakeMove(bestMove,board).makeMove();
    }




    private int alphaBeta ( FakeBox[][] board, int depth, boolean maximizingPlayer, int a , int b, boolean winning, boolean losing) {
        if (depth == 0 || winning || losing) {
            int valutation = evaluateBoard(board);
            return valutation;
        }
        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (FakeMove move : getPossibleMoves(board)) {
                winning = move.makeMove();
                value = Math.max(value, alphaBeta(board, depth - 1, false, a, b, winning, losing));
                move.undo();
                a = Math.max(a, value);
                if (b <= a) break; // β cutoff
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (FakeMove move : getPossibleMoves(board)) {
                losing = move.makeMove();
                value = Math.min(value, alphaBeta(board, depth - 1, true, a, b,winning,losing));
                move.undo();
                b = Math.min(b, value);
                if (b <= a) break; // α cutoff
            }
            return value;
        }
    }

    public FakeBox createFakeBoxFromBox (RealBox realBox) {
        FakeBox fakeBox = new FakeBox(realBox.getRow(), realBox.getCol());
        fakeBox.setCurrentPiece(realBox.getCurrentPiece());
        return fakeBox;
    }

    public FakeBox[][] createFakeBoardFromBoard (RealBox[][] board){
        FakeBox[][] fakeBoxes = new FakeBox[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                fakeBoxes[i][j] =  createFakeBoxFromBox(board[i][j]);
            }
        }
        return fakeBoxes;
    }

    public RealBox findRealBoxFromFakeBox (FakeBox fakeBox,RealBox[][] board) {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                RealBox currentRealBox = board[i][j];
                if (currentRealBox.getRow() == fakeBox.getRow() && currentRealBox.getCol() == fakeBox.getCol())
                    return currentRealBox;
            }
        }
        return null;
    }


    public RealMove createRealMoveFromFakeMove (FakeMove fakeMove,RealBox[][] board){
        RealMove realMove = new RealMove();
        realMove.setStart(findRealBoxFromFakeBox((FakeBox) fakeMove.getStart(),board));
        realMove.setEnd(findRealBoxFromFakeBox((FakeBox) fakeMove.getEnd(),board));
        return realMove;
    }

    public int evaluateBoard(FakeBox[][]board) {
        int result = 0;
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getCurrentPiece()!=null)
                    result += board[i][j].getCurrentPiece().getStrength();
            }
        }
        return result;
    }

    public List<FakeMove> getPossibleMoves (FakeBox[][] board){
        List<FakeMove> possibleMoves = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece currentPiece = board[i][j].getCurrentPiece();
                if(currentPiece!=null && currentPiece.getColor() == getColor()) {
                    possibleMoves.addAll(currentPiece.getPossibleMoves(board[i][j], board));
                }
            }
        }
        return possibleMoves;
    }



}
