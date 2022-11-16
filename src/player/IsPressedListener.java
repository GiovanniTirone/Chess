package player;

import board.boxes.RealBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class IsPressedListener implements PropertyChangeListener {

    private HumanPlayer humanPlayer ;

    public IsPressedListener(HumanPlayer humanPlayer){
        this.humanPlayer = humanPlayer;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("I AM LISTENING the event: " + evt);
        if(evt.getPropertyName().equals("firstPressed")){
            humanPlayer.getNextMove().setStart((RealBox)evt.getSource());
        }
        if(evt.getPropertyName().equals("secondPressed")){
            humanPlayer.getNextMove().setEnd((RealBox)evt.getSource());
            System.out.println("Next move: " + humanPlayer.getNextMove());
        }
    }
}
