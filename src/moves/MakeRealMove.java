package moves;
import board.boxes.RealBox;

public class MakeRealMove extends MakeMove {
    public MakeRealMove(RealBox[][] board, Move move) {
        super(board, move);
    }

    @Override
    public Boolean apply(Object o) {
        return move.makeRealMove((RealBox[][])board);
    }
}
