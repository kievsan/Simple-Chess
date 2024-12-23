package ru.mail.kievsan;

public class PieceHorse extends PiecesImpl{

    protected PieceHorse(Color color) {
        super(color);
    }

    @Override
    public PiecesID getID() {
        return PiecesID.HORSE;
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