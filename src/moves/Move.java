package moves;
import board.boxes.IBox;
import lombok.Data;
import pieces.Piece;
import pieces.PieceName;


@Data
public abstract class Move<SomeBox extends IBox>{

    private SomeBox start;
    private SomeBox end;

    private Piece eatenPiece;

    public Move () {}

    public Move(SomeBox startingBox, SomeBox endingBox) {
        this.start = startingBox;
        this.end = endingBox;
        this.eatenPiece = null;
    }


    public  boolean makeMove (){
        Piece startPiece = start.getCurrentPiece();
        Piece endPiece = end.getCurrentPiece();
        if(endPiece != null){
            if(endPiece.getColor() != startPiece.getColor()){
                if(endPiece.getPieceName() == PieceName.KING) return true;
                eatenPiece = endPiece;
                removePieceFromEnd();
            }
        }
        removePieceFromStart();
        addPieceToEnd(startPiece);
        return false;
    };

    protected void removePieceFromStart () {
        start.removePiece();
    };
    protected void removePieceFromEnd () {
        end.removePiece();
    };
    protected void addPieceToEnd (Piece pieceToAdd){
        end.addPiece(pieceToAdd);
    };

    protected void addPieceToStart (Piece pieceToAdd){
        start.addPiece(pieceToAdd);
    }


    public void undo () {
        addPieceToStart(end.getCurrentPiece());
        removePieceFromEnd();
        addPieceToEnd(eatenPiece);
    }


    public boolean checkEndingRowCol (int row, int col){
        return end.getRow()==row && end.getCol()==col ? true : false;
    }



}
