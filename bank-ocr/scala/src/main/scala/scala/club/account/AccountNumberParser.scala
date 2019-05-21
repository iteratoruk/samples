package scala.club.account

import java.io.InputStream

import scala.io.Source

class AccountNumberParser() {

  def parse(in: InputStream): String = calculateTotals(
    Source.fromInputStream(in).getLines().toList.zipWithIndex.map(lineToScores)
  ).map(totalToDigit).mkString

  private def lineToScores(lineWithIndex: (String, Int)): List[Int] =
    lineWithIndex._1.sliding(3, 3).toList.map(str => characterLineToScore(str, lineWithIndex._2))

  private def characterLineToScore(characterLine: String, i: Int): Int = characterLine.zipWithIndex.toList.foldLeft(0) { (total: Int, entry: (Char, Int)) =>
    total + (charToInt(entry._1) * (i + 10) * (entry._2 + 100))
  }

  private def charToInt(c: Char): Int = c match {
    case '_' => 10
    case '|' => 100
    case _ => 0
  }

  private def calculateTotals(scores: List[List[Int]]): List[Int] = (0 to 8).map { j =>
    (0 to 2) map { i =>
      (i, j)
    }
  }.map {
    _.foldLeft(0) { (total: Int, coord: (Int, Int)) =>
      total + scores(coord._1).lift(coord._2).getOrElse(0)
    }
  }.toList

  private def totalToDigit(total: Int): Char = total match {
    case 234600 => '1'
    case 265530 => '2'
    case 267930 => '3'
    case 355710 => '4'
    case 265730 => '5'
    case 385730 => '6'
    case 244700 => '7'
    case 497930 => '8'
    case 377930 => '9'
    case 486820 => '0'
    case _ => throw new IllegalArgumentException(s"Unrecognized digit total: $total")
  }

}
