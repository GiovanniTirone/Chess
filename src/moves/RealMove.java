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
        System.out.println("start box before removing: \n" + getStart());
        getStart().removePieceGUI();
        super.removePieceFromStart();
        System.out.println("start box after removing: \n" + getStart());
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
        System.out.println("End box before adding: \n" + getEnd());
        super.addPieceToEnd(pieceToAdd);
        getEnd().addPieceGUI();
        System.out.println("End box after adding: \n" +getEnd());
    }

    @Override
    protected void addPieceToStart(Piece pieceToAdd){
        System.out.println("----------AddPieceToStart------------");
        super.addPieceToStart(pieceToAdd);
        getStart().addPieceGUI();
    }
}
