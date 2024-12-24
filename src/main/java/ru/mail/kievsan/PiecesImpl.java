package ru.mail.kievsan;

public abstract class PiecesImpl implements Pieces {

    private final Color color;
    private int movesCounter = 0;

    PiecesImpl(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean hasMoved() {
        return movesCounter > 0;
    }

    @Override
    public void incMoved() {
        movesCounter++;
    }
}
