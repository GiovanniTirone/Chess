package moves;
import board.boxes.IBox;
import board.boxes.RealBox;
import pieces.Piece;
import pieces.PieceName;
import javax.swing.*;

public class RealMove extends Move<RealBox> {



    public RealMove() {
        super();
    }

    public RealMove(RealBox startingBox,RealBox endingBox,JFrame jFrame) {
        super(startingBox,endingBox);
    }


    @Override
    protected void removePieceFromStart() {
        System.out.println("----------RemovePieceFromStart------------");
        System.out.println("start box: " + getStart());
        getStart().removePieceGUI();
        super.removePieceFromStart();
        System.out.println("start box: " + getStart());
    }

    @Override
    protected void removePieceFromEnd() {
        System.out.println("----------RemovePieceFromEnd------------");
        getEnd().removePieceGUI();
        super.removePieceFromEnd();
    }

    @Override
    protected void addPieceToEnd(Piece pieceToAdd) {
        System.out.println("----------AddPieceToEnd------------");
        super.addPieceToEnd(pieceToAdd);
        getEnd().addPieceGUI();
    }

    @Override
    protected void addPieceToStart(Piece pieceToAdd){
        System.out.println("----------AddPieceToStart------------");
        super.addPieceToStart(pieceToAdd);
        getStart().addPieceGUI();
    }
}
