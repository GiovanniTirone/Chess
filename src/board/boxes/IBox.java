package board.boxes;


import pieces.Piece;

public interface IBox {
        int getRow ();
        int getCol ();
        Piece getCurrentPiece();
        void setRow(int row);
        void setCol(int col);
        void setCurrentPiece(Piece piece);

        void addPiece(Piece piece);

        void removePiece();
}
