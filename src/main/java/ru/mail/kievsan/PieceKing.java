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
        return canBeDiagonalMove(start, finish, 1) && canBeLineMove(start, finish, 1);
    }
}
