package player;

import board.boxes.FakeBox;
import board.boxes.RealBox;
import lombok.Data;
import moves.FakeMove;
import moves.RealMove;
import pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

;

@Data
public class AiPlayerParallel extends Player {

    private final TypePlayer type = TypePlayer.AI;

    private RealBox [][] board;

    private int standardDepth;

    public AiPlayerParallel(Color color, RealBox [][] board) {
        super(false, color);
        this.board = board;
        this.standardDepth = 2;
    }

    private class Value_Move {
        int value;
        FakeMove move;
        public Value_Move (int value, FakeMove move){
            this.value = value;
            this.move = move;
        }
        @Override
        public String toString () {
            return String.format("value: %d - move: " + move , value);
        }
    }

    public boolean makeMove () throws ExecutionException, InterruptedException {
        return makeMoveWithDepth(board,standardDepth);
    }


    public boolean makeMoveWithDepth (RealBox[][] board,int maxDepth) throws InterruptedException, ExecutionException {  //Inserire alphaBeta anche qui???
        System.out.println("-------------------Make AI move-----------------------");

        FakeBox[][] fakeBoard = createFakeBoardFromBoard(board);

        List<FakeMove> possibleMoves = getPossibleMoves(fakeBoard);
        List<Callable<Value_Move>> tasks = possibleMoves.stream().map(move -> createTask(fakeBoard,move,maxDepth)).collect(Collectors.toList());

        ExecutorService executor = Executors.newFixedThreadPool(8);
        List<Future<Value_Move>> computed_values = executor.invokeAll(tasks);

        int bestValue = Integer.MIN_VALUE;
        FakeMove bestMove = null;

        for(Future<Value_Move> future : computed_values){
            System.out.println("future number " + computed_values.indexOf(future) + " : " + future.get() );
            int tempValue = future.get().value;
            if(tempValue > bestValue) {
                bestValue = tempValue;
                bestMove = future.get().move;
            }
        }

        return createRealMoveFromFakeMove(bestMove,board).makeMove();
    }


    private FakeBox[][] copyFakeBoard (FakeBox[][] fakeBoard) {
        FakeBox [][] copy = new FakeBox[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                copy[i][j] = new FakeBox(i,j);
                copy[i][j].setCurrentPiece(
                        fakeBoard[i][j].getCurrentPiece() == null ? null : fakeBoard[i][j].getCurrentPiece().clone());
            }
        }
        return copy;
    }

    private Callable<Value_Move> createTask (FakeBox [][] fakeBoard, FakeMove move,int maxDepth) {
        return () -> {
            FakeBox[][] copyBoard = copyFakeBoard(fakeBoard);
            FakeMove copyMove = translateMove(move,copyBoard);
            copyMove.makeMove();
            int value = alphaBeta(copyBoard,maxDepth,true,Integer.MIN_VALUE,Integer.MAX_VALUE,false,false);
            return new Value_Move(value,move);
        };
    }


    private FakeMove translateMove (FakeMove move, FakeBox[][] newBoard) {
        FakeMove translateMove = new FakeMove();
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();
        int endRow = move.getEnd().getRow();
        int endCol = move.getEnd().getCol();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(i==startRow && j==startCol) translateMove.setStart(newBoard[i][j]);
                else if(i==endRow && j==endCol) translateMove.setEnd(newBoard[i][j]);
            }
        }
        return translateMove;
    }


    private int alphaBeta ( FakeBox[][] board, int depth, boolean maximizingPlayer, int a , int b, boolean winning, boolean losing) {
        if (depth == 0 || winning || losing) {
            return evaluateBoard(board);
        }
        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (FakeMove move : getPossibleMoves(board)) {
                winning = move.makeMove();
                value = Math.max(value, alphaBeta(board, depth - 1, false, a, b, winning, false));
                move.undo();
                a = Math.max(a, value);
                if (b <= a) break; // β cutoff
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (FakeMove move : getPossibleMoves(board)) {
                losing = move.makeMove();
                value = Math.min(value, alphaBeta(board, depth - 1, true, a, b, false,losing));
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
