package iteratoruk.turtle

import iteratoruk.turtle.Orientations._
import org.scalatest._

class TurtleSpec extends WordSpec with MustMatchers {

  class Scenario {
    val turtle = Turtle()
  }

  "turtle" should {

    "start in centre" in new Scenario {
      turtle.currentSquare must be ("d4")
    }

    "start facing north" in new Scenario {
      turtle.orientation must be (North)
    }

    "rotate clockwise" in new Scenario {
      turtle.rotateClockwise.orientation must be (NorthEast)
    }

    "rotate counter-clockwise" in new Scenario {
      turtle.rotateCounterClockwise.orientation must be (NorthWest)
    }

    "rotate bi-directionally through the compass" in new Scenario {
      turtle.orientation must be (North)
      val t1 = turtle.rotateClockwise
      t1.orientation must be (NorthEast)
      val t2 = t1.rotateClockwise
      t2.orientation must be (East)
      val t3 = t2.rotateClockwise
      t3.orientation must be (SouthEast)
      val t4 = t3.rotateClockwise
      t4.orientation must be (South)
      val t5 = t4.rotateClockwise
      t5.orientation must be (SouthWest)
      val t6 = t5.rotateClockwise
      t6.orientation must be (West)
      val t7 = t6.rotateClockwise
      t7.orientation must be (NorthWest)
      val t8 = t7.rotateClockwise
      t8.orientation must be (North)
      val t9 = t8.rotateCounterClockwise
      t9.orientation must be (NorthWest)
      val t10 = t9.rotateCounterClockwise
      t10.orientation must be (West)
      val t11 = t10.rotateCounterClockwise
      t11.orientation must be (SouthWest)
      val t12 = t11.rotateCounterClockwise
      t12.orientation must be (South)
      val t13 = t12.rotateCounterClockwise
      t13.orientation must be (SouthEast)
      val t14 = t13.rotateCounterClockwise
      t14.orientation must be (East)
      val t15 = t14.rotateCounterClockwise
      t15.orientation must be (NorthEast)
      val t16 = t15.rotateCounterClockwise
      t16.orientation must be (North)
    }

    "move one square north" in new Scenario {
      turtle.moveForward.currentSquare must be ("d5")
    }

    "move one square south" in new Scenario {
      turtle
        .rotateClockwise
        .rotateClockwise
        .rotateClockwise
        .rotateClockwise
        .moveForward
        .currentSquare must be ("d3")
    }

    "move one square east" in new Scenario {
      turtle
        .rotateClockwise
        .rotateClockwise
        .moveForward
        .currentSquare must be ("e4")
    }

    "move one square west" in new Scenario {
      turtle
        .rotateCounterClockwise
        .rotateCounterClockwise
        .moveForward
        .currentSquare must be ("c4")
    }

    "move one square northeast" in new Scenario {
      turtle
        .rotateClockwise
        .moveForward
        .currentSquare must be ("e5")
    }

    "move one square northwest" in new Scenario {
      turtle
        .rotateCounterClockwise
        .moveForward
        .currentSquare must be ("c5")
    }

    "move one square southeast" in new Scenario {
      turtle
        .rotateClockwise
        .rotateClockwise
        .rotateClockwise
        .moveForward
        .currentSquare must be ("e3")
    }

    "move one square southwest" in new Scenario {
      turtle
        .rotateCounterClockwise
        .rotateCounterClockwise
        .rotateCounterClockwise
        .moveForward
        .currentSquare must be ("c3")
    }

    "not be able to move beyond board limit northward" in new Scenario {
      intercept[IllegalStateException] {
        val t1 = turtle
          .moveForward
          .moveForward
          .moveForward
        t1.currentSquare must be ("d7")
        t1.moveForward
      }
    }

    "not be able to move beyond board limit eastward" in new Scenario {
      intercept[IllegalStateException] {
        val t1 = turtle
          .rotateClockwise
          .rotateClockwise
          .moveForward
          .moveForward
          .moveForward
        t1.currentSquare must be ("g4")
        t1.moveForward
      }
    }

    "not be able to move beyond board limit southward" in new Scenario {
      intercept[IllegalStateException] {
        val t1 = turtle
          .rotateClockwise
          .rotateClockwise
          .rotateClockwise
          .rotateClockwise
          .moveForward
          .moveForward
          .moveForward
        t1.currentSquare must be ("d1")
        t1.moveForward
      }
    }

    "not be able to move beyond board limit westward" in new Scenario {
      intercept[IllegalStateException] {
        val t1 = turtle
          .rotateCounterClockwise
          .rotateCounterClockwise
          .moveForward
          .moveForward
          .moveForward
        t1.currentSquare must be ("a4")
        t1.moveForward
      }
    }

  }

}
