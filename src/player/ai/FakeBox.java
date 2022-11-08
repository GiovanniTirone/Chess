package player.ai;
import board.Box;
import lombok.Data;
import pieces.Piece;

@Data
public class FakeBox implements Box {

    private Piece currentPiece;
    private int row; // row coordinate
    private int col; // col coordinate


    public FakeBox(int x,int y){
        this.row = x;
        this.col = y;
    }

    public void addPiece(Piece piece) {
        this.currentPiece = piece;
    }

    public void removePiece(){
            this.currentPiece = null;
        }

}
