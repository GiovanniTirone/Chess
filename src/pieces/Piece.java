package pieces;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public abstract class Piece {

    private Color color;



    public Piece (Color color) {
        this.color = color;
    }




}
