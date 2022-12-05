package moves;
import board.ChessBoard;
import board.PlayerPieces;
import board.boxes.IBox;
import lombok.Data;
import pieces.Piece;
import player.HumanPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Data
public abstract class Move<SomeBox extends IBox>{

    private SomeBox start;
    private SomeBox end;

    private Piece eatenPiece;

    public Move () {}

    public Move(SomeBox startingBox, SomeBox endingBox) {
        this.start = startingBox;
        this.end = endingBox;
        this.eatenPiece = null;
    }


    public  boolean makeMove (){
        Piece startPiece = start.getCurrentPiece();
        Piece endPiece = end.getCurrentPiece();
        if(endPiece != null){  //se voglio spostare la logica delle possible moves dai pieces alle moves devo metterla qui
            if(endPiece.getColor() != startPiece.getColor()){
               // if(endPiece.getPieceName() == PieceName.KING) return true;
                //endPiece.setLive(false);
                eatenPiece = endPiece;
                removePieceFromEnd();
            }
        }
        removePieceFromStart();
        addPieceToEnd(startPiece);
        return false;
    };

    protected void removePieceFromStart () {
        start.removePiece();
    };
    protected void removePieceFromEnd () {
        end.removePiece();
    };
    protected void addPieceToEnd (Piece pieceToAdd){
        end.addPiece(pieceToAdd);
    };

    protected void addPieceToStart (Piece pieceToAdd){
        start.addPiece(pieceToAdd);
    }


    public void undo () {
        addPieceToStart(end.getCurrentPiece());
        removePieceFromEnd();
        addPieceToEnd(eatenPiece);
    }


    public boolean checkEndingRowCol (int row, int col){
        return end.getRow()==row && end.getCol()==col ? true : false;
    }


    public static void main(String[] args) throws Exception {

        JFrame f = new JFrame("ChessChamp");
        ChessBoard cb = new ChessBoard(f);
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


       HumanPlayer p1 = new HumanPlayer(Color.WHITE,cb.getBoard());


        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(p1.getIsPressedListener())));


        CompletableFuture p1_turn = CompletableFuture.runAsync(p1.getWaitFillTheMove())
                                                    .thenRun(p1.getMakeRealMove());

       // cb.getBoard()[6][2].removePieceGUI();

        f.setVisible(true);
    }

}
