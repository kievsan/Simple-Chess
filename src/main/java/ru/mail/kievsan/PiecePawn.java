package ru.mail.kievsan;

public class PiecePawn extends PiecesImpl {
    protected PiecePawn(Color color) {
        super(color);
    }

    @Override
    public PiecesID getID() {
        return PiecesID.PAWN;
    }

//    @Override
//    public boolean canBeMove(Position start, Position finish, ChessBoard board) {
//        return false;
//    }
//
//    @Override
//    public boolean canBeAttack() {
//        return false;
//    }
}
