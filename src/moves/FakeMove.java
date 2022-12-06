package moves;

import board.boxes.IBox;


public class FakeMove extends Move<IBox> {

    public FakeMove (IBox startingBox, IBox endingBox) {
        super(startingBox,endingBox);
    }

}
