package at.spengergasse.kai17521.sem03.lab03;

import java.util.Random;

/**
 * Used to process basic arithmetic calculations on a Fraction
 * @author Samuel Kaiser
 * @since 10/3/2016
 */
public class Fraction {
  private int numerator;
  private int denominator;

  /**
   * Initializes with random integers between 1 and 10
   */
  public Fraction() {
    Random random = new Random();
    numerator = random.nextInt(10) + 1;
    denominator = random.nextInt(10) + 1;
  }

  /**
   * Initializes the Fraction with two values
   * @param numerator Numerator
   * @param denominator Denominator
   * @throws IllegalDenominatorException Thrown if the passed denominator is illegal
   */
  public Fraction(int numerator, int denominator) throws IllegalDenominatorException {
    setNumerator(numerator);
    setDenominator(denominator);
  }

  /**
   * Initializes the Fraction with two values that get rounded to the next integers
   * @param numerator Numerator
   * @param denominator Denominator
   * @throws IllegalDenominatorException Thrown if the passed denominator is illegal
   */
  public Fraction(float numerator, float denominator) throws IllegalDenominatorException {
    setNumerator(Math.round(numerator));
    setDenominator(Math.round(denominator));
  }

  /**
   * Initializes with main method args friendly String varargs
   * @param fraction String varargs including two Strings for the numerator and denominator
   * @throws NumberFormatException Thrown if the passed values could not be converted to integers
   * @throws IllegalDenominatorException Thrown if the passed denominator is illegal
   */
  public Fraction(String... fraction) throws NumberFormatException, IllegalDenominatorException {
    try {
      setNumerator(Integer.parseInt(fraction[0]));
      setDenominator(Integer.parseInt(fraction[1]));
    } catch (NumberFormatException nfe) {
      throw new NumberFormatException("Argument 0 and 1 must be Strings "
        + "that are convertible to an integer.");
    }
  }

  /**
   * Applies a Fraction's numerator and denominator to the current Fraction
   * @param fraction Fraction with new values
   * @return Fraction object for chaining
   * @throws IllegalDenominatorException Thrown if the denominator within the passed Fraction is illegal
   */
  public Fraction apply(Fraction fraction) throws IllegalDenominatorException {
    if (fraction != null) {
      setNumerator(fraction.getNumerator());
      setDenominator(fraction.getDenominator());
    }
    return this;
  }

  /**
   * Applies numerator and denominator
   * @param numerator New numerator
   * @param denominator New denominator
   * @return Fraction object for chaining
   * @throws IllegalDenominatorException Thrown if the passed denominator is illegal
   */
  public Fraction apply(int numerator, int denominator) throws IllegalDenominatorException {
    setNumerator(numerator);
    setDenominator(denominator);
    return this;
  }

  /**
   * Sets the numerator
   * @param numerator Numerator
   */
  public void setNumerator(int numerator) {
    this.numerator = numerator;
  }

  /**
   * Sets the denominator
   * @param denominator Denominator, must not be 0
   * @throws IllegalDenominatorException Thrown if 0 is passed
   */
  public void setDenominator(int denominator) throws IllegalDenominatorException {
    if (denominator == 0) throw new IllegalDenominatorException();
    this.denominator = denominator;
  }

  public int getNumerator() {
    return numerator;
  }
  public int getDenominator() {
    return denominator;
  }

  /**
   * Multiplies two Fractions
   * @param a Fraction 1
   * @param b Fraction 2
   * @return Multiplied Fraction
   * @throws IllegalDenominatorException Thrown if the multiplication results in an illegal denominator
   */
  public static Fraction multiply(Fraction a, Fraction b) throws IllegalDenominatorException {
    if (a == null || b == null) return null;
    return new Fraction(
      a.getNumerator() * b.getNumerator(),
      a.getDenominator() * b.getDenominator()
    );
  }

  /**
   * Multiplies another Fraction to the current Fraction
   * @param fraction Fraction to be multiplied with
   * @return Fraction object for chaining
   * @throws IllegalDenominatorException Thrown if the multiplication results in an illegal denominator
   */
  public Fraction multiply(Fraction fraction) throws IllegalDenominatorException {
    return apply(multiply(this, fraction));
  }

  /**
   * Divides two Fractions
   * @param a Fraction 1
   * @param b Fraction 2
   * @return Divided Fraction
   * @throws IllegalDenominatorException Thrown if the division results in an illegal denominator
   */
  public static Fraction divide(Fraction a, Fraction b) throws IllegalDenominatorException {
    if (a == null || b == null) return null;
    return new Fraction(
      (float) a.getNumerator() * b.getDenominator(),
      (float) a.getDenominator() * b.getNumerator()
    );
  }

