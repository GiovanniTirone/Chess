package game;
import board.ChessBoard;
import board.PlayerPieces;
import player.AiPlayer;
import player.HumanPlayer;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Game  {

    private HumanPlayer p1;

    private AiPlayer p2;

    private ChessBoard cb;

    private JFrame frame;

    private Runnable turn;

    private Winner winner;

    public Game(){
        this.frame = new JFrame();
        this.cb = new ChessBoard(frame);
        this.p1 = new HumanPlayer(Color.WHITE,cb.getBoard());
        this.p2 = new AiPlayer(Color.BLACK,cb.getBoard());
        this.winner = Winner.NONE;
        this.turn = () -> {
            while(true) {

                p1.waitFillTheMove();
                if (p1.makeMove()){
                    winner = Winner.P1;
                    break;
                }
                cb.clearPressedDatas();
                if (p2.makeMove()){
                    winner = Winner.P2;
                    break;
                };

            }

            System.out.println(winner.name() +  " has win the game!!!");
        };
        p2.setStandardDepth(5);
    }


    public void initGame () throws Exception {
        frame.add(cb.getGui());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());

        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);

        blackPieces.setStrengths(true);
        whitePieces.setStrengths(false);

        cb.addPiecesInStarterPosition(whitePieces,blackPieces);

        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setBoard(cb.getBoard())));
        cb.addBoxListeners();

        frame.setVisible(true);

        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(p1.getIsPressedListener())));
    }

    public void startGame () {
        Thread game = new Thread(turn);
        game.start();
    }



    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.initGame();
        game.startGame();

    }
}
