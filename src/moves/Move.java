package moves;
import board.MyChessBoard;
import board.PlayerPieces;
import board.boxes.IBox;
import board.boxes.RealBox;
import lombok.Data;
import pieces.Piece;
import pieces.PieceName;
import player.HumanPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Data
public class Move {

    private IBox start;
    private IBox end;

    public Move () {}

    public Move(IBox startingBox, IBox endingBox) {
        this.start = startingBox;
        this.end = endingBox;
    }


    public boolean makeMove (IBox[][] board) {
        Piece startPiece = board[start.getRow()][start.getCol()].getCurrentPiece();
        Piece endPiece =  board[end.getRow()][end.getCol()].getCurrentPiece();
        if(endPiece != null){  //se voglio spostare la logica delle possible moves dai pieces alle moves devo metterla qui
            if(endPiece.getColor() != startPiece.getColor() && endPiece.getPieceName() == PieceName.KING)
                return true;
        }
        board[end.getRow()][end.getCol()].addPiece(startPiece);
        board[start.getRow()][start.getCol()].removePiece();
        return false;
    }

    public boolean makeRealMove (RealBox [][] board){
        RealBox startReal = (RealBox) start;
        RealBox endReal = (RealBox) end;
        Piece startPiece = startReal.getCurrentPiece();
        Piece endPiece =  endReal.getCurrentPiece();
        if(endPiece != null){  //se voglio spostare la logica delle possible moves dai pieces alle moves devo metterla qui
            if(endPiece.getColor() != startPiece.getColor()){
                if(endPiece.getPieceName() == PieceName.KING) return true;
                endPiece.setLive(false);
                endReal.removePiece();
                endReal.removePieceGUI();
            }
        }
        endReal.addPiece(startPiece);
        endReal.addPieceGUI();
        startReal.removePiece();
        startReal.removePieceGUI();
        return false;
    }

    public void undo (IBox[][] board) {
        board[start.getRow()][start.getCol()].addPiece(
                board[end.getRow()][end.getCol()].getCurrentPiece());
        board[end.getRow()][end.getCol()].removePiece();
    }


    public boolean checkEndingRowCol (int row, int col){
        return end.getRow()==row&&end.getCol()==col ? true : false;
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


        HumanPlayer p1 = new HumanPlayer(Color.WHITE,cb.getBoard());


        Arrays.stream(cb.getBoard()).forEach(row -> Arrays.stream(row)
                .forEach(realBox -> realBox
                        .addIsPressedListener(p1.getIsPressedListener())));


        CompletableFuture p1_turn = CompletableFuture.runAsync(p1.getWaitFillTheMove())
                                                    .thenRun(p1.getMakeRealMove());

/*
        p1_turn.thenRunAsync(p1.AddChangePressedListenersUntilFillTheMove)
                        .thenApply(p1.makeRealMove);
*/
        f.setVisible(true);
    }

}
