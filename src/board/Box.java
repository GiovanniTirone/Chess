package board;
import lombok.Data;
import pieces.Piece;
import java.awt.*;
import java.awt.event.MouseEvent;

@Data
public class Box extends Button {

    private Piece currentPiece;

    private int x; // x coordinate

    private int y; // y coordinate

    private boolean pressed;

    public Box(int x,int y){
        this.x = x;
        this.y = y;
        this.currentPiece = null;
        this.pressed = false;
    }

    public void activateClickListener () {
        this.addMouseListener(new BoxListener(this,false));
    }

    public void removeClickListener ( ){
        this.removeMouseListener();
    }

}
