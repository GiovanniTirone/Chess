package board;

import lombok.Data;
import pieces.Piece;

import javax.swing.*;

@Data
public class MyBox extends JButton {

    private Piece currentPiece;
    private MyListener myListener;

    private boolean pressed;

    public MyBox(){
        this.pressed = false;
        this.myListener = new MyListener(this,false);
        this.currentPiece = null;
    }

    public void activateClickListener () {
        this.addMouseListener(myListener);
    }

    public void removeClickListener ( ){
        this.removeMouseListener(myListener);
    }

}
