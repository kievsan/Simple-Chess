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
    public boolean canBeMove() {
        return false;
    }

    @Override
    public boolean canBeAttack() {
        return false;
    }
}
