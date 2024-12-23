package ru.mail.kievsan;

import java.util.InputMismatchException;

public class Position {

    private static final String ROWS = ChessBoard.ROWS;
    private static final String COLS = ChessBoard.COLUMNS;

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position(String pos) {
        int[] intPos;
        if ((intPos = positionToInt(pos)) == null) throw new InputMismatchException();
        this.row = intPos[0];
        this.column = intPos[1];
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getPosition() {
        return new String(new char[]{COLS.charAt(getColumn()), ROWS.charAt(getRow())});
    }

    public static int[] positionToInt(String pos) {
        // "a8" - (0, 0)
        if (pos.isBlank() && (pos = pos.trim().toLowerCase()).length() != 2) return null;
        int row, col;
        if (((col = COLS.indexOf(pos.charAt(0))) == -1) || ((row = ROWS.indexOf(pos.charAt(1))) == -1)) return null;
        return  new int[]{row, col};
    }

    public int[] getVector(Position next) {
        return new int[]{getHorizontalVector(next), getVerticalVector(next)};
    }

    public int getHorizontalVector(Position next) {
        return next.getColumn() - column;
    }

    public int getVerticalVector(Position next) {
        return next.getRow() - row;
    }
}
