package pieces;
import board.boxes.IBox;
import lombok.Data;
import moves.BoxMoves;
import moves.Move;
import javax.swing.JLabel;
import java.awt.*;


@Data
public abstract class Piece {
    private Color color;
    private PieceName pieceName;

    private int strength;
    private JLabel jLabel;

    public Piece (Color color,PieceName pieceName) {
        this.color = color;
        this.pieceName = pieceName;
    }

    public String getUnicodeChar () {
        return this.pieceName.getUnicodeChar();
    }


    public void setStrength (boolean maximizingPlayer){
        int k = maximizingPlayer ? 1 : -1;
        this.strength = k*this.pieceName.getStrength();
    }

    public abstract BoxMoves getPossibleMoves (IBox currentBox, IBox[][] board);


    public String printPossibleMoves (BoxMoves boxMoves) {
        String str = "";
        for(Move move : boxMoves){
            str+="[" + move.getEnd().getRow() +","+ move.getEnd().getCol() +"]";
        }
        return str;
    }

    protected  boolean numberIsInBoard (int num) {
        return num==-1 || num==8 ? false : true;
    }

    @Override
    public String toString () {
        String colorStr = color == Color.BLACK ? "BLACK" : "WHITE";
        return "Name: " + pieceName.name() + " - " + colorStr; // + " - " + jLabel ;
    }
    public void printAllDetails () {
        System.out.println("PieceName: "+pieceName);
        System.out.println("Color: " + color);
        System.out.println("JLabel: " + jLabel);
    }


    public abstract Piece clone () ;



}
