package game;
import player.Player;

import javax.swing.*;
import java.util.function.Supplier;

public class Turn implements Supplier<Boolean> {

    Player currentPlayer;


    public Turn (Player player) {
        this.currentPlayer = player;
    }

    @Override
    public Boolean get(Box[][]board) {

        return
    }
}
