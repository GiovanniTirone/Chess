package player;
import board.boxes.RealBox;
import lombok.Data;
import moves.BoxMoves;
import moves.Move;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class HumanPlayer extends Player {

    private Move nextMove;

    private boolean fillTheMove;
    private PropertyChangeListener changePressedListener;


    public HumanPlayer (boolean human,Color color){
        super(human,color);
        fillTheMove = false;
        changePressedListener = evt -> {
            if(evt.getSource().getClass() == RealBox.class) {
                    if(evt.getPropertyName().equals("firstPressed") && evt.getOldValue().equals(false)){
                        nextMove.setStart((RealBox)evt.getSource());
                    }
                    if(evt.getPropertyName().equals("secondPressed") && evt.getOldValue().equals(false)){
                        nextMove.setEnd((RealBox)evt.getSource());
                        fillTheMove = true;
                    }
            }
        };
    }

    @Override
    public boolean makeMove(RealBox[][] board) {

        AtomicBoolean winningMove = new AtomicBoolean(false);

        CompletableFuture.supplyAsync(() -> {
            while(!fillTheMove){
                Arrays.stream(board).forEach(row -> Arrays.stream(row)
                        .forEach(realBox -> realBox
                                .addPropertyChangeListener(changePressedListener)));
            }
            return nextMove;
        }).thenApply((move) -> {
            winningMove.set(move.makeMove(board));
            return winningMove;
        });

        return winningMove.get();

    }

    public BoxMoves getPossibleMoves (RealBox starterRealBox, RealBox[][] board) throws Exception {
        if(starterRealBox.getCurrentPiece()==null) throw new Exception("The starter box must contain a piece!!");
        return starterRealBox.getCurrentPiece().getPossibleMoves(starterRealBox,board);
    }




}
