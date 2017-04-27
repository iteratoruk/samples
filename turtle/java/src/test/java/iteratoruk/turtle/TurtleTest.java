
package iteratoruk.turtle;

import static org.assertj.core.api.Assertions.*;
import static iteratoruk.turtle.Turtle.Orientation.*;

import org.junit.Before;
import org.junit.Test;

/*
   a b c d e f g
  |-|-|-|-|-|-|-|
7 | | | | | | | |
  |-|-|-|-|-|-|-|
6 | | | | | | | |
  |-|-|-|-|-|-|-|
5 | | | | | | | |
  |-|-|-|-|-|-|-|
4 | | | |X| | | |
  |-|-|-|-|-|-|-|
3 | | | | | | | |
  |-|-|-|-|-|-|-|
2 | | | | | | | |
  |-|-|-|-|-|-|-|
1 | | | | | | | |
  |-|-|-|-|-|-|-|
 */
public class TurtleTest {

    private Turtle turtle;

    @Before
    public void setup() {
        turtle = new Turtle();
    }

    @Test
    public void should_start_in_centre() {
        assertThat(turtle.getCurrentSquare()).isEqualTo("d4");
    }

    @Test
    public void should_start_facing_north() {
        assertThat(turtle.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void should_rotate_clockwise() {
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(NORTH_EAST);
    }

    @Test
    public void should_rotate_counter_clockwise() {
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(NORTH_WEST);
    }

    @Test
    public void should_rotate_bidirectionally_through_compass() {
        assertThat(turtle.getOrientation()).isEqualTo(NORTH);
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(NORTH_EAST);
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(EAST);
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(SOUTH_EAST);
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(SOUTH);
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(SOUTH_WEST);
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(WEST);
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(NORTH_WEST);
        turtle.rotateClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(NORTH);
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(NORTH_WEST);
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(WEST);
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(SOUTH_WEST);
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(SOUTH);
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(SOUTH_EAST);
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(EAST);
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(NORTH_EAST);
        turtle.rotateCounterClockwise();
        assertThat(turtle.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void should_move_one_square_north() {
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("d5");
    }

    @Test
    public void should_move_one_square_south() {
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("d3");
    }

    @Test
    public void should_move_one_square_east() {
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("e4");
    }

    @Test
    public void should_move_one_square_west() {
        turtle.rotateCounterClockwise();
        turtle.rotateCounterClockwise();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("c4");
    }

    @Test
    public void should_move_one_square_north_east() {
        turtle.rotateClockwise();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("e5");
    }

    @Test
    public void should_move_one_square_north_west() {
        turtle.rotateCounterClockwise();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("c5");
    }

    @Test
    public void should_move_one_square_south_east() {
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("e3");
    }

    @Test
    public void should_move_one_square_south_west() {
        turtle.rotateCounterClockwise();
        turtle.rotateCounterClockwise();
        turtle.rotateCounterClockwise();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("c3");
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_be_able_to_move_beyond_board_limit_northward() {
        turtle.moveForward();
        turtle.moveForward();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("d7");
        turtle.moveForward();
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_be_able_to_move_beyond_board_limit_eastward() {
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.moveForward();
        turtle.moveForward();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("g4");
        turtle.moveForward();
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_be_able_to_move_beyond_board_limit_southward() {
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.rotateClockwise();
        turtle.moveForward();
        turtle.moveForward();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("d1");
        turtle.moveForward();
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_be_able_to_move_beyond_board_limit_westward() {
        turtle.rotateCounterClockwise();
        turtle.rotateCounterClockwise();
        turtle.moveForward();
        turtle.moveForward();
        turtle.moveForward();
        assertThat(turtle.getCurrentSquare()).isEqualTo("a4");
        turtle.moveForward();
    }

}