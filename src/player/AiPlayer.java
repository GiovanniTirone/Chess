package player;

import board.MyChessBoard;
import board.PlayerPieces;
import board.boxes.FakeBox;;
import board.boxes.RealBox;
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

public class AiPlayer extends Player {

    private Runnable makeMoveRunnable;

    private RealBox [][] board;

    JFrame jFrame;

    public AiPlayer (Color color, RealBox [][] board,JFrame jFrame) {
        super(false, color);
        this.board = board;
        this.jFrame = jFrame;
        makeMoveRunnable = () -> makeMove(board);
    }


    public boolean makeMove (RealBox[][] board) {  //Inserire alphaBeta anche qui???
        System.out.println("-------------------Make AI move-----------------------");
        FakeBox[][] fakeBoard = createFakeBoardFromBoard(board);
        System.out.println("fakeboard" + fakeBoard);
        List<FakeMove> possibleMoves = getPossibleMoves(fakeBoard);
        int bestValue = Integer.MIN_VALUE;
        FakeMove bestMove = null;
        for(FakeMove move : possibleMoves){
            move.makeMove();
            int tempValue = alphaBeta(fakeBoard,0,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
            move.undo();
            if(tempValue>bestValue) {
                bestValue = tempValue;
                bestMove = move;
            }
        }
        System.out.println("Best move: " + bestMove);
        return createRealMoveFromFakeMove(bestMove,board).makeMove();
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
        System.out.println("-----------------AlphaBeta at depth" + depth + "----------------");
        if (depth == 2) return evaluateBoard(board); //aggiungere vittoria
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
        realMove.setStart(findRealBoxFromFakeBox(fakeMove.getStart(),board));
        realMove.setEnd(findRealBoxFromFakeBox(fakeMove.getEnd(),board));
        /*realMove.setStart(new RealBox(fakeMove.getStart().getRow(),fakeMove.getStart().getCol(),jFrame,fakeMove.getStart().getCurrentPiece()));
        realMove.setEnd(new RealBox(fakeMove.getEnd().getRow(),fakeMove.getEnd().getCol(),jFrame,fakeMove.getEnd().getCurrentPiece()));*/
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

    public List<FakeMove> getPossibleMoves (FakeBox[][] board){
        System.out.println("------------getPossibleMoves from all the board --------------");
        List<FakeMove> possibleMoves = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece currentPiece = board[i][j].getCurrentPiece();
                if(currentPiece!=null) {
                    //System.out.println("get moves of box: " + i +" , " + j );
                    currentPiece.getPossibleMoves( board[i][j], board)
                            .forEach(move-> possibleMoves.add((FakeMove) move));
                }
            }
        }
        System.out.println("Possible moves: " + possibleMoves);
        return possibleMoves;
    }

    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame("ChessChamp");
        MyChessBoard cb = new MyChessBoard(f);
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
        AiPlayer p2 = new AiPlayer(Color.BLACK,cb.getBoard(),f);


        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(p1.getIsPressedListener())));


        CompletableFuture p1_turn = CompletableFuture.runAsync(p1.getWaitFillTheMove())
                .thenRun(p1.getMakeRealMove())
                .thenRun(p2.makeMoveRunnable)
                .thenRun(()->{
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        // cb.getBoard()[6][2].removePieceGUI();

        f.setVisible(true);
    }




}
