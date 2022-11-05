package pieces;
import lombok.Data;
import moves.BoxMoves;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
public class Piece {
    private Color color;
    private PieceName pieceName;
    private JLabel jLabel;
    private boolean live;

   private BoxMoves[][] possibleMoves;
    public Piece (Color color,PieceName pieceName) {
        this.color = color;
        this.pieceName = pieceName;
        this.live = true;
       // this.jLabel =  PieceGUI.getJLabelFromUnicodeChar(getUnicodeChar(),Color.BLACK, Color.DARK_GRAY,false);
    }

    public String getUnicodeChar () {
        return this.pieceName.getUnicodeChar();
    }



}
