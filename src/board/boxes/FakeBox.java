package board.boxes;
import lombok.Data;
import pieces.Piece;

@Data
public class FakeBox implements IBox {

    private Piece currentPiece;
    private int row; // row coordinate
    private int col; // col coordinate


    public FakeBox(int x, int y){
        this.row = x;
        this.col = y;
    }


    public void addPiece(Piece piece) {
        this.currentPiece = piece;
    }

    public void removePiece(){
            this.currentPiece = null;
        }


    @Override
    public String toString() {
        return "Box (" + row  + ","  +col +")"  +
                "\n currentPiece: " + currentPiece ;
                //print possible moves , it needs a board.
                //"\n pressListener: " + pressListener +

    }


}
