package at.spengergasse.kai17521.sem04.lab17;

/**
 * @author Samuel Kaiser
 * @since 2/28/2017
 */
class Number {
  final String digits;
  final int decimals;

  final static Number ZERO = new Number("0", 0);

  Number(String digits, int decimals) {
    this.digits = digits;
    this.decimals = decimals;
  }

  Number(String number) {
    double parsable = Double.parseDouble(number);
    String[] parts = number.split("\\.");
    String fractional = parts.length > 1 ? parts[1] : "";
    this.digits = parts[0] + fractional;
    this.decimals = fractional.length();
  }

  double getNumber() {
    return Double.parseDouble(toString());
  }

  private String getIntegerDigits() {
    String str = digits.substring(0, digits.length() - decimals);
    return str; // str.length() == 0 ? "0" + str : str;
  }

  private String getFractionalDigits() {
    String str = digits.substring(digits.length() - decimals, digits.length());
    return str; // str.length() == 0 ? "0" + str : str;
  }

  @Override
  public String toString() {
    return getIntegerDigits() +
      (getFractionalDigits().length() > 0 ? "." + getFractionalDigits() : "");
  }
}
