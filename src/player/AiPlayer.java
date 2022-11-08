package player;

import board.Box;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AiPlayer extends Player{

    public AiPlayer (boolean human, Color color) {
        super(human, color);
    }

    public void makeMove (Box [][] board) {
        Node startingNode = new Node(createFakeBoardFromBoard(board));
        int bestValue = Integer.MIN_VALUE;
        FakeMove bestFakeMove = null;
        for(Node child : startingNode.getChildren()){
            int tempValue = miniMax(child,0,true);
            if(tempValue>bestValue) {
                bestValue = tempValue;
                bestFakeMove =
            }
        }
    }




    private int miniMax (Node node, int depth, boolean maximizingPlayer) {
        if(depth == 0 || node.getChildren().size()==0) return node.evaluate();
        if(maximizingPlayer){
            int value = Integer.MIN_VALUE;
            for(NodeChild child : node.getChildren()){
                value = Math.max(value,miniMax(child,depth-1,false));
            }
            return value;
        }else{
            int value = Integer.MAX_VALUE;
            for(NodeChild child : node.getChildren()){
                value = Math.min(value,miniMax(child,depth-1,true));
            }
            return value;
        }
    }

    private FakeBox createFakeBoxFromBox (Box box) {
        return new FakeBox(box.getRow(), box.getCol());
    }

    private FakeBox[][] createFakeBoardFromBoard (Box[][] board){
        FakeBox [][] fakeBoxes = new FakeBox[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                fakeBoxes[i][j] =  createFakeBoxFromBox(board[i][j]);
            }
        }
        return fakeBoxes;
    }

    private Stream<FakeMove> createPossibleMoves (FakeBox[][] board){
        List<FakeMove> possibleFakeMoves = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getCurrentPiece()!=null) {
                    board[i][j].getCurrentPiece()
                                .getPossibleMoves( board[i][j], board)
                                .stream()
                                .forEach(move-> possibleFakeMoves.add(move));
                }
            }
        }
    }


}
