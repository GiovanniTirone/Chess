package board.boxes;
import board.MyChessBoard;
import board.PlayerPieces;
import lombok.Data;
import player.HumanPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Data
public class BoxListener implements MouseListener {

    //JFrame jFrame;

    private RealBox realBox;

    private RealBox[][] board;


    public BoxListener(RealBox realBox, RealBox[][]board){
        this.realBox = realBox;
        //this.jFrame = jFrame;
        this.board = board;
    }

    private void pressFirstBox() {
        System.out.println("---------Press the first box-------------");
        realBox.setFirstPressed(true);  // FIRST false -> true
        realBox.getPropertyChangeSupport().firePropertyChange("firstPressed",false,true); //FIRE PROPERTY
        MyChessBoard.currentPressedRealBox = realBox;
        MyChessBoard.currentPossibleMoves = realBox.getCurrentPiece().getPossibleMoves(realBox,board);
        System.out.println("cb.cb: " + MyChessBoard.currentPressedRealBox);
        System.out.println("Box moves: " + MyChessBoard.currentPossibleMoves);
        System.out.println("-----------------------------------------");
    }

    private void pressAgainFirstBox () {
        System.out.println("---------------Press again the same box-----------------");
        realBox.setFirstPressed(false); // FIRST true -> false
        MyChessBoard.currentPressedRealBox = null;
        MyChessBoard.currentPossibleMoves = null;
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
        realBox.setSecondPressed(false); //SECOND true -> false
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
        //attenzione: devo essere sicuro che le condizioni justPressedAnotherBox e box.isPressed NON si verifichino contemporaneamente
        if (MyChessBoard.currentPressedRealBox == null) {
            if (realBox.getCurrentPiece() != null) pressFirstBox();
        }else {
            if (realBox.isFirstPressed()) pressAgainFirstBox();
            else if (MyChessBoard.currentPossibleMoves.containsEndingMove(realBox.getRow(), realBox.getCol())){
                if (realBox.getCurrentPiece() != null) {
                    if (realBox.getCurrentPiece().getColor() == MyChessBoard.currentPressedRealBox.getCurrentPiece().getColor())
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

    public static void main(String[] args) throws Exception {

        MyChessBoard cb = new MyChessBoard();
        JFrame f = new JFrame("ChessChamp");
        f.add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());

        PlayerPieces blackPieces = new PlayerPieces(Color.BLACK);
        PlayerPieces whitePieces = new PlayerPieces(Color.WHITE);

        cb.addPiecesInStarterPosition(whitePieces,blackPieces);



        //IMPORTANTE: settare jFrame e board nei box listeners
       // Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setJFrame(f)));
        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row).forEach(box -> box.getPressListener().setBoard(cb.getBoard())));

        cb.addBoxListeners();


        HumanPlayer p1 = new HumanPlayer(Color.WHITE,cb.getBoard(),f);


        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(p1.getIsPressedListener())));


        CompletableFuture p1_turn = new CompletableFuture<>();

/*
        p1_turn.thenRunAsync(p1.AddChangePressedListenersUntilFillTheMove)
                        .thenApply(p1.makeRealMove);
*/
        f.setVisible(true);
    }
}
