package player;
import lombok.Data;

@Data
public class NodeChild extends Node{

    private Move move;

    public NodeChild (FakeBox[][] fakeBoard,Move move) {
        super(fakeBoard);
        this.move = move;
    }

}
