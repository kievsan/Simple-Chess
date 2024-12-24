package ru.mail.kievsan;

public interface Pieces {
    Color getColor();

    PiecesID getID();

    void incMoved();

    default boolean hasMoved() {return false;}

    default boolean canBeMove(Position start, Position finish, ChessBoard board) {return false;}

    default boolean canBeLineMove(Position start, Position finish, int pieceMaxMove, boolean onlyForwardDirections) {
        int vStep = start.getVerticalVector(finish);
        int hStep = Math.abs(start.getHorizontalVector(finish));

        boolean isWhitePiece = getColor().getSymbol().equalsIgnoreCase("w");
        boolean anyDirections = (vStep == 0 && pieceMaxMove >= hStep) || (hStep == 0 && pieceMaxMove >= Math.abs(vStep));

        if (anyDirections) {
            if (!onlyForwardDirections) return true;
            return isWhitePiece ? vStep > 0 : vStep < 0;
        } else return false;
    }

    default boolean canBeDiagonalMove(Position start, Position finish, int pieceMaxMove, boolean onlyForwardDirections) {
        int vStep = start.getVerticalVector(finish);
        int hStep = Math.abs(start.getHorizontalVector(finish));

        boolean isWhitePiece = getColor().getSymbol().equalsIgnoreCase("w");
        boolean anyDirections = vStep != 0 && pieceMaxMove >= Math.abs(vStep) && hStep == Math.abs(vStep);

        if (anyDirections) {
            if (!onlyForwardDirections) return true;
            return isWhitePiece ? vStep > 0 : vStep < 0;
        } else return false;
    }
}
