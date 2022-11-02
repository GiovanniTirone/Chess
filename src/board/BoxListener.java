package board;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoxListener implements MouseListener {

    private Box box ;

    private boolean justPressedAnotherBox;

    public BoxListener(Box box, boolean justPressedAnotherBox){
        this.box = box;
        this.justPressedAnotherBox = justPressedAnotherBox;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //attenzione: devo essere sicuro che le condizioni justPressedAnotherBox e box.isPressed NON si verifichino contemporaneamente
        if(!justPressedAnotherBox) {
            if (box.getCurrentPiece() != null) {
                //togli il pezzo dal box
                box.setPressed(true);
            }
        }else{
            if(box.isPressed()){
                box.setPressed(false);
            }else{
                //fai l'azione in base alla mossa precedente
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
