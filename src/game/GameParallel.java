package game;

import board.ChessBoard;
import board.PlayerPieces;
import player.AiPlayer;
import player.AiPlayerParallel;
import player.HumanPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class GameParallel {

    private HumanPlayer p1;

    private AiPlayerParallel p2;

    private ChessBoard cb;

    private JFrame frame;

    private Runnable turn;

    private Winner winner;

    public GameParallel() {
        this.frame = new JFrame();
        this.cb = new ChessBoard(frame);
        this.p1 = new HumanPlayer(Color.WHITE, cb.getBoard());
        this.p2 = new AiPlayerParallel(Color.BLACK, cb.getBoard());
        this.winner = Winner.NONE;
        this.turn = () -> {
            while (true) {

                p1.waitFillTheMove();
                if (p1.makeMove()) {
                    winner = Winner.P1;
                    break;
                }
                cb.clearPressedDatas();
                try {
                    if (p2.makeMove()) {
                        winner = Winner.P2;
                        break;
                    }
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ;

            }

            System.out.println(winner.name() + " has win the game!!!");
        };
        p2.setStandardDepth(6);
    }



    public void initChessBoard() {
        frame.add(cb.getGui());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(box -> box.getPressListener().setBoard(cb.getBoard())));
        cb.addBoxListeners();
        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox.addIsPressedListener(p1.getIsPressedListener())));
        frame.setVisible(true);
    }


    public void initPieces() throws Exception {
        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);

        blackPieces.setStrengths(true);
        whitePieces.setStrengths(false);

        cb.addPiecesInStarterPosition(whitePieces, blackPieces);

        SwingUtilities.updateComponentTreeUI(frame);

    }



    public void startGame () {
        Thread game = new Thread(turn);
        game.start();
    }



    public static void main(String[] args) throws Exception {
        GameParallel game = new GameParallel();
        game.initChessBoard();
        game.initPieces();
        game.startGame();

    }
}
