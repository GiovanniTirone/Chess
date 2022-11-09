package player;

import board.boxes.RealBox;
import lombok.Data;

import java.awt.*;

@Data
public abstract class Player {

    private boolean human;

    private Color color;

    public Player () {};

    public Player(boolean human, Color color) {
        this.human = human;
        this.color = color;
    }

    public abstract boolean makeMove(RealBox[][] board);

}