package board;
import lombok.Data;
import pieces.Piece;
import javax.swing.*;

@Data
public class Box extends JButton {

    private Piece currentPiece;

    private int x; // x coordinate

    private int y; // y coordinate

    private BoxListener pressListener;

    private boolean pressed;

    public Box(int x,int y){
        this.x = x;
        this.y = y;
        this.currentPiece = null;
        this.pressed = false;
        this.pressListener = new BoxListener(this,false);
    }

    public void activateClickListener () {
        this.addMouseListener(pressListener);
    }

    public void removeClickListener ( ){
        this.removeMouseListener(pressListener);
    }

}
