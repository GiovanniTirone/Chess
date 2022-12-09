package player;

import lombok.Data;
import java.awt.*;
import java.util.concurrent.ExecutionException;


@Data
public abstract class Player {

    private TypePlayer type;

    private boolean human;

    private Color color;

    public Player () {};

    public Player(boolean human, Color color) {
        this.human = human;
        this.color = color;
    }

    public abstract boolean makeMove() throws ExecutionException, InterruptedException;



}