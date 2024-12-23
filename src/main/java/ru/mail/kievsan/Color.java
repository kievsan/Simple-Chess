package ru.mail.kievsan;

public enum Color {
    BLACK("Black"), WHITE("White");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toLowerCase();
    }

    public String getSymbol() {
        return name.substring(0, 1).toLowerCase();
    }
}
