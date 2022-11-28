package moves;

import board.boxes.FakeBox;


public class FakeMove extends Move<FakeBox> {

    public FakeMove (FakeBox startingBox, FakeBox endingBox) {
        super(startingBox,endingBox);
    }

}
