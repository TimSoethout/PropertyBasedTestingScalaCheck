In this repository you can find the slides and example code for a presentation I gave for the Scala community of ING.
It is basically an introduction to property based testing. And in particular the ScalaCheck library used in conjunction with ScalaTest.

ScalaCheck is a property based testing tool, which allows you to specify properties using predicates such as: ∀s:s.reverse.reverse≡s, which denotes that for all Strings s when you reverse s twice it should equal the original s.

Please see the [Slides](http://blog.timmybankers.nl/PropertyBasedTestingScalaCheck/index.html), which are created using the nice RevealJS.

Maybe even more interesting are the code examples which can be found in the code folder in this repository. There are a couple of files with accompanying tests. PropertiesTest.scala shows the ScalaCheck way of writing an executable test file which checks properties. `ReverseExampleTest.scala` contain some simple properties using ScalaTest’s `GeneratorDrivenPropertyChecks`, which using ScalaCheck under the hood. `IbanExampleTest.scala` contains a more interesting example where an implementation that calculates IBANs from old bank account numbers is tested.
