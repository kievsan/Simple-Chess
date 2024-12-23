package ru.mail.kievsan;

public class PieceRook extends PiecesImpl {
    protected PieceRook(Color color) {
        super(color);
    }

    @Override
    public PiecesID getID() {
        return PiecesID.ROOK;
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