  /**
   * Divides the current Fraction by another Fraction
   * @param fraction Fraction to be divided by
   * @return Fraction object for chaining
   * @throws IllegalDenominatorException Thrown if the division results in an illegal denominator
   */
  public Fraction divide(Fraction fraction) throws IllegalDenominatorException {
    return apply(divide(this, fraction));
  }

  /**
   * Sums two Fractions
   * @param a Fraction 1
   * @param b Fraction 2
   * @return Summed Fraction
   * @throws IllegalDenominatorException Thrown if the addition results in an illegal denominator
   */
  public static Fraction add(Fraction a, Fraction b) throws IllegalDenominatorException {
    if (a == null || b == null) return null;
    return new Fraction(
      a.getNumerator() * b.getDenominator() + b.getNumerator() * a.getDenominator(),
      a.getDenominator() * b.getDenominator() // common denominator
    ).reduce();
  }

  /**
   * Adds another Fraction to the current Fraction
   * @param fraction Fraction to add
   * @return Fraction object for chaining
   * @throws IllegalDenominatorException Thrown if the addition results in an illegal denominator
   */
  public Fraction add(Fraction fraction) throws IllegalDenominatorException {
    return apply(add(this, fraction));
  }

  /**
   * Subtracts two Fractions
   * @param a Fraction 1
   * @param b Fraction 2
   * @return Subtracted Fraction
   * @throws IllegalDenominatorException Thrown if the subtraction results in an illegal denominator
   */
  public static Fraction subtract(Fraction a, Fraction b) throws IllegalDenominatorException {
    if (a == null || b == null) return null;
    return new Fraction(
      a.getNumerator() * b.getDenominator() - b.getNumerator() * a.getDenominator(),
      a.getDenominator() * b.getDenominator() // common denominator
    ).reduce();
  }

  /**
   * Subtracts another Fraction from the current Fraction
   * @param fraction Fraction to subtract
   * @return Fraction object for chaining
   * @throws IllegalDenominatorException Thrown if the subtraction results in an illegal denominator
   */
  public Fraction subtract(Fraction fraction) throws IllegalDenominatorException {
    return apply(subtract(this, fraction));
  }

  /**
   * Reduces the numerator and the denominator to as small as possible integers.
   * @return Fraction object for chaining
   * @throws IllegalDenominatorException Thrown if the reduction results in an illegal denominator
   */
  public Fraction reduce() throws IllegalDenominatorException {
    return new Reducer(this).reduce();
  }

  /** @return Fraction formatted like "n / d" */
  @Override
  public String toString() {
    return toString(" / ");
  }

  /**
   * @param delimiter CharSequence (e.g. String) that is placed between numerator and denominator
   * @return String based on Fraction with custom delimiter between numerator and denominator
   */
  public String toString(String delimiter) {
    return getNumerator() + delimiter + getDenominator();
  }


  /**
   * Used to reduce a Fraction as much as possible
   */
  public static class Reducer {
    private Fraction fraction;

    /**
     * Initializes the Reducer
     * @param fraction Fraction that should be reduced
     */
    public Reducer(Fraction fraction) {
      this.fraction = fraction;
    }

    /**
     * Executes the reduction. Object that was passed to the constructor is modified.
     * @return Reduced Fraction object
     * @throws IllegalDenominatorException Thrown if the reduction results in an illegal denominator
     */
    public Fraction reduce() throws IllegalDenominatorException {
      recursion(2);
      return fraction;
    }

    private void recursion(int divisor) throws IllegalDenominatorException {
      if (stop(divisor)) return;
      if (again(divisor)) divide(divisor);
      if (!again(divisor)) divisor += 1;
      recursion(divisor);
    }

    private void divide(int divisor) throws IllegalDenominatorException {
      if (!again(divisor)) return;
      fraction.setNumerator( /*(int) Math.ceil( */ fraction.getNumerator() / divisor /* ) */ );
      fraction.setDenominator( /*(int) Math.ceil( */ fraction.getDenominator() / divisor /* ) */ );
    }

    private boolean again(int divisor) {
      return fraction.getNumerator() % divisor == 0 && fraction.getDenominator() % divisor == 0;
    }

    private boolean stop(int divisor) {
      return divisor > fraction.getNumerator() || divisor > fraction.getDenominator();
    }
  }

  /**
   * Thrown if the denominator specified is illegal (has a value of 0)
   */
  public static class IllegalDenominatorException extends Exception {
    private static final long serialVersionUID = 1L;

    private IllegalDenominatorException() {
      super("Denominator must not be 0");
    }
  }
}
