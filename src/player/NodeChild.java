package player;
import lombok.Data;

@Data
public class NodeChild extends Node{

    private FakeMove fakeMove;

    public NodeChild (FakeBox[][] fakeBoard, FakeMove fakeMove) {
        super(fakeBoard);
        this.fakeMove = fakeMove;
    }

}
