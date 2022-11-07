package player;
import board.Box;
import moves.BoxMoves;

import java.awt.*;

public class HumanPlayer extends Player{

    public HumanPlayer (boolean human,Color color){
        super(human,color);
    }

    public BoxMoves getPossibleMoves (Box starterBox , Box[][] board) throws Exception {
        if(starterBox.getCurrentPiece()==null) throw new Exception("The starter box must contain a piece!!");
        return starterBox.getCurrentPiece().getPossibleMoves(starterBox,board);
    }



}
