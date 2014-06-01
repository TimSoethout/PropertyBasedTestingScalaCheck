package pbt

import IbanExample._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import org.scalacheck.Gen

class IbanExampleTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  behavior of "IbanExample"

  it should "convert a simple bban correctly" in {
    calculateIban("1111111") should be("NL24INGB0001111111")
  }

  it should "contain the origin bban" in {
    forAll {
      bban: Int =>
        whenever(bban > 0) {
          calculateIban(bban.toString) should include(bban.toString)
        }
    }
  }

  def bbans: Gen[String] =
    for {
      x <- Gen.chooseNum(0, 9999999)
    } yield x.toString

  it should "still contain the origin bban using a custom generator" in {
    forAll(bbans) { bban =>
      calculateIban(bban) should include(bban)
    }
  }

}
