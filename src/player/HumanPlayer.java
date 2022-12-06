package player;
import board.ChessBoard;
import board.PlayerPieces;
import board.boxes.RealBox;
import lombok.Data;
import moves.BoxMoves;
import moves.RealMove;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;


@Data
public class HumanPlayer extends Player {

    private final TypePlayer type = TypePlayer.HUMAN;
    private RealBox[][] board;

    private RealMove nextMove;

    private boolean fillTheMove;
    private IsPressedListener isPressedListener;


    public HumanPlayer (Color color,RealBox[][] board){
        super(true,color);
        this.board = board;
        fillTheMove = false;
        nextMove = new RealMove();
        this.isPressedListener = new IsPressedListener(this);

    }

    public boolean makeMove () {
        if(nextMove.makeMove()) return true;
        nextMove.setStart(null);
        nextMove.setEnd(null);
        fillTheMove = false;
        return false;
    }

    public BoxMoves getPossibleMoves (RealBox starterRealBox, RealBox[][] board) throws Exception {
        if(starterRealBox.getCurrentPiece()==null) throw new Exception("The starter box must contain a piece!!");
        return starterRealBox.getCurrentPiece().getPossibleMoves(starterRealBox,board);
    }

    public void waitFillTheMove () {
        while(!fillTheMove) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("..........wait fill the move...........");
        }
    }



}
