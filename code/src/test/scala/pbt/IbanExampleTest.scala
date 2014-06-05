package pbt

import IbanExample._
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import org.scalacheck.{Arbitrary, Gen}
import scala.util.parsing.json.JSON
import scala.io.Source

class IbanExampleTest extends FlatSpec with Matchers with PropertyChecks {

  behavior of "IbanExample"

  // basic unit test
  it should "convert a simple bban correctly" in {
    calculateIban("1111111") should equal("NL24INGB0001111111")
  }

  // simple property
  it should "contain the origin bban" in {
    forAll {
      bban: Int =>
        whenever(bban > 0) {
          calculateIban(bban.toString) should include(bban.toString)
        }
    }
  }

  def bbans: Gen[String] = Gen.chooseNum(0, 9999999) map (_.toString)

  // property with generator
  it should "still contain the origin bban using a custom generator" in
    forAll(bbans) {
      bban =>
        calculateIban(bban) should include(bban)
    }


  // property against known correct implementation
  it should "give the same result as openiban.nl" in {
    forAll(bbans) {
      bban =>
        val json = Source.fromURL(s"http://www.openiban.nl/?rekeningnummer=$bban&output=json").mkString
        val iban = JSON.parseFull(json).map {
          case m: Map[String, String] => m("iban")
        }.get
        calculateIban(bban) should equal(iban)
    }
  }


  // some magic to make the generator see the diference between String and Bban
  case class Bban(bban: String)
  implicit def BBanToString = (bban : Bban) => bban.bban

  // implicit generator
  implicit def BbanGen: Arbitrary[Bban] = Arbitrary(Gen.chooseNum(0, 9999999) map (i => Bban(i.toString)))

  it should "still contain the origin bban using the bban generator" in
    forAll {
      bban: Bban =>
        calculateIban(bban) should include(bban)
    }

  
  behavior of "prefill"

  it should "have the preferred length and end with the original string" in
    forAll(Arbitrary.arbitrary[String], Gen.posNum[Int]) {
      (s, i) =>
        val filled = prefill(s, i)
        filled should have length i
        if (s.length <= i) filled should endWith(s)
    }

  it should "have enough leading zero's when smaller than preferred length" in
    forAll(Arbitrary.arbitrary[String], Gen.posNum[Int]) {
      (s, i) =>
        whenever(s.length < i) {
          val nrOf0s = i - s.length
          prefill(s, i) should startWith("0" * nrOf0s)
        }
    }
}