package moves;
import board.boxes.IBox;
import lombok.Data;
import java.util.ArrayList;

@Data
public class BoxMoves  extends ArrayList <FakeMove> {

    private IBox box;

    public BoxMoves (IBox box) {
        super();
        this.box = box;
    }

    public void addMove(int row, int col, IBox[][] board){
        if(box.getCurrentPiece()!=null&&board[row][col].getCurrentPiece()!=null)
        if(box.getCurrentPiece().getColor() == board[row][col].getCurrentPiece().getColor()) return;
        this.add( new FakeMove( board[box.getRow()][box.getCol()],  board[row][col]));
    }


    public void removeMove(int row, int col){
        for(FakeMove move : this) {
            if(move.checkEndingRowCol(row,col)) {
                this.remove(move);
                break;
            }
        }
    }

    public boolean containsEndingMove(int i, int j){
        return this.stream().anyMatch(move -> move.getEnd().getRow()==i && move.getEnd().getCol()==j);
    }

    @Override
    public String toString() {
        String str = "";
        for(Move move : this) str += "[" + move.getEnd().getRow() +"," + move.getEnd().getCol()+"] ";
        return str;
    }
}
