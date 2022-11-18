package moves;
import board.boxes.IBox;


public class MakeMoveRunnable <someBox extends IBox> implements Runnable {

    someBox [][] board;

    Move move ;

    public MakeMoveRunnable(someBox [][] board, Move move){
        super();
        this.board = board;
        this.move = move;
    }


    @Override
    public void run() {
        move.makeMove();
    }
}
