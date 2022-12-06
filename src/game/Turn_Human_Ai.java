package game;
import board.ChessBoard;
import player.AiPlayer;
import player.HumanPlayer;
import player.Player;


public class Turn_Human_Ai implements Runnable {

    private HumanPlayer p1;

    private AiPlayer p2;

    private Winner winner;

    private ChessBoard cb;

    public Turn_Human_Ai(HumanPlayer p1, AiPlayer p2,ChessBoard cb) {
        this.p1 = p1;
        this.p2 = p2;
        this.winner = Winner.NONE;
        this.cb = cb;
    }

    @Override
    public void run() {

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

    }
}
