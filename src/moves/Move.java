package moves;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Move  {
    private int row;
    private int col;

    public Move(int row,int col){
        this.row = row;
        this.col = col;
    }

    public boolean equals (Move move){
        return checkRowCol(move.getRow(), move.getCol());
    }

    public boolean checkRowCol (int row, int col){
        return this.row == row && this.col == col ? true : false;
    }

}
