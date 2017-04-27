
package iteratoruk.turtle

import iteratoruk.turtle.Orientations._

case class Turtle(x: Char = 'd', y: Int = 4, orientation: Orientation = North) {
  val currentSquare = x.toString + y.toString
  def rotateClockwise: Turtle = copy(orientation = orientation.clockwise)
  def rotateCounterClockwise: Turtle = copy(orientation = orientation.counterClockwise)
  def moveForward: Turtle = orientation.move(this)

  private[turtle] def incrementX: Turtle = {
    if (x == 'g') throw new IllegalStateException("Cannot move beyond the 'g' file")
    copy(x = (x + 1).toChar)
  }

  private[turtle] def decrementX: Turtle = {
    if (x == 'a') throw new IllegalStateException("Cannot move beyond the 'a' file")
    copy(x = (x - 1).toChar)
  }

  private[turtle] def incrementY: Turtle = {
    if (y == 7) throw new IllegalStateException("Cannot move above the 7th rank")
    copy(y = y + 1)
  }

  private[turtle] def decrementY: Turtle = {
    if (y == 1) throw new IllegalStateException("Cannot move below the 1st rank")
    copy(y = y - 1)
  }
}

object Orientations {
  sealed trait Orientation {
    def clockwise: Orientation
    def counterClockwise: Orientation
    def move(turtle: Turtle): Turtle
  }

  case object North extends Orientation {
    override def clockwise: Orientation = NorthEast
    override def counterClockwise: Orientation = NorthWest
    override def move(turtle: Turtle): Turtle = turtle.incrementY
  }

  case object NorthEast extends Orientation {
    override def clockwise: Orientation = East
    override def counterClockwise: Orientation = North
    override def move(turtle: Turtle): Turtle = turtle.incrementX.incrementY
  }

  case object East extends Orientation {
    override def clockwise: Orientation = SouthEast
    override def counterClockwise: Orientation = NorthEast
    override def move(turtle: Turtle): Turtle = turtle.incrementX
  }

  case object SouthEast extends Orientation {
    override def clockwise: Orientation = South
    override def counterClockwise: Orientation = East
    override def move(turtle: Turtle): Turtle = turtle.incrementX.decrementY
  }

  case object South extends Orientation {
    override def clockwise: Orientation = SouthWest
    override def counterClockwise: Orientation = SouthEast
    override def move(turtle: Turtle): Turtle = turtle.decrementY
  }

  case object SouthWest extends Orientation {
    override def clockwise: Orientation = West
    override def counterClockwise: Orientation = South
    override def move(turtle: Turtle): Turtle = turtle.decrementX.decrementY
  }

  case object West extends Orientation {
    override def clockwise: Orientation = NorthWest
    override def counterClockwise: Orientation = SouthWest
    override def move(turtle: Turtle): Turtle = turtle.decrementX
  }

  case object NorthWest extends Orientation {
    override def clockwise: Orientation = North
    override def counterClockwise: Orientation = West
    override def move(turtle: Turtle): Turtle = turtle.decrementX.incrementY
  }
}