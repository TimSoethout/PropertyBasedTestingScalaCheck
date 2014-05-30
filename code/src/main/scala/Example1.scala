/**
 * Created by tim on 30/05/14.
 */
object Example1 {
  def reverseStrings(list: List[String]): List[String] = {
    list match {
      case Nil => Nil
      case (x :: xs) => reverseStrings(xs) ++ List(x)
    }
  }
}
