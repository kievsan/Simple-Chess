package ru.mail.kievsan;

public class PiecePawn extends PiecesImpl {
    protected PiecePawn(Color color) {
        super(color);
    }

    @Override
    public PiecesID getID() {
        return PiecesID.PAWN;
    }

    @Override
    public boolean canBeMove(Position start, Position finish, ChessBoard board) {
        int maxMoveLen = hasMoved() ? 1 : 2;
        return canBeLineMove(start, finish, maxMoveLen, true) || canBeAttack(start, finish, board);
    }

    private boolean canBeAttack(Position start, Position finish, ChessBoard board) {
        if (!canBeDiagonalMove(start, finish, 1, true)) return false;
        return false;
    }
}
