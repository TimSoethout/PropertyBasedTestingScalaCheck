package pbt

/**
 * Example that calculates Iban from Bban
 *
 * Algorithm from wikipedia
 * The preferred algorithm is:
 * Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid
 * Replace the two check digits by 00 (e.g. GB00 for the UK)
 * Move the four initial characters to the end of the string
 * Replace the letters in the string with digits, expanding the string as necessary, such that A or a = 10, B or b = 11, and Z or z = 35. Each alphabetic character is therefore replaced by 2 digits
 * Convert the string to an integer (i.e. ignore leading zeroes)
 * Calculate mod-97 of the new number, which results in the remainder
 * Subtract the remainder from 98, and use the result for the two check digits. If the result is a single digit number, pad it with a leading 0 to make a two-digit number
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

    s"${countryPart}${controlPart}${bankPart}$numberPart"
  }

  def prefill(input: String, preferedLength: Int): String = {
    if (input.length < preferedLength)
      "0" * (preferedLength - input.length) ++ input
    else input.substring(0, preferedLength)
  }
}
