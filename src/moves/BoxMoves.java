package moves;

import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

@Data
public class BoxMoves extends ArrayList<Move> {

    public BoxMoves () {
        super();
    }

    public void addMove(int row,int col){
        this.add(new Move(row,col));
    }

    public void removeMove(int row, int col){
        for(Move move : this) {
            if(move.checkRowCol(row,col)) {
                this.remove(move);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String str = "";
        for(Move move : this) str += "[" +move.getRow() +"," + move.getCol()+"] ";
        return str;
    }
}
