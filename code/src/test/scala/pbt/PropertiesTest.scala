package pbt

import IbanExample._
import org.scalacheck._
import org.scalacheck.Prop.{forAll, BooleanOperators}

/**
 * Some properties using the ScalaTest
 */
object PropertiesTest extends Properties("IbanExample") {

  property("should be the same after reversing twice") =
    forAll((s: String) => s.reverse.reverse == s)

  property("contain the origin bban") =
    forAll {
      bban: Int =>
        (bban > 0) ==>
          (calculateIban(bban.toString) contains bban.toString)
    }

  property("prefilled strings should have the preferred length") =
    forAll(Arbitrary.arbitrary[String], Gen.posNum[Int]) {
      (s, i) =>
        prefill(s, i).length == i
    }

  property("bounded prefilled strings should have the correct number of preceding 0's") =
    forAll(Arbitrary.arbitrary[String], Gen.posNum[Int]) {
      (s, i) =>
        (s.length < i) ==>
          (prefill(s, i).length equals ("0" * (i - s.length) ++ s).length)
    }

}
