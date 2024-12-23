package ru.mail.kievsan;

public enum PiecesID {
    PAWN("Pawn"), // пешка
    KING("King"), // король
    QUEEN("Queen"), // ферзь
    BISHOP("Bishop"), // слон
    HORSE("Horse"), // конь
    ROOK("Rook"); // ладья

    private final String name;

    PiecesID(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getSymbol() {
        return name.substring(0, 1).toUpperCase();
    }
}
