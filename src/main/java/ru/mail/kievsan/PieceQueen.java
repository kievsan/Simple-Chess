package ru.mail.kievsan;

public class PieceQueen extends PiecesImpl {

    public PieceQueen(Color color) {
        super(color);
    }

    @Override
    public PiecesID getID() {
        return PiecesID.QUEEN;
    }

    @Override
    public boolean canBeMove(Position start, Position finish, ChessBoard board) {
        return canBeDiagonalMove(start, finish, ChessBoard.ROWS.length(), false) &&
                canBeLineMove(start, finish, ChessBoard.ROWS.length(), false);
    }
}
