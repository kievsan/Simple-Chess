package ru.mail.kievsan;

public interface Pieces {
    Color getColor();
    PiecesID getID();
    boolean canBeMove();
    boolean canBeAttack();
}
