package player;
import board.boxes.FakeBox;
import board.boxes.IBox;
import board.boxes.RealBox;
import moves.Move;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class AiPlayer extends Player {

    public AiPlayer (boolean human, Color color) {
        super(human, color);
    }

    public boolean makeMove (RealBox[][] board) {
        List<Move> possibleMoves = getPossibleMoves(board);
        FakeBox[][] fakeBoard = createFakeBoardFromBoard(board);
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = null;
        for(Move move : possibleMoves){
            move.makeMove(fakeBoard);
            int tempValue = miniMax(fakeBoard,0,true);
            move.undo(fakeBoard);
            if(tempValue>bestValue) {
                bestValue = tempValue;
                bestMove = move;
            }
        }
        return bestMove.makeMove(board);
    }




    private int miniMax (FakeBox[][] board, int depth, boolean maximizingPlayer) {
        if(depth == 3 ) return evaluateBoard(board); //aggiungere condizione di VITTORIA
        if(maximizingPlayer){
            int value = Integer.MIN_VALUE;
            for(Move move : getPossibleMoves(board)){
                move.makeMove(board);
                value = Math.max(value,miniMax(board,depth+1,false));
                move.undo(board);
            }
            return value;
        }else{
            int value = Integer.MAX_VALUE;
            for(Move move : getPossibleMoves(board)){
                move.makeMove(board);
                value = Math.min(value,miniMax(board,depth+1,true));
                move.undo(board);
            }
            return value;
        }
    }

    private FakeBox createFakeBoxFromBox (RealBox realBox) {
        return new FakeBox(realBox.getRow(), realBox.getCol());
    }

    private FakeBox[][] createFakeBoardFromBoard (RealBox[][] board){
        FakeBox[][] fakeBoxes = new FakeBox[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                fakeBoxes[i][j] =  createFakeBoxFromBox(board[i][j]);
            }
        }
        return fakeBoxes;
    }

    private int evaluateBoard(FakeBox[][]board) {
        int result = 0;
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getCurrentPiece()!=null)
                    result += board[i][j].getCurrentPiece().getStrength();
            }
        }
        return result;
    }

    private List<Move> getPossibleMoves (IBox[][] board){
        List<Move> possibleMoves = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getCurrentPiece()!=null) {
                    board[i][j].getCurrentPiece()
                                .getPossibleMoves( board[i][j], board)
                                .stream()
                                .forEach(move-> possibleMoves.add(move));
                }
            }
        }
        return possibleMoves;
    }


}
