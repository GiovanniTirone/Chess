package moves;
import pieces.PieceName;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PossibleMoves {

    Map<PieceName,BoxMoves[][]> piecesMovesMap;

    BoxMoves[][] killingMoves;

    public PossibleMoves (){
        //white pawn (bottom of the board)
        piecesMovesMap = new HashMap<>();


        inizializeBoxMoves(PieceName.PAWN);
        inizializeBoxMoves(PieceName.KING);


        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(i-1>=0) piecesMovesMap.get(PieceName.PAWN)[i][j].add(new Move(i-1,j));
            }
        }
        /*
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++){
                if(i-1<=0) {
                    if (j + 1 <= 7) killingMoves[i][j].add(new Move(i - 1, j + 1));
                    if (j - 1 >= 0) killingMoves[i][j].add(new Move(i - 1, j - 1));
                }
            }
        }
        */

        /*
        piecesMovesMap.put(PieceName.KING,new BoxMoves[8][8]);
        for(int i=0; i<8; i++){
            for(int j=0; i<8; i++) {
                if(i+1<8){
                    piecesMovesMap.get(PieceName.KING)[i][j].add(new Move(i+1,j));
                    if(j-1>=0) piecesMovesMap.get(PieceName.KING)[i][j].add(new Move(i+1,-1));
                    if(j+1<8) piecesMovesMap.get(PieceName.KING)[i][j].add(new Move(i+1,j+1));
                }
                if()
            }
        }
        */

        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                for(int k=-1; k<2; k+=2){
                    if(mod9(i+k)!=8) {
                        piecesMovesMap.get(PieceName.KING)[i][j].add(new Move(i+k,j));
                        if(mod9(j-k)!=8) piecesMovesMap.get(PieceName.KING)[i][j].add(new Move(i+k,j-k));
                        if(mod9(j+k)!=8) piecesMovesMap.get(PieceName.KING)[i][j].add(new Move(i+k,j+k));
                    }
                    if(mod9(j+k)!=8){
                        piecesMovesMap.get(PieceName.KING)[i][j].add(new Move(i,j+k));
                    }
                }
            }
        }



    }



    public int mod9 (int number) {
        if(number>=0) return number%9;
        else return (number+9*(f(number)))%9;
    }

    public int f (int number) {
        if(number < 9 ) return 1;
        else return number/9;
    }

    private void inizializeBoxMoves(PieceName pieceName) {
        piecesMovesMap.put(pieceName,new BoxMoves[8][8]);
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                piecesMovesMap.get(pieceName)[i][j]=new BoxMoves();
            }
        }
    }


    public String printMoves (PieceName pieceName){
        String str = "Moves of piece " + pieceName.name();
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                str += "\n Moves of box " + "(" +i + "," + j + ")";
                str+= "\n " + piecesMovesMap.get(pieceName)[i][j];
            }
        }
        return str;
    }


    public static void main(String[] args) {


        PossibleMoves possibleMoves = new PossibleMoves();
        System.out.println(possibleMoves.printMoves(PieceName.KING));
    }

}
