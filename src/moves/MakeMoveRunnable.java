package moves;
import board.boxes.IBox;


public class MakeMoveRunnable implements Runnable {

    IBox[][] board;

    Move move ;

    public MakeMoveRunnable(IBox[][] board, Move move){
        super();
        this.board = board;
        this.move = move;
    }


    @Override
    public void run() {
        move.makeMove();
    }
}
