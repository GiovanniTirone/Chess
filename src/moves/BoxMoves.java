package moves;
import board.boxes.Box;
import board.boxes.RealBox;
import lombok.Data;
import board.boxes.FakeBox;

import java.util.ArrayList;


@Data
public class BoxMoves extends ArrayList<Move> {

    private Box box;


    public BoxMoves (RealBox box) {
        super();
        this.box = box;
    }

    public void addMove(int row, int col, RealBox[][] board){
        if(box.getCurrentPiece()!=null&&board[row][col].getCurrentPiece()!=null)
        if(box.getCurrentPiece().getColor() == board[row][col].getCurrentPiece().getColor())return;
        this.add(new Move(new FakeBox(box.getRow(),box.getCol()) ,new FakeBox(row,col)));
    }

    public void removeMove(int row, int col){
        for(Move move : this) {
            if(move.checkEndingRowCol(row,col)) {
                this.remove(move);
                break;
            }
        }
    }

    public boolean containsMove (int i, int j){
        return this.stream().anyMatch(move -> move.getEnd().getRow()==i && move.getEnd().getCol()==j);
    }

    @Override
    public String toString() {
        String str = "";
        for(Move move : this) str += "[" + move.getEnd().getRow() +"," + move.getEnd().getCol()+"] ";
        return str;
    }
}
