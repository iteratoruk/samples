package iteratoruk.gameoflife;

import org.junit.Test;
import iteratoruk.gameoflife.Game.Square;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void shouldBeTolerantOfNonExistentGridReferenceWhenGetSquare() {
        // given
        Game game = new Game(new boolean[0][0]);
        // when
        Square actual = game.getSquare(42, 24);
        // then
        assertThat(actual.getX()).isEqualTo(42);
        assertThat(actual.getY()).isEqualTo(24);
        assertThat(actual.isAlive()).isFalse();
    }

    @Test
    public void shouldReturnTrueGiven1x1GridInLiveState() {
        // given
        Game game = new Game(new boolean[][] {
                { true }
        });
        // when
        boolean actual = game.getSquare(0, 0).isAlive();
        // then
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldReturnFalseGiven1x1GridInDeadState() {
        // given
        Game game = new Game(new boolean[][] {
                { false }
        });
        // when
        boolean actual = game.getSquare(0, 0).isAlive();
        // then
        assertThat(actual).isFalse();
    }

    @Test
    public void shouldReturnNumberOfLiveNeighboursAsZeroGivenEmptyGrid() {
        // given
        Game game = new Game(new boolean[0][0]);
        // when
        int actual = game.getSquare(0, 0).getNumberOfLiveNeighbours();
        // then
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void shouldReturnNumberOfLiveNeighboursAsThree() {
        // given
        Game game = new Game(new boolean[][] {
                { true , true , true  },
                { false, false, false },
                { false, false, false }
        });
        // when
        int actual = game.getSquare(1, 1).getNumberOfLiveNeighbours();
        // then
        assertThat(actual).isEqualTo(3);
    }

    @Test
    public void shouldReturnCurrentGridStateAsBooleanArray() {
        // given
        Game game = new Game(new boolean[][] {
                { true , true , true  },
                { false, false, false },
                { false, false, false }
        });
        // when
        boolean[][] grid = game.grid();
        // then
        assertThat(grid).isEqualTo(new boolean[][] {
                { true , true , true  },
                { false, false, false },
                { false, false, false }
        });
    }

    @Test
    public void shouldEvolveBlinkerGame() {
        // given
        Game neanderthal = Game.BLINKER;
        // when
        Game croMagnon = neanderthal.evolve();
        // then
        assertThat(croMagnon.grid()).isEqualTo(new boolean[][] {
                { false, false, false, false, false },
                { false, false, true , false, false },
                { false, false, true , false, false },
                { false, false, true , false, false },
                { false, false, false, false, false }
        });
        // when
        Game homoSapiens = croMagnon.evolve();
        // then
        assertThat(homoSapiens.grid()).isEqualTo(neanderthal.grid()); // we have reverted to the original state
    }

}