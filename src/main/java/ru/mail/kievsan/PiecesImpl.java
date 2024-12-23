package ru.mail.kievsan;

public abstract class PiecesImpl implements Pieces {

    private final Color color;

    protected PiecesImpl(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
