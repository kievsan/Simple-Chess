package ru.mail.kievsan;

public class PieceBeshop extends PiecesImpl {

    public PieceBeshop(Color color) {
        super(color);
    }

    @Override
    public PiecesID getID() {
        return PiecesID.BISHOP;
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
