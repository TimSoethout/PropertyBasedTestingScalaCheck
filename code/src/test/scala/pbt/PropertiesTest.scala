package pbt

import IbanExample._
import org.scalacheck._
import Prop.forAll

object PropertiesTest extends Properties("IbanExample") {

  property("contain the origin bban") =
    forAll {
      bban: Int =>
        calculateIban(bban.toString) contains bban.toString
    }
}
