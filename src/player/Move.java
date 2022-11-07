package player;
import lombok.Data;

@Data
public class Move {

    private FakeBox start;
    private FakeBox end;

    public Move (FakeBox startingBox, FakeBox endingBox) {
        this.start = startingBox;
        this.end = endingBox;
    }

    public void makeMove (FakeBox [][] fakeBoard) {
        fakeBoard[end.getRow()][end.getCol()].addPiece(
                fakeBoard[start.getRow()][start.getCol()].getCurrentPiece());
        fakeBoard[start.getRow()][start.getCol()].removePiece();
    }


}
