package moves;
import board.boxes.RealBox;

public class MakeRealMoveRunnable extends MakeMoveRunnable {
    public MakeRealMoveRunnable(RealBox[][] board, RealMove move) {
        super(board, move);
    }

    @Override
    public void run() {
        move.makeMove();
    }
}
