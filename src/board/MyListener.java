package board;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyListener implements MouseListener {

    private MyBox myBox;

    boolean justPressedAnotherBox;

    public  MyListener (MyBox myBox, boolean justPressedAnotherBox) {
        this.myBox = myBox;
        this.justPressedAnotherBox = justPressedAnotherBox;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //attenzione: devo essere sicuro che le condizioni justPressedAnotherBox e box.isPressed NON si verifichino contemporaneamente
        if(!justPressedAnotherBox) {
            if (myBox.getCurrentPiece() != null) {
                //togli il pezzo dal box
                myBox.setPressed(true);
            }
        }else{
            if(myBox.isPressed()){
                myBox.setPressed(false);
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
