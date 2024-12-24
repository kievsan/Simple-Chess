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
    public boolean canBeMove(Position start, Position finish, ChessBoard board) {
        var v = start.getVector(finish);
        return (Math.abs(v[0]) == 2 && Math.abs(v[1]) == 1) || (Math.abs(v[0]) == 1 && Math.abs(v[1]) == 2);
    }
}
