package ru.mail.kievsan;

import java.util.*;

public class ChessGame {

    private final ChessBoard board = new ChessBoard();
    private final List<MoveHistory> history = new ArrayList<>();
    private final Scanner scan = new Scanner(System.in);
    private final Gamer whiteGamer;
    private final Gamer blackGamer;

    private int movesCounter = 0;

    private Gamer currentGamer;

    public ChessGame(String whiteName, String blackName) {
        this.whiteGamer = new Gamer(Color.WHITE, whiteName);
        this.blackGamer = new Gamer(Color.BLACK, blackName);
    }

    public List<MoveHistory> getHistory() {
        return history;
    }

    public void printHistory() {
        System.out.printf("%nGAME HISTORY:\tходов - %s.%n============%n", getMovesCounter());
        for (MoveHistory move : getHistory()) System.out.println(move);
    }

    public int getMovesCounter() {
        return movesCounter;
    }

    public void start() {
        this.currentGamer = whiteGamer;
        Map<String, String> nextMove;
        while (true) {
            try {
                nextMove = currentGamer.getNextMove();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }

            var start = new Position(nextMove.get("start"));
            var finish = new Position(nextMove.get("finish"));

            Pieces startPiece = this.board.pieces[start.getRow()][start.getColumn()];
            Pieces finishPiece = this.board.pieces[finish.getRow()][finish.getColumn()];

            if (isNotValid(startPiece, finishPiece)) continue;

            if (!startPiece.canBeMove(start, finish, board)) {
                System.out.printf("%s, %s так не ходит!%n", currentGamer.getName(), startPiece.getID().name());
                continue;
            }

            System.out.printf("Принят ход: %s-%s%n", nextMove.get("start"), nextMove.get("finish"));

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
            System.out.printf("%s, нет вашей фигуры в выбранном поле, чтобы сделать ход!" +
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

        public Map<String, String> getNextMove() throws Exception {
            String nextMove;
            System.out.printf("""
                    
                    Введите через пробел местоположение фигуры для следующего хода,
                    и куда поставить эту  фигуру на доске, например: e2 e3
                    Для выхода из игры введите 'exit'.
                    
                    %s, твой ход: %d-й%n""", currentGamer.getName(), movesCounter + 1);
            while ((nextMove = scan.nextLine()).isBlank() || !(nextMove = nextMove.trim()).equals("exit")) {
                try {
                    return validMoveWithinChessBoard(nextMove);
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            throw new Exception(("\nИгра прервана на %d-м ходу " +
                    "игроком %s").formatted(movesCounter + 1, currentGamer.getName()));
        }

        private Map<String, String> validMoveWithinChessBoard(String nextMove) {
            String err = "Неполные, некорректные данные для хода или за пределами шахматной доски.";
            try {
                var move = getMoveMap(nextMove);
                if (Position.positionToInt(move.get("start")) == null
                        || Position.positionToInt(move.get("finish")) == null)
                    throw new InputMismatchException(err);
                return move;
            } catch (Exception ex) {
                throw new InputMismatchException(err);
            }
        }

        private Map<String, String> getMoveMap(String rawMove) {
            String[] move = rawMove.trim().split(" ");
            Map<String, String> validatedMove = new HashMap<>(2);
            validatedMove.put("start", move[0]);
            validatedMove.put("finish", move[1]);
            return validatedMove;
        }

        public MoveHistory moveIt(Position from, Position into) {
            var newHistory = new MoveHistory(++movesCounter, currentGamer,
                    new Position(from.getRow(),from.getColumn()),
                    new Position(into.getRow(), into.getColumn()),
                    board.pieces[from.getRow()][from.getColumn()],
                    board.pieces[into.getRow()][into.getColumn()]
            );
            board.pieces[into.getRow()][into.getColumn()] = board.pieces[from.getRow()][from.getColumn()];
            board.pieces[from.getRow()][from.getColumn()] = null;

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
