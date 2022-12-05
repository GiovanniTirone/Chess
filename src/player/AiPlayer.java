package player;

import board.ChessBoard;
import board.PlayerPieces;
import board.boxes.FakeBox;;
import board.boxes.RealBox;
import moves.FakeMove;
import moves.Move;
import moves.RealMove;
import pieces.Piece;
import javax.swing.*;
import java.awt.Color;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class AiPlayer extends Player {

    private Runnable makeMoveRunnable;

    private RealBox [][] board;

    private int standardDepth;



    public AiPlayer (Color color, RealBox [][] board) {
        super(false, color);
        this.board = board;
        this.makeMoveRunnable = ()->makeMove(board);
        this.standardDepth = 2;
    }

    public Runnable getMakeMoveWithDepthRunnable (int maxDepth) {
        return ()->makeMoveWithDepth(board,maxDepth);
    }

    public boolean makeMove (RealBox[][] board) {
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
            int tempValue = alphaBeta(fakeBoard,maxDepth,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
            move.undo();
            if(tempValue>bestValue) {
                bestValue = tempValue;
                bestMove = move;
            }
        }
        System.out.println("Best move: " + bestMove);
        return createRealMoveFromFakeMove(bestMove,board).makeMove();
    }




    private int alphaBeta ( FakeBox[][] board, int depth, boolean maximizingPlayer, int a , int b) {
        System.out.println("-----------------AlphaBeta at depth" + depth + "----------------");
        if (depth == 0) {
            int valutation = evaluateBoard(board); //aggiungere vittoria
            /*for(int i=0; i<2; i++){
                for(int j=0;j<2;j++){
                    System.err.println(board[i][j]);
                }
            }
            System.err.println("Valutation :  " + valutation);*/
            return valutation;
        }
        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (FakeMove move : getPossibleMoves(board)) {
                move.makeMove();
                value = Math.max(value, alphaBeta(board, depth - 1, false, a, b));
                move.undo();
                a = Math.max(a, value);
                if (b <= a) break; // β cutoff
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (FakeMove move : getPossibleMoves(board)) {
                move.makeMove();
                value = Math.min(value, alphaBeta(board, depth - 1, true, a, b));
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
        /*realMove.setStart(new RealBox(fakeMove.getStart().getRow(),fakeMove.getStart().getCol(),jFrame,fakeMove.getStart().getCurrentPiece()));
        realMove.setEnd(new RealBox(fakeMove.getEnd().getRow(),fakeMove.getEnd().getCol(),jFrame,fakeMove.getEnd().getCurrentPiece()));*/
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
       // System.out.println("------------getPossibleMoves from all the board --------------");
        List<FakeMove> possibleMoves = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece currentPiece = board[i][j].getCurrentPiece();
                if(currentPiece!=null && currentPiece.getColor() == getColor()) {
                    //System.out.println("get moves of box: " + i +" , " + j );
                    possibleMoves.addAll(currentPiece.getPossibleMoves(board[i][j], board));
                }
            }
        }
        //System.out.println("Possible moves: " + possibleMoves);
        return possibleMoves;
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

    public static void main(String[] args) throws Exception {

        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b) {
                //DO NOTHING
            }
        }));

        JFrame f = new JFrame("ChessChamp");
        ChessBoard cb = new ChessBoard(f);
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

        blackPieces.setStrengths(true);
        whitePieces.setStrengths(false);

        cb.addPiecesInStarterPosition(whitePieces,blackPieces);



        //IMPORTANTE: settare jFrame e board nei box listeners
        // Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setJFrame(f)));
        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setBoard(cb.getBoard())));

        cb.addBoxListeners();


        HumanPlayer p1 = new HumanPlayer(Color.WHITE,cb.getBoard());
        AiPlayer p2 = new AiPlayer(Color.BLACK,cb.getBoard());
        f.setVisible(true);

        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(p1.getIsPressedListener())));


        AtomicBoolean endTurn = new AtomicBoolean(false);




        while(true) {
            System.out.println("-------------START TURN---------------");

            endTurn.set(false);

            CompletableFuture
                    .runAsync(p1.getWaitFillTheMove())
                    .thenRun(p1.getMakeRealMove())
                    .thenRun(cb::clearPressedDatas)
                    .thenRun(p2.makeMoveRunnable)
                    .thenRun(()->endTurn.set(true));
               /* .thenRun(p1.getWaitFillTheMove())
                .thenRun(p1.getMakeRealMove())
                .thenRun(p2.makeMoveRunnable);*/

            while(!endTurn.get()){
                //System.out.println(".....wait end turn.....");
                Thread.sleep(1000);
            }

            System.out.println("-------------END TURN---------------");
        }





        // cb.getBoard()[6][2].removePieceGUI();


    }




}
