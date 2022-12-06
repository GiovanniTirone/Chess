package board.boxes;
import board.ChessBoard;
import lombok.Data;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Data
public class BoxListener implements MouseListener {

    private RealBox realBox;

    private RealBox[][] board;


    public BoxListener(RealBox realBox, RealBox[][]board){
        this.realBox = realBox;
        this.board = board;
    }

    private void pressFirstBox() {
        System.out.println("---------Press the first box-------------");
        realBox.setFirstPressed(true);  // FIRST false -> true
        realBox.getPropertyChangeSupport().firePropertyChange("firstPressed",false,true); //FIRE PROPERTY
        ChessBoard.currentPressedRealBox = realBox;
        ChessBoard.currentPossibleMoves = realBox.getCurrentPiece().getPossibleMoves(realBox,board);
        System.out.println("cb.cb: " + ChessBoard.currentPressedRealBox);
        System.out.println("Box moves: " + ChessBoard.currentPossibleMoves);
        System.out.println("-----------------------------------------");
    }

    private void pressAgainFirstBox () {
        System.out.println("---------------Press again the same box-----------------");
        realBox.setFirstPressed(false); // FIRST true -> false
        ChessBoard.currentPressedRealBox = null;
        ChessBoard.currentPossibleMoves = null;
        System.out.println("----------------------------------------");
    }

    private void pressSecondFullBoxWithSamePieceColor () {
        System.out.println("---------------Press second box with SAME color piece-----------------");
        pressFirstBox();
        System.out.println("---------------------------------------------------------");
    }

    private void pressSecondFullBoxWithDifferentPieceColor () {
        //implementare la logica di gioco
        System.out.println("----------------Press second box with DIFFERENT color piece----------------");
        realBox.setSecondPressed(true); //SECOND true -> false
        realBox.getPropertyChangeSupport().firePropertyChange("secondPressed",false,true); //FIRE PROPERTY
        //jFrame.setVisible(true); DA SPOSTARE IN MOVE
        System.out.println("-------------------------------------");
    }

    private void pressSecondEmptyBox () {
        System.out.println("----------------Press second EMPTY box ----------------");
        pressSecondFullBoxWithDifferentPieceColor();
        System.out.println("------------------------------------------------------");
    }


    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("You press the box " + realBox.getRow() + " , " + realBox.getCol());
        if (ChessBoard.currentPressedRealBox == null) {
            if (realBox.getCurrentPiece() != null) pressFirstBox();
        }else {
            if (realBox.isFirstPressed()) pressAgainFirstBox();
            else if (ChessBoard.currentPossibleMoves.containsEndingMove(realBox.getRow(), realBox.getCol())){
                if (realBox.getCurrentPiece() != null) {
                    if (realBox.getCurrentPiece().getColor() == ChessBoard.currentPressedRealBox.getCurrentPiece().getColor())
                        pressSecondFullBoxWithSamePieceColor();
                    else
                        pressSecondFullBoxWithDifferentPieceColor();
                }else
                    pressSecondEmptyBox();
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
