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

  property("test") =
    forAll (Arbitrary.arbitrary[String], Gen.posNum[Int]) {
      (s, i) =>
        prefill(s, i).length == i
    }

  property("bounded") =
    forAll (Arbitrary.arbitrary[String], Gen.posNum[Int]) {
      (s, i) =>
        (s.length < i) ==>
          (prefill(s, i).length equals ("0" * (i - s.length) ++ s).length)
    }

}
