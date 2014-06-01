package pbt

import IbanExample._
import org.scalacheck._
import org.scalacheck.Prop.{forAll, BooleanOperators}

object PropertiesTest extends Properties("IbanExample") {

  property("contain the origin bban") =
    forAll {
      bban: Int =>
        (bban > 0) ==>
          (calculateIban(bban.toString) contains bban.toString)
    }
}
