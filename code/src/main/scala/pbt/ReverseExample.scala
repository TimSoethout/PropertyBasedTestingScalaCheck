package pbt

object ReverseExample {
  def reverseStrings(list: List[String]): List[String] = {
    list match {
      case Nil => Nil
      case (x :: xs) => reverseStrings(xs) ++ List(x)
    }
  }

  def genericReverse[T](list: List[T]): List[T] = {
    list.foldLeft(List[T]()) {
      case (res, x) => x :: res
    }
  }
}
