package player;
import board.boxes.RealBox;
import moves.BoxMoves;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class HumanPlayer extends Player {

    public HumanPlayer (boolean human,Color color){
        super(human,color);
    }

    @Override
    public boolean makeMove(RealBox[][] board) {
        CompletableFuture.supplyAsync(() -> {

        })
    }

    public BoxMoves getPossibleMoves (RealBox starterRealBox, RealBox[][] board) throws Exception {
        if(starterRealBox.getCurrentPiece()==null) throw new Exception("The starter box must contain a piece!!");
        return starterRealBox.getCurrentPiece().getPossibleMoves(starterRealBox,board);
    }




}
