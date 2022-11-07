package board;
import lombok.Data;
import moves.BoxMoves;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Data
public class BoxListener implements MouseListener {

    JFrame jFrame;

    private Box box;

    private Box [][] board;


    public BoxListener(Box box, JFrame jFrame,Box[][]board){
        this.box = box;
        this.jFrame = jFrame;
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("You press the box " + box.getRow() + " , " + box.getCol());
        //attenzione: devo essere sicuro che le condizioni justPressedAnotherBox e box.isPressed NON si verifichino contemporaneamente
        if (MyChessBoard.currentPressedBox == null) {
            if (box.getCurrentPiece() != null) {
                System.out.println("-------------------------------------");
                System.out.println("Press the first box");
                // box.removePieceGUI();
                box.setPressed(true);
                MyChessBoard.currentPressedBox = box;
                MyChessBoard.currentPossibleMoves = box.getCurrentPiece().getPossibleMoves(box,board);
                System.out.println("Current piece box: " + MyChessBoard.currentPressedBox);
                System.out.println("Box moves: " + MyChessBoard.currentPossibleMoves);
                System.out.println("-------------------------------------");
            }
        } else {
            if (box.isPressed()) {
                System.out.println("-------------------------------------");
                System.out.println("Press again the same box");
                box.setPressed(false);
                MyChessBoard.currentPressedBox = null;
                System.out.println("-------------------------------------");
            } else {
                if (box.getCurrentPiece() != null) {
                    /* if (box.getCurrentPiece().getColor() == MyChessBoard.currentPressedBox.getCurrentPiece().getColor()) {
                        return;
                    }else{*/
                    if(MyChessBoard.currentPossibleMoves.containsMove(box.getRow(),box.getCol())) {
                        box.getCurrentPiece().setLive(false);
                        box.removePieceGUI();
                        box.removePiece();
                    }
                    }
                }
            if(MyChessBoard.currentPossibleMoves.containsMove(box.getRow(),box.getCol())){
                //implementare la logica di gioco
                System.out.println("-------------------------------------");
                System.out.println("Press a box after another");
                System.out.println("currentPressedBox: " + MyChessBoard.currentPressedBox);
                System.out.println("New box: " + box);
                MyChessBoard.currentPressedBox.setPressed(false);
                MyChessBoard.currentPressedBox.removePieceGUI();
                box.addPiece(MyChessBoard.currentPressedBox.getCurrentPiece());
                System.out.println("Fill the new box: " + box);
                MyChessBoard.currentPressedBox.removePiece();
                MyChessBoard.currentPressedBox = null;
                jFrame.setVisible(true);
                System.out.println("-------------------------------------");
            }
        }
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
