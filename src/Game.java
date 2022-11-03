import board.Box;

public class Game {

    private Box board [][];

    private static Game game = new Game ();

    private Game () {
        this.board = new Box[8][8];
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++){
                board[i][j]= new Box(i,j);
            }
        }
    }

    public Game getIstance () {return game;}

    public void humanTurn () {

    }


}
