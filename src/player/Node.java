package player;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Node {

    private FakeMove fakeMove;
    private List <Node> children;

    public Node () {
        this.children = new ArrayList<>();
    }

    public Node(FakeMove fakeMove){
        this.fakeMove = fakeMove;
        this.children = new ArrayList<>();
    }

    public void addChild (FakeMove fakeMove) {
        children.add(new Node(fakeMove));
    }

    public int evaluate () {
        int result = 0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                result += fakeBoard[i][j].getCurrentPiece().getStrength();
            }
        }
        return result;
    }

}
