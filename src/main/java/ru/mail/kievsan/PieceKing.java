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
    public boolean canBeMove(Position start, Position finish, ChessBoard board){
        return canBeDiagonalMove(start, finish, 1, false) &&
                canBeLineMove(start, finish, 1, false);
    }

    boolean isUnderAttack(ChessBoard board, int line, int column) {
        // в разработке:
        Position kingPosition = new Position(line, column);
        Pieces[][] pieces = board.getPieces();
        for (int rowNum = 0; rowNum < pieces.length; rowNum++) {
            Pieces[] row = pieces[rowNum];
            for (int colNum = 0; colNum < row.length; colNum++) {
                var piece = row[colNum];
                if (piece.canBeAttack(new Position(rowNum, colNum), kingPosition, board)) return true;
            }
        }
        return false;
    }
}
