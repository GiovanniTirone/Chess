package player;

import java.awt.*;

public abstract class Player {

    private boolean human;

    private Color color;

    public Player (boolean human,Color color){
        this.human = human;
        this.color = color;
    }
}
