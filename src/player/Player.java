package player;

import board.boxes.IBox;
import board.boxes.RealBox;
import lombok.Data;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
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