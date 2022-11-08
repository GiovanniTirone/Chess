package moves;
import board.boxes.IBox;
import board.boxes.RealBox;
import lombok.Data;
import pieces.Piece;
import pieces.PieceName;

@Data
public  class Move {

    private IBox start;
    private IBox end;

    public Move(IBox startingBox, IBox endingBox) {
        this.start = startingBox;
        this.end = endingBox;
    }


    public boolean makeMove (IBox[][] board) {
        Piece startPiece = board[start.getRow()][start.getCol()].getCurrentPiece();
        Piece endPiece =  board[end.getRow()][end.getCol()].getCurrentPiece();
        if(endPiece != null){  //se voglio spostare la logica delle possible moves dai pieces alle moves devo metterla qui
            if(endPiece.getColor() != startPiece.getColor() && endPiece.getPieceName() == PieceName.KING)
                return true;
        }
        board[end.getRow()][end.getCol()].addPiece(startPiece);
        board[start.getRow()][start.getCol()].removePiece();
        return false;
    }

    public void undo (IBox[][] board) {
        board[start.getRow()][start.getCol()].addPiece(
                board[end.getRow()][end.getCol()].getCurrentPiece());
        board[end.getRow()][end.getCol()].removePiece();
    }


    public boolean checkEndingRowCol (int row, int col){
        return end.getRow()==row&&end.getCol()==col ? true : false;
    }

}
