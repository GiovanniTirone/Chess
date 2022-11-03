package board;
import Sources.PieceGUI;
import lombok.Data;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

@Data
public class MyChessBoard {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private MyBox[][] board;
    private JPanel boardPanel;

    private static final String COLS = "ABCDEFGH";

    public MyChessBoard() {
        this.board = new MyBox[8][8];
        this.boardPanel = new JPanel(new GridLayout(0, 9));
        boardPanel.setBorder(new LineBorder(Color.BLACK));
        gui.add(boardPanel);


        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                MyBox b = new MyBox();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))  b.setBackground(Color.WHITE);
                else b.setBackground(Color.BLACK);
                board[j][i] = b;
            }
        }


        boardPanel.add(new JLabel(""));
        for (int i = 0; i < 8; i++) {
            boardPanel.add(
                    new JLabel(COLS.substring(i, i + 1),
                            SwingConstants.CENTER));
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (j) {
                    case 0:
                        boardPanel.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        boardPanel.add(board[j][i]);
                }
            }
        }

        PieceGUI pieceGUI = new PieceGUI();
        pieceGUI.addColoredUnicodeCharToContainer("\u2655", board[0][0], Color.BLACK, Color.DARK_GRAY, false);
        //board[0][0].addMouseListener(new MyListener(board[0][0]));
    }

    public void addBoxListeners() {
        Arrays.stream(board).forEach(row -> Arrays.stream(row).forEach(MyBox::activateClickListener));
    }

    public void removeBoxListeners() {
        Arrays.stream(board).forEach(row -> Arrays.stream(row).forEach(MyBox::removeClickListener));
    }




    public static void main(String[] args) {
        MyChessBoard cb = new MyChessBoard();
        JFrame f = new JFrame("ChessChamp");
        f.add(cb.gui);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }

}
