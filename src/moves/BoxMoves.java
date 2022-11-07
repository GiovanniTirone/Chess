package moves;

import board.Box;
import lombok.Data;

import java.util.ArrayList;


@Data
public class BoxMoves extends ArrayList<EndingMove> {

    private Box box;


    public BoxMoves (Box box) {
        super();
        this.box = box;
    }

    public void addMove(int row, int col, Box [][] board){
        if(box.getCurrentPiece()!=null&&board[row][col].getCurrentPiece()!=null)
        if(box.getCurrentPiece().getColor() == board[row][col].getCurrentPiece().getColor())return;
        this.add(new EndingMove(row,col));
    }

    public void removeMove(int row, int col){
        for(EndingMove endingMove : this) {
            if(endingMove.checkRowCol(row,col)) {
                this.remove(endingMove);
                break;
            }
        }
    }

    public boolean containsMove (int i, int j){
        return this.stream().anyMatch(move -> move.getRow()==i && move.getCol()==j);
    }

    @Override
    public String toString() {
        String str = "";
        for(EndingMove endingMove : this) str += "[" + endingMove.getRow() +"," + endingMove.getCol()+"] ";
        return str;
    }
}
