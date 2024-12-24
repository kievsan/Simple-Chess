package ru.mail.kievsan;

public class PieceKing extends PiecesImpl {

    public PieceKing(Color color) {
        super(color);
    }

    @Override
    public PiecesID getID() {
        return PiecesID.KING;
    }

    @Override
    public boolean canBeMove(Position start, Position finish, ChessBoard board){
        return canBeDiagonalMove(start, finish, 1, false) &&
                canBeLineMove(start, finish, 1, false);
    }

    boolean isUnderAttack(ChessBoard board, int line, int column) {
        return false;
    }
}
