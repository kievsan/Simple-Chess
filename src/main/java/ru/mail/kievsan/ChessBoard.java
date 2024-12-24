package ru.mail.kievsan;

public class ChessBoard {

    static final String ROWS = "87654321";
    static final String COLUMNS = "abcdefgh";


    static final int rows = ROWS.length();
    static final int cols = COLUMNS.length();

    private final Pieces[][] pieces;
    String whiteGamer, blackGamer;

    public ChessBoard(String whiteGamer, String blackGamer) {
        this.pieces = new PiecesImpl[rows][cols];
        this.whiteGamer = whiteGamer;
        this.blackGamer = blackGamer;
        arrangePieces();
        printBoard();
    }

    public Pieces[][] getPieces() {
        return pieces;
    }

    private void arrangePieces() {
        // Setup Rooks
        pieces[0][0] = new PieceRook(Color.BLACK);
        pieces[0][cols - 1] = new PieceRook(Color.BLACK);
        pieces[rows - 1][0] = new PieceRook(Color.WHITE);
        pieces[rows - 1][cols - 1] = new PieceRook(Color.WHITE);
        // Setup Knights
        pieces[0][1] = new PieceHorse(Color.BLACK);
        pieces[0][cols - 2] = new PieceHorse(Color.BLACK);
        pieces[rows - 1][1] = new PieceHorse(Color.WHITE);
        pieces[rows - 1][cols - 2] = new PieceHorse(Color.WHITE);
        // Setup Bishops
        pieces[0][2] = new PieceBishop(Color.BLACK);
        pieces[0][cols - 3] = new PieceBishop(Color.BLACK);
        pieces[rows - 1][2] = new PieceBishop(Color.WHITE);
        pieces[rows - 1][cols - 3] = new PieceBishop(Color.WHITE);
        // Setup Queens
        pieces[0][3] = new PieceQueen(Color.BLACK);
        pieces[rows - 1][3] = new PieceQueen(Color.WHITE);
        // Setup Kings
        pieces[0][cols - 4] = new PieceKing(Color.BLACK);
        pieces[rows - 1][cols - 4] = new PieceKing(Color.WHITE);
        // Setup Pawns
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new PiecePawn(Color.BLACK);
            pieces[rows - 2][i] = new PiecePawn(Color.WHITE);
        }
    }

    public void printBoard() {
        StringBuilder titleLineBuilder = new StringBuilder("\n");
        StringBuilder dividingLineBuilder = new StringBuilder("\n  |");
        COLUMNS.chars().forEach(column -> {
            titleLineBuilder.append("    ").append((char) column);
            dividingLineBuilder.append("====|");
        });
        String titleLine = titleLineBuilder.toString();
        String dividingLine = dividingLineBuilder.toString();

        StringBuilder representation = new StringBuilder(titleLine + blackGamer + dividingLine);
        ROWS.chars().forEach(row -> {
            representation
                    .append("\n")
                    .append((char)row)
                    .append(" | ");
            for (Pieces piece : this.pieces[ROWS.indexOf((char)row)])
                representation
                        .append(piece == null ? " " : piece.getID().getSymbol())
                        .append(piece == null ? " " : piece.getColor().getSymbol())
                        .append(" | ");
            representation
                    .append((char)row)
                    .append(dividingLine);
        });
        representation.append(titleLine).append(whiteGamer);

        System.out.println(representation);
    }
}
