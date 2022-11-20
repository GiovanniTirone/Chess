package player;
import board.MyChessBoard;
import board.PlayerPieces;
import board.boxes.RealBox;
import lombok.Data;
import moves.BoxMoves;
import moves.MakeRealMoveRunnable;
import moves.Move;
import moves.RealMove;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;


@Data
public class HumanPlayer extends Player {

    private RealBox[][] board;

    private RealMove nextMove;

    private Runnable makeRealMove;

    private Runnable waitFillTheMove;

    private boolean fillTheMove;
    private IsPressedListener isPressedListener;


    public HumanPlayer (Color color,RealBox[][] board,JFrame jFrame){
        super(true,color);
        this.board = board;
        fillTheMove = false;
        nextMove = new RealMove(jFrame);
        this.isPressedListener = new IsPressedListener(this);

        this.waitFillTheMove = () -> {
            while(!fillTheMove) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("..........wait fill the move...........");
            }
        } ;

        this.makeRealMove = () -> {
            nextMove.makeMove();
        };

    }


    public BoxMoves getPossibleMoves (RealBox starterRealBox, RealBox[][] board) throws Exception {
        if(starterRealBox.getCurrentPiece()==null) throw new Exception("The starter box must contain a piece!!");
        return starterRealBox.getCurrentPiece().getPossibleMoves(starterRealBox,board);
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
      //  Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setJFrame(f)));
        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setBoard(cb.getBoard())));

        cb.addBoxListeners();


        HumanPlayer p1 = new HumanPlayer(Color.WHITE,cb.getBoard(),f);


        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(p1.getIsPressedListener())));


        CompletableFuture p1_turn = new CompletableFuture<>();

/*
        p1_turn.thenRunAsync(p1.AddChangePressedListenersUntilFillTheMove)
                        .thenApply(p1.makeRealMove);
*/
        f.setVisible(true);



    }


}
