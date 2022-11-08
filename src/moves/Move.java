package moves;
import board.boxes.IBox;
import board.boxes.RealBox;
import lombok.Data;

@Data
public  class Move {

    private IBox start;
    private IBox end;

    public Move(IBox startingBox, IBox endingBox) {
        this.start = startingBox;
        this.end = endingBox;
    }

    /*
    public void makeMove (RealBox[][] board) {
        board[end.getRow()][end.getCol()].addPiece(
                board[start.getRow()][start.getCol()].getCurrentPiece());
        board[start.getRow()][start.getCol()].removePiece();
    }
    */


    public boolean checkEndingRowCol (int row, int col){
        return end.getRow()==row&&end.getCol()==col ? true : false;
    }

}
