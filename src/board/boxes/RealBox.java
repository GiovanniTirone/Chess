package board.boxes;
import Sources.PieceGUI;
import lombok.Data;
import pieces.Piece;
import player.IsPressedListener;

import javax.swing.*;
import java.awt.*;
import java.beans.BeanProperty;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


@Data
public class RealBox extends JButton implements IBox {

    private Piece currentPiece;
    private int row; // row coordinate
    private int col; // col coordinate
    private BoxListener pressListener;
    private boolean isBlack;

    private boolean firstPressed;
    private boolean secondPressed;

    private PropertyChangeSupport propertyChangeSupport;


    public RealBox(int x, int y){
        this.row = x;
        this.col = y;
        this.firstPressed = false;
        this.secondPressed = false;
        this.pressListener = new BoxListener(this,null);
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPiece(Piece piece) {
        this.currentPiece = piece;
        currentPiece.setJLabel(
                PieceGUI.getJLabelFromUnicodeChar(piece.getUnicodeChar(),piece.getColor(), Color.DARK_GRAY, isBlack));
        this.add(this.currentPiece.getJLabel());
    }

    public void removePiece(){
        if(this.currentPiece!=null){
           // currentPiece.setJLabel(null);
            this.currentPiece = null;
        }
    }

    public void activateClickListener () {
        this.addMouseListener(pressListener);
    }

    public void removeClickListener ( ){
        this.removeMouseListener(pressListener);
    }


    public void removePieceGUI () {
        this.remove(this.currentPiece.getJLabel());
    }

    public void addPieceGUI (){
       this.add(this.currentPiece.getJLabel());
    }

    public void addIsPressedListener (IsPressedListener isPressedListener) {
        propertyChangeSupport.addPropertyChangeListener(isPressedListener);
    }

    public boolean isBorderBox () {
        return (row==0 || row==7 || col==0 || col==7) ? true : false;
    }

    @Override
    public String toString() {
        return "Box (" + row  + ","  +col +")"  +
                "\n currentPiece: " + currentPiece +
                //"\n pressListener: " + pressListener +
                "\nfirstPressed=" + firstPressed +
                "\nsecondPressed=" + secondPressed +
                "\nisBlack=" + isBlack ;
    }
}
