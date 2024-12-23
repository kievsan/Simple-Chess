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
    public boolean canBeMove() {
        return false;
    }

    @Override
    public boolean canBeAttack() {
        return false;
    }
}
