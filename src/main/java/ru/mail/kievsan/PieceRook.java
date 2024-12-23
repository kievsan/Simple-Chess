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
    public boolean canBeMove(Position start, Position finish, ChessBoard board){
        return canBeLineMove(start, finish, ChessBoard.ROWS.length());
    }
}
