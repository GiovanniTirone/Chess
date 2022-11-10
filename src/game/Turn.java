package game;
import player.Player;
import java.util.concurrent.CompletableFuture;


public class Turn extends CompletableFuture {

    Player currentPlayer;

    public Turn (Player player) {
        this.currentPlayer = player;
    }

}
