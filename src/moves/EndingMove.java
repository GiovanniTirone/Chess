package moves;
import lombok.Data;

@Data
public class EndingMove {
    private int row;
    private int col;

    public EndingMove(int row, int col){
        this.row = row;
        this.col = col;
    }

    public boolean equals (EndingMove endingMove){
        return checkRowCol(endingMove.getRow(), endingMove.getCol());
    }

    public boolean checkRowCol (int row, int col){
        return this.row == row && this.col == col ? true : false;
    }

}
