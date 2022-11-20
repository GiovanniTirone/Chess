package moves;

import board.boxes.FakeBox;
import pieces.Piece;

public class FakeMove extends Move<FakeBox> {

    public FakeMove (FakeBox startingBox, FakeBox endingBox) {
        super(startingBox,endingBox);
    }

}
