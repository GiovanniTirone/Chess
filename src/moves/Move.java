package moves;
import board.boxes.Box;
import board.boxes.RealBox;
import lombok.Data;

@Data
public  class Move {

    private Box start;
    private Box end;

    public Move(Box startingBox, Box endingBox) {
        this.start = startingBox;
        this.end = endingBox;
    }

    public void makeMove (RealBox[][] board) {
        board[end.getRow()][end.getCol()].addPiece(
                board[start.getRow()][start.getCol()].getCurrentPiece());
        board[start.getRow()][start.getCol()].removePiece();
    }

    public boolean checkEndingRowCol (int row, int col){
        return end.getRow()==row&&end.getCol()==col ? true : false;
    }

}
