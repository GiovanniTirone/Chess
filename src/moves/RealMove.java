package moves;
import board.boxes.IBox;
import board.boxes.RealBox;
import pieces.Piece;
import pieces.PieceName;
import javax.swing.*;

public class RealMove extends Move<RealBox> {

    JFrame jFrame;

    public RealMove(JFrame jFrame) {
        super();
        this.jFrame = jFrame;
    }

    public RealMove(RealBox startingBox,RealBox endingBox,JFrame jFrame) {
        super(startingBox,endingBox);
        this.jFrame = jFrame;
    }


    @Override
    protected void removePieceFromStart() {
        super.removePieceFromStart();
        getStart().removePieceGUI();
    }

    @Override
    protected void removePieceFromEnd() {
        super.removePieceFromEnd();
        getEnd().removePieceGUI();
    }

    @Override
    protected void addPieceToEnd(Piece pieceToAdd) {
        super.addPieceToEnd(pieceToAdd);
        getEnd().addPieceGUI();
        jFrame.setVisible(true);
    }

    @Override
    protected void addPieceToStart(Piece pieceToAdd){
        super.addPieceToStart(pieceToAdd);
        getStart().addPieceGUI();
    }
}
