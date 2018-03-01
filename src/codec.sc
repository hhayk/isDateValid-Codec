import java.time.LocalDate

import scala.util.{Failure, Try}

def isValidDate(s: String) = {
  val args = s.split("/").toList.map(_.toInt)
  args.permutations.map { arr =>
    Try {
      val year = arr.head + (if (arr.head < 100) 2000 else 0)
      LocalDate.of(year, arr(1), arr(2))
    }
  }.filter(_.isSuccess).map(_.get).toList match {
    case Nil => "is illegal"
    case xs => xs.minBy(_.toEpochDay).toString
  }
}

//isValidDate("12/10/17")
//isValidDate("15/15/15")
//isValidDate("15/15/0000")
isValidDate("12/15/1996")
isValidDate("12/15/48")