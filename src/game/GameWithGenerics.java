package game;
import board.ChessBoard;
import board.PlayerPieces;
import player.AiPlayer;
import player.HumanPlayer;
import player.Player;
import player.TypePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;


public class GameWithGenerics<P1_type extends Player, P2_type extends Player, Turn_type extends Runnable> {

    private Player p1;

    private Player p2;

    private ChessBoard cb;

    private JFrame frame;

    private Turn_type turn;

    public GameWithGenerics(P1_type p1, P2_type p2, ChessBoard cb){
        this.p1 = p1.getType() == TypePlayer.HUMAN ? (HumanPlayer) p1 : (AiPlayer) p1;
        this.p2 = p2.getType() == TypePlayer.HUMAN ? (HumanPlayer) p2 : (AiPlayer) p2;
        this.cb = cb;
        this.frame = new JFrame();

    }


    public void initGame () {
        frame.add(cb.getGui());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());

        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);

        blackPieces.setStrengths(true);
        whitePieces.setStrengths(false);
    }




    public static void main(String[] args) throws Exception {
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

        CompletableFuture turn = new CompletableFuture();


        while(true) {
            System.out.println("-------------START TURN---------------");

            endTurn.set(false);

            turn.thenRun(p1.getWaitFillTheMove())
                    .thenRun(p1.getMakeRealMove())
                    .thenRun(cb::clearPressedDatas)
                    .thenRun(p2.getMakeMoveRunnable());
            /*
            CompletableFuture
                    .runAsync(p1.getWaitFillTheMove())
                    .thenRun(p1.getMakeRealMove())
                    .thenRun(cb::clearPressedDatas)
                    .thenRun(p2.makeMoveRunnable)
                    .thenRun(()->endTurn.set(true));
               /* .thenRun(p1.getWaitFillTheMove())
                .thenRun(p1.getMakeRealMove())
                .thenRun(p2.makeMoveRunnable);*/
            /*
            while(!endTurn.get()){
                //System.out.println(".....wait end turn.....");
                Thread.sleep(1000);
            }*/

            System.out.println("-------------END TURN---------------");
        }



    }
}
