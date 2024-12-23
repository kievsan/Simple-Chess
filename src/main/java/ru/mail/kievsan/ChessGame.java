package ru.mail.kievsan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessGame {
    private final ChessBoard board = new ChessBoard();
    private final List<MoveHistory> history = new ArrayList<>();
    private final Scanner scan = new Scanner(System.in);
    private final Gamer whiteGamer;
    private final Gamer blackGamer;

    private int movesCounter = 0;

    Gamer currentGamer;

    public ChessGame(String whiteName, String blackName) {
        this.whiteGamer = new Gamer(Color.WHITE, whiteName);
        this.blackGamer = new Gamer(Color.BLACK, blackName);
    }

    public void start() {
        this.currentGamer = whiteGamer;
        String[] nextMove;
        while (true) {
            try {
                nextMove = currentGamer.getNextMove();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }

            int[] start = Position.positionToInt(nextMove[0]);
            int[] finish = Position.positionToInt(nextMove[1]);

            Pieces startPiece = this.board.pieces[start[0]][start[1]];
            Pieces finishPiece = this.board.pieces[finish[0]][finish[1]];

            if (isNotValid(startPiece, finishPiece)) continue;

            // TODO  проверка, нет ли мешающих фигур на пути? (конь и пешка - с ньюансами...)

            System.out.printf("Принят ход: %s-%s%n", nextMove[0], nextMove[1]);

            var newHistory = currentGamer.moveIt(start, finish);
            history.add(newHistory);

            board.printBoard();
            System.out.printf("%n%s%n", newHistory);

            currentGamer = getNextGamer();
        }
        System.out.println("Game over!");
    }

    private boolean isNotValid(Pieces startPiece, Pieces finishPiece) {
        if (startPiece == null) {
            System.out.printf("%s, нет вашей фигуры в выбранном поле, чтобы походить!" +
                    "%n", currentGamer.getName());
            return true;
        }

        if (!startPiece.getColor().equals(currentGamer.getColor())) {
            System.out.printf("%s, можно ходить только своими фигурами (%s)!" +
                    "%n", currentGamer.getName(), currentGamer.getColor().name());
            return true;
        }
        if (finishPiece == null) return false;

        if (finishPiece.getColor().equals(currentGamer.getColor())) {
            System.out.printf("%s, нельзя пойти в клетку, занятую своей же фигурой (%s)!" +
                    "%n", currentGamer.getName(), currentGamer.getColor().name());
            return true;
        }

        return false;
    }

    private Gamer getNextGamer() {
        return currentGamer.equals(whiteGamer) ? blackGamer : whiteGamer;
    }

    public int getMovesCounter() {
        return movesCounter;
    }


    private class Gamer {
        private final Color color;
        private final String name;

        private Gamer(Color color, String name) {
            this.color = color;
            this.name = name;
        }

        public Color getColor() {
            return color;
        }

        public String getName() {
            return name;
        }

        public String[] getNextMove() throws Exception {
            String nextMove;
            String[] move;
            System.out.printf("""
                    
                    Введите через пробел местоположение фигуры для следующего хода, 
                    и куда поставить эту  фигуру на доске, например: e2 e3
                    Для выхода из игры введите 'exit'.
                    
                    %s, твой ход: %d-й%n""", currentGamer.getName(), movesCounter + 1);
            while ((nextMove = scan.nextLine()).isBlank() || !(nextMove = nextMove.trim()).equals("exit")) {
                if ((move = nextMove.trim().split(" ")).length > 1)
                    if ((Position.positionToInt(move[0]) != null) && Position.positionToInt(move[1]) != null)
                        return new String[]{move[0], move[1]};
                System.out.printf("""
                        Ошибочный ход - не принят!
                        
                        Попробуй еще раз,например: e2 e3
                        %s, твой ход: %d-й%n""", currentGamer.getName(), movesCounter + 1);;
            }
            throw new Exception(("\nИгра прервана на %d-м ходу " +
                    "игроком %s").formatted(movesCounter + 1, currentGamer.getName()));
        }

        public MoveHistory moveIt(int[] from, int[] into) {
            var newHistory = new MoveHistory(++movesCounter, currentGamer,
                    new Position(from[0],from[1]),
                    new Position(into[0], into[1]),
                    board.pieces[from[0]][from[1]],
                    board.pieces[into[0]][into[1]]
            );

            board.pieces[into[0]][into[1]] = board.pieces[from[0]][from[1]];
            board.pieces[from[0]][from[1]] = null;

            return newHistory;
        }
    }


    private class MoveHistory {
        private final int moveNumber;
        private final Gamer gamer;
        private final Position fromPosition;
        private final Position intoPosition;
        private final Pieces piece;
        private final Pieces lostPeace;

        public MoveHistory(int moveNumber, Gamer gamer,
                           Position fromPosition, Position intoPosition,
                           Pieces piece, Pieces lostPeace) {
            this.moveNumber = moveNumber;
            this.gamer = gamer;
            this.fromPosition = fromPosition;
            this.intoPosition = intoPosition;
            this.piece = piece;
            this.lostPeace = lostPeace;
        }

        @Override
        public String toString() {
            StringBuilder interview = new StringBuilder("На %d ходу ".formatted(moveNumber));
            interview
                    .append(gamer.getName())
                    .append(":\t%s%s ".formatted(piece.getID().getSymbol(), piece.getColor().getSymbol()))
                    .append("%s-%s".formatted(fromPosition.getPosition(), intoPosition.getPosition()))
            ;
            if (lostPeace != null) interview.append(", убиты - %s%s"
                    .formatted(piece.getID().getSymbol(), piece.getColor().getSymbol()));
            interview.append(".");
            return interview.toString();
        }
    }
}