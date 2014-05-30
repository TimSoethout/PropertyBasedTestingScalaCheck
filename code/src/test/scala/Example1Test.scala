
class Example1Test extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "reverseStrings" should "reverse a string" in {
    Example1.reverseStrings(List("A", "list", "of", "Strings")) should equal List("Strings", "of", "list", "A")
  }

}
