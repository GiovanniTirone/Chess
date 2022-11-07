package player;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Node {

    private FakeBox [][] fakeBoard;

    private List <NodeChild> children;

    public Node () {
        this.fakeBoard = new FakeBox[8][8];
        this.children = new ArrayList<>();
    }

    public Node(FakeBox[][] fakeBoard){
        this.fakeBoard = fakeBoard;
        this.children = new ArrayList<>();
    }

    public void addChild (Move move) {
        children.add(new NodeChild(move.makeMove(fakeBoard)));
    }

    public int evaluate ( ) {
        int result = 0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                result += fakeBoard[i][j].getCurrentPiece().getStrength();
            }
        }
        return result;
    }

}
