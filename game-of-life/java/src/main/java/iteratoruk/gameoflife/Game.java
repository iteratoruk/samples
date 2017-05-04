
package iteratoruk.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Game {

    public static final class Square {

        private final int x, y;

        private List<Square> neighbours;

        private final Game game;

        private final int value;

        Square(int x, int y, int value, Game game) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.game = game;
        }

        public boolean isAlive() {
            return value > 0;
        }

        public int getNumberOfLiveNeighbours() {
            if (neighbours == null) {
                neighbours = Stream.of(
                        game.getSquare(x + 1, y + 1),
                        game.getSquare(x + 1, y),
                        game.getSquare(x + 1, y -1),
                        game.getSquare(x - 1, y + 1),
                        game.getSquare(x - 1, y),
                        game.getSquare(x - 1, y - 1),
                        game.getSquare(x, y + 1),
                        game.getSquare(x, y -1)
                ).collect(Collectors.toList());
            }
            return neighbours.stream().map(sq -> sq.value).mapToInt(i -> i.intValue()).sum();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        Square copy(boolean alive) {
            return new Square(x, y, alive ? 1 : 0, game);
        }

    }

    public static Game BLINKER = new Game(new boolean[][] {
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, true , true , true , false },
            { false, false, false, false, false },
            { false, false, false, false, false }
    });

    private final List<Square> s = new ArrayList<>();

    private final boolean[][] g; // really, just here for testability/state verification

    // externally, we represent as booleans to limit potential values to 'alive' (true) or 'dead' (false)
    public Game(boolean[][] squares) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                s.add(new Square(j, i, squares[i][j] ? 1 : 0, this));
            }
        }
        g = squares;
    }

    public Square getSquare(int x, int y) {
        return s.stream().filter(sq -> sq.x == x && sq.y == y).findFirst().orElse(new Square(x, y, 0, this));
    }

    public boolean[][] grid() {
        return g;
    }

    public Game evolve() {
        List<Square> evolved = s.stream().map(sq -> {
            int livingNeighbours = sq.getNumberOfLiveNeighbours();
            // 1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
            // 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
            if (sq.isAlive() && livingNeighbours < 2 || livingNeighbours > 3) {
                return sq.copy(false);
            }
            // 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
            if (!sq.isAlive() && livingNeighbours == 3) {
                return sq.copy(true);
            }
            // 2. Any live cell with two or three live neighbours lives on to the next generation.
            return sq;
        }).collect(Collectors.toList());
        boolean[][] squares = new boolean[evolved.stream().mapToInt(sq -> sq.y).max().getAsInt() + 1][evolved.stream().mapToInt(sq -> sq.x).max().getAsInt() + 1];
        for (Square sq : evolved) {
            squares[sq.y][sq.x] = sq.isAlive();
        }
        return new Game(squares);
    }

}
