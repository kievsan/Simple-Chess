package ru.mail.kievsan;

public class PieceBishop extends PiecesImpl {

    public PieceBishop(Color color) {
        super(color);
    }

    @Override
    public PiecesID getID() {
        return PiecesID.BISHOP;
    }

    @Override
    public boolean canBeMove(Position start, Position finish, ChessBoard board) {
        return canBeDiagonalMove(start, finish, ChessBoard.ROWS.length());
    }
}
