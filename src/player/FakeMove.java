package player;
import lombok.Data;
import moves.Move;

@Data
public class FakeMove extends Move {

    private FakeBox start;
    private FakeBox end;

    public FakeMove(FakeBox startingBox, FakeBox endingBox) {
        this.start = startingBox;
        this.end = endingBox;
    }

    public void makeMove (FakeBox [][] fakeBoard) {
        fakeBoard[end.getRow()][end.getCol()].addPiece(
                fakeBoard[start.getRow()][start.getCol()].getCurrentPiece());
        fakeBoard[start.getRow()][start.getCol()].removePiece();
    }


}
