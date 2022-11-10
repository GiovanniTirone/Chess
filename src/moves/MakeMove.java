package moves;
import board.boxes.IBox;
import java.util.function.Function;


public class MakeMove implements Function {

    IBox[][] board;

    Move move ;

    public MakeMove (IBox[][] board, Move move){
        super();
        this.board = board;
        this.move = move;
    }



    @Override
    public Boolean apply(Object o) {
        return move.makeMove(board);
    }

    @Override
    public Function andThen(Function after) {
        return Function.super.andThen(after);
    }

    @Override
    public Function compose(Function before) {
        return Function.super.compose(before);
    }
}
