package player;

import lombok.Data;
import java.awt.*;
import java.util.function.Supplier;

@Data
public abstract class Player {

    private boolean human;

    private Color color;

    public Player () {};

    public Player(boolean human, Color color) {
        this.human = human;
        this.color = color;
    }

    public Supplier<Boolean> makeMove;



}