package moves;
import board.boxes.RealBox;

public class MakeRealMoveRunnable extends MakeMoveRunnable {
    public MakeRealMoveRunnable(RealBox[][] board, Move move) {
        super(board, move);
    }

    @Override
    public void run() {
        move.makeRealMove((RealBox[][])board);
    }
}
