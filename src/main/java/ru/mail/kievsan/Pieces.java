package ru.mail.kievsan;

public interface Pieces {
    Color getColor();
    PiecesID getID();
    default boolean canBeMove(Position start, Position finish, ChessBoard board) {return false;}
    default boolean canBeLineMove(Position start, Position finish, int pieceMaxMove) {
        int hStep = Math.abs(start.getHorizontalVector(finish));
        int vStep = Math.abs(start.getVerticalVector(finish));
        return (vStep == 0 && pieceMaxMove >= hStep) || hStep == 0 && pieceMaxMove >= vStep;
    }
    default boolean canBeDiagonalMove(Position start, Position finish, int pieceMaxMove) {
        int hStep = Math.abs(start.getHorizontalVector(finish));
        int vStep = Math.abs(start.getVerticalVector(finish));
        return vStep != 0 && pieceMaxMove >= vStep && hStep == vStep;
    }
    default boolean canBeAttack() {return true;}
}
