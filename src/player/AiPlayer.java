package player;
import board.MyChessBoard;
import board.PlayerPieces;
import board.boxes.FakeBox;
import board.boxes.IBox;
import board.boxes.RealBox;
import moves.FakeMove;
import moves.Move;
import moves.RealMove;

import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AiPlayer extends Player {

    private Runnable makeMoveRunnable;

    private RealBox [][] board;

    JFrame jFrame;

    public AiPlayer (boolean human, Color color, RealBox [][] board,JFrame jFrame) {
        super(human, color);
        this.board = board;
        this.jFrame = jFrame;
        makeMoveRunnable = () -> makeMove(board);
    }


    public boolean makeMove (RealBox[][] board) {  //Inserire alphaBeta anche qui???
        FakeBox[][] fakeBoard = createFakeBoardFromBoard(board);
        List<FakeMove> possibleMoves = getPossibleMoves(fakeBoard);
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = null;
        for(Move move : possibleMoves){
            move.makeMove();
            int tempValue = alphaBeta(fakeBoard,0,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
            move.undo();
            if(tempValue>bestValue) {
                bestValue = tempValue;
                bestMove = move;
            }
        }
        return bestMove.makeMove();
    }




    private int miniMax (FakeBox[][] board, int depth, boolean maximizingPlayer) {
        if(depth == 3 ) return evaluateBoard(board); //aggiungere condizione di VITTORIA
        if(maximizingPlayer){
            int value = Integer.MIN_VALUE;
            for(Move move : getPossibleMoves(board)){
               // move.makeMove();
                value = Math.max(value,miniMax(board,depth+1,false));
               // move.undo(board);
            }
            return value;
        }else{
            int value = Integer.MAX_VALUE;
            for(Move move : getPossibleMoves(board)){
                move.makeMove();
                value = Math.min(value,miniMax(board,depth+1,true));
               // move.undo(board);
            }
            return value;
        }
    }

    private int alphaBeta ( FakeBox[][] board, int depth, boolean maximizingPlayer, int a , int b) {
        if (depth == 3) return evaluateBoard(board); //aggiungere vittoria
        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (Move move : getPossibleMoves(board)) {
                move.makeMove();
                value = Math.max(value, alphaBeta(board, depth + 1, false, a, b));
                move.undo();
                if (value >= b) break; // β cutoff
                a = Math.max(a, value);
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (Move move : getPossibleMoves(board)) {
                move.makeMove();
                value = Math.min(value, alphaBeta(board, depth + 1, true, a, b));
                move.undo();
                if (value <= a) break; // α cutoff
                b = Math.min(b, value);
            }
            return value;
        }
    }

    private FakeBox createFakeBoxFromBox (RealBox realBox) {
        return new FakeBox(realBox.getRow(), realBox.getCol());
    }

    private FakeBox[][] createFakeBoardFromBoard (RealBox[][] board){
        FakeBox[][] fakeBoxes = new FakeBox[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                fakeBoxes[i][j] =  createFakeBoxFromBox(board[i][j]);
            }
        }
        return fakeBoxes;
    }

    private RealMove createRealMoveFromFakeMove (FakeMove fakeMove){
        RealMove realMove = new RealMove(jFrame);
        realMove.setStart(new RealBox(fakeMove.getStart().getRow(),fakeMove.getStart().getCol()));
        realMove.setEnd(new RealBox(fakeMove.getEnd().getRow(),fakeMove.getEnd().getCol()));
        return realMove;
    }

    private int evaluateBoard(FakeBox[][]board) {
        int result = 0;
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getCurrentPiece()!=null)
                    result += board[i][j].getCurrentPiece().getStrength();
            }
        }
        return result;
    }

    private List<FakeMove> getPossibleMoves (FakeBox[][] board){
        List<FakeMove> possibleMoves = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getCurrentPiece()!=null) {
                    board[i][j].getCurrentPiece()
                                .getPossibleMoves( board[i][j], board)
                                .stream()
                                .forEach(move-> possibleMoves.add(move));
                }
            }
        }
        return possibleMoves;
    }

    public static void main(String[] args) throws Exception {
        MyChessBoard cb = new MyChessBoard();
        JFrame f = new JFrame("ChessChamp");
        f.add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());

        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);

        cb.addPiecesInStarterPosition(whitePieces,blackPieces);



        //IMPORTANTE: settare jFrame e board nei box listeners
        // Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setJFrame(f)));
        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setBoard(cb.getBoard())));

        cb.addBoxListeners();


        HumanPlayer p1 = new HumanPlayer(Color.WHITE,cb.getBoard(),f);
        AiPlayer p2 = new AiPlayer(false,Color.BLACK,cb.getBoard(),f);


        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(p1.getIsPressedListener())));


        CompletableFuture p1_turn = CompletableFuture.runAsync(p1.getWaitFillTheMove())
                .thenRun(p1.getMakeRealMove())
                .thenRun(p2.makeMoveRunnable);

        // cb.getBoard()[6][2].removePieceGUI();

        f.setVisible(true);
    }




}
