package pbt

import Example1._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{Matchers, FlatSpec}

class Example1Test extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  behavior of "reverseStrings"

  it should "reverse a string" in {
    val reversed = reverseStrings(List("A", "list", "of", "Strings"))
    reversed should equal(List("Strings", "of", "list", "A"))
  }

  it should "also reverse all strings" in {
    forAll {
      (ss: List[String]) => reverseStrings(ss) should be(ss.reverse) // should result in the same as library implementation
    }
  }

  it should "have the same size" in {
    forAll {
      (ss: List[String]) => reverseStrings(ss) should have length ss.length
    }
  }

  it should "have the same elements" in {
    forAll {
      (ss: List[String]) => reverseStrings(ss) should contain theSameElementsAs ss
    }
  }

  it should "be the same after reversing twice" in {
    forAll {
      (ss: List[String]) => reverseStrings(reverseStrings(ss)) should equal(ss)
    }
  }

}
