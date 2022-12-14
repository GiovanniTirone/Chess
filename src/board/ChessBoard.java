package board;
import board.boxes.RealBox;
import lombok.Data;
import moves.BoxMoves;
import pieces.Piece;
import pieces.PieceName;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;


@Data
public class ChessBoard {

    public static RealBox currentPressedRealBox = null;
    public static BoxMoves currentPossibleMoves = null;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private  RealBox[][] board;
    private JPanel boardPanel;

    private JFrame jFrame;

    private static final String COLS = "ABCDEFGH";

    public ChessBoard(JFrame jFrame)  {
        this.jFrame = jFrame;
        this.board = new RealBox[8][8];
        this.boardPanel = new JPanel(new GridLayout(0, 9));
        boardPanel.setBorder(new LineBorder(Color.BLACK));
        gui.add(boardPanel);


        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                RealBox b = new RealBox(i, j,jFrame);
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))  {
                    b.setBackground(Color.WHITE);
                    b.setBlack(false);
                }
                else{
                    b.setBackground(Color.BLACK);
                    b.setBlack(true);
                }
                board[i][j] = b;
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
                        boardPanel.add(board[i][j]);
                }
            }
        }
    }



    public void addBoxListeners() {
        Arrays.stream(board).forEach(row -> Arrays.stream(row).forEach(RealBox::activateClickListener));
    }

    public void removeBoxListeners() {
        Arrays.stream(board).forEach(row -> Arrays.stream(row).forEach(RealBox::removeClickListener));
    }

    public void clearPressedDatas () {
        Arrays.stream(board).forEach(row -> Arrays.stream(row).forEach(box -> {
            box.setFirstPressed(false);
            box.setSecondPressed(false);
            currentPressedRealBox = null;
            currentPossibleMoves = null;
        }));
    }


    public void addPieceToBoard (int i, int j,Piece piece){
        board[i][j].addPiece(piece);
        board[i][j].addPieceGUI();
    }

    public void addPiecesInStarterPosition (PlayerPieces whitePieces, PlayerPieces blackPieces) throws Exception {
        if(whitePieces.getColor() != Color.WHITE) throw new Exception("The color of white pieces must be WHITE!");
        if(blackPieces.getColor() != Color.BLACK) throw new Exception("The color of black pieces must be BLACK!");

        for(int j=0; j<8; j++){
            addPieceToBoard(1,j,blackPieces.getPiece(PieceName.PAWN,j));
        }
        addPieceToBoard(0,0,blackPieces.getPiece(PieceName.ROOK,0));
        addPieceToBoard(0,7,blackPieces.getPiece(PieceName.ROOK,1));
        addPieceToBoard(0,1,blackPieces.getPiece(PieceName.KNIGHT,0));
        addPieceToBoard(0,6,blackPieces.getPiece(PieceName.KNIGHT,1));
        addPieceToBoard(0,2,blackPieces.getPiece(PieceName.BISHOP,0));
        addPieceToBoard(0,5,blackPieces.getPiece(PieceName.BISHOP,1));
        addPieceToBoard(0,3,blackPieces.getPiece(PieceName.QUEEN,0));
        addPieceToBoard(0,4,blackPieces.getPiece(PieceName.KING,0));

        for(int j=0; j<8; j++){
            addPieceToBoard(6,j,whitePieces.getPiece(PieceName.PAWN,j));
        }
        addPieceToBoard(7,0,whitePieces.getPiece(PieceName.ROOK,0));
        addPieceToBoard(7,7,whitePieces.getPiece(PieceName.ROOK,1));
        addPieceToBoard(7,1,whitePieces.getPiece(PieceName.KNIGHT,0));
        addPieceToBoard(7,6,whitePieces.getPiece(PieceName.KNIGHT,1));
        addPieceToBoard(7,2,whitePieces.getPiece(PieceName.BISHOP,0));
        addPieceToBoard(7,5,whitePieces.getPiece(PieceName.BISHOP,1));
        addPieceToBoard(7,3,whitePieces.getPiece(PieceName.QUEEN,0));
        addPieceToBoard(7,4,whitePieces.getPiece(PieceName.KING,0));

    }

    public void printBoard () {
        Arrays.stream(board).forEach(row -> Arrays.stream(row).forEach(box -> System.out.println(box)));
    }



}
