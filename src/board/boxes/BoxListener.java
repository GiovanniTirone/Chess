package board.boxes;
import board.MyChessBoard;
import lombok.Data;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Data
public class BoxListener implements MouseListener {

    JFrame jFrame;

    private RealBox realBox;

    private RealBox[][] board;


    public BoxListener(RealBox realBox, JFrame jFrame, RealBox[][]board){
        this.realBox = realBox;
        this.jFrame = jFrame;
        this.board = board;
    }

    private void pressFirstBox() {
        System.out.println("-------------------------------------");
        System.out.println("Press the first box");
        // box.removePieceGUI();
        realBox.setPressed(true);
        realBox.setFirstPressed(true);  // FIRST false -> true
        realBox.getPropertyChangeSupport().firePropertyChange("firstPressed",false,true); //FIRE PROPERTY
        MyChessBoard.currentPressedRealBox = realBox;
        MyChessBoard.currentPossibleMoves = realBox.getCurrentPiece().getPossibleMoves(realBox,board);
        System.out.println("Current piece box: " + MyChessBoard.currentPressedRealBox);
        System.out.println("Box moves: " + MyChessBoard.currentPossibleMoves);
        System.out.println("-------------------------------------");
    }

    private void pressAgainFirstBox () {
        System.out.println("-------------------------------------");
        System.out.println("Press again the same box");
        realBox.setPressed(false);
        realBox.setFirstPressed(false); // FIRST true -> false
        MyChessBoard.currentPressedRealBox = null;
        System.out.println("-------------------------------------");
    }

    private void pressSecondFullBoxWithSamePieceColor () {
        MyChessBoard.currentPressedRealBox.setPressed(false);
        MyChessBoard.currentPressedRealBox.setFirstPressed(false);  // FIRST true -> false
        MyChessBoard.currentPressedRealBox = realBox;
        realBox.setPressed(true);
        realBox.setFirstPressed(true);
    }

    private void pressSecondFullBoxWithDifferentPieceColor () {
        realBox.setSecondPressed(true); // SECOND false -> true
        realBox.getPropertyChangeSupport().firePropertyChange("secondPressed",false,true); //FIRE PROPERTY
        //implementare la logica di gioco
        System.out.println("-------------------------------------");
        System.out.println("Press a box after another");
        System.out.println("currentPressedBox: " + MyChessBoard.currentPressedRealBox);
        System.out.println("New box: " + realBox);
        MyChessBoard.currentPressedRealBox.setPressed(false);
        MyChessBoard.currentPressedRealBox.setFirstPressed(false); // FIRST true -> false
        System.out.println("Fill the new box: " + realBox);
        MyChessBoard.currentPressedRealBox = null;
        realBox.setSecondPressed(false); //SECOND true -> false
        jFrame.setVisible(true);
        System.out.println("-------------------------------------");
    }

    private void pressSecondEmptyBox () {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("You press the box " + realBox.getRow() + " , " + realBox.getCol());
        //attenzione: devo essere sicuro che le condizioni justPressedAnotherBox e box.isPressed NON si verifichino contemporaneamente
        if (MyChessBoard.currentPressedRealBox == null) {
            if (realBox.getCurrentPiece() != null) {
                pressFirstBox();
            }
        } else {
            if (realBox.isPressed()) {
                pressAgainFirstBox();
            } else {
                if (realBox.getCurrentPiece() != null) {
                    if (realBox.getCurrentPiece().getColor() == MyChessBoard.currentPressedRealBox.getCurrentPiece().getColor()) {
                        pressSecondFullBoxWithSamePieceColor();
                    } else if (MyChessBoard.currentPossibleMoves.containsEndingMove(realBox.getRow(), realBox.getCol())) {
                        pressSecondFullBoxWithDifferentPieceColor();
                    } else{
                        // aggiungere caso
                    }
                }else{
                    pressSecondEmptyBox();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
