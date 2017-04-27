
package iteratoruk.turtle;

import java.util.Deque;
import java.util.LinkedList;

import static java.util.Arrays.asList;

public final class Turtle {

    public enum Orientation {

        NORTH {
            @Override
            void moveForward(Turtle turtle) {
                turtle.incrementY();
            }
        }, NORTH_EAST {
            @Override
            void moveForward(Turtle turtle) {
                turtle.incrementX();
                turtle.incrementY();
            }
        }, EAST {
            @Override
            void moveForward(Turtle turtle) {
                turtle.incrementX();
            }
        }, SOUTH_EAST {
            @Override
            void moveForward(Turtle turtle) {
                turtle.incrementX();
                turtle.decrementY();
            }
        }, SOUTH {
            @Override
            void moveForward(Turtle turtle) {
                turtle.decrementY();
            }
        }, SOUTH_WEST {
            @Override
            void moveForward(Turtle turtle) {
                turtle.decrementX();
                turtle.decrementY();
            }
        }, WEST {
            @Override
            void moveForward(Turtle turtle) {
                turtle.decrementX();
            }
        }, NORTH_WEST {
            @Override
            void moveForward(Turtle turtle) {
                turtle.decrementX();
                turtle.incrementY();
            }
        };

        abstract void moveForward(Turtle turtle);

    }

    private Deque<Orientation> orientation = new LinkedList<>(asList(Orientation.values()));

    private char x = 'd';

    private int y = 4;

    public String getCurrentSquare() {
        return String.valueOf(x) + String.valueOf(y);
    }

    public Orientation getOrientation() {
        return orientation.peekFirst();
    }

    public void rotateClockwise() {
        orientation.offer(orientation.pollFirst());
    }

    public void rotateCounterClockwise() {
        orientation.push(orientation.pollLast());
    }

    public void moveForward() {
        orientation.peekFirst().moveForward(this);
    }

    void incrementX() {
        if (x == 'g') throw new IllegalStateException("Cannot move beyond the 'g' file");
        x+=1;
    }

    void decrementX() {
        if (x == 'a') throw new IllegalStateException("Cannot move beyond the 'a' file");
        x-=1;
    }

    void incrementY() {
        if (y == 7) throw new IllegalStateException("Cannot move above the 7th rank");
        y+=1;
    }

    void decrementY() {
        if (y == 1) throw new IllegalStateException("Cannot move below the 1st rank");
        y-=1;
    }

}
