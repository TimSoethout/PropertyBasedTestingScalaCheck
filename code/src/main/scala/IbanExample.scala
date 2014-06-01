/**
 * Created by tim on 01/06/14.
 */
object IbanExample {

  val countryPart = "NL"
  val bankPart = "INGB"

  def calculateIban(bban: String): String = {
    val numberPart = prefill(bban, 10)
    val hashList = bankPart ++ numberPart ++ countryPart  ++ "00"
    val bbanHashNumber = BigInt(hashList.map(_.asDigit).mkString)

    val controlNumber = 98 - (bbanHashNumber % 97)

    val controlPart = prefill(controlNumber.toString(), 2)

    s"${countryPart}${controlPart}${bankPart}${numberPart}"
  }

  private def prefill(input: String, preferedLength: Int): String = {
    if (input.length < preferedLength)
      List.fill(preferedLength - input.length)("0").mkString("") ++ input
    else input.substring(0, preferedLength)
  }
}
