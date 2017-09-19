import at.spengergasse.kai17521.sem03.lab03.Fraction;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Samuel Kaiser
 * @since 10/3/2016
 */
public class FractionTest {
  private Fraction fraction;

  @Before
  public void construct() throws Exception {
    fraction = new Fraction(4, 3);
    System.out.printf("\n~ %s\n", fraction);
  }

  @Test
  public void constructStrings() throws Exception {
    System.out.println("Constructing the Fraction from String args");
    fraction = new Fraction("3", "2");
    assert fraction.getNumerator() == 3;
    assert fraction.getDenominator() == 2;
  }

  @Test
  public void constructRandom() throws Exception {
    fraction = new Fraction();
    System.out.println("Constructing a random Fraction");
    assert fraction.getNumerator() <= 10 && fraction.getNumerator() > 0;
    assert fraction.getDenominator() <= 10 && fraction.getDenominator() > 0;
    System.out.println(fraction);
  }

  @Test
  public void apply() throws Exception {
    System.out.println("Applying another Fraction's data to the object");
    fraction.apply(new Fraction(3, 5));
    assert fraction.getNumerator() == 3;
    assert fraction.getDenominator() == 5;
  }

  @Test
  public void setNumerator() throws Exception {
    System.out.println("Set numerator");
    fraction.setNumerator(4);
    assert fraction.getNumerator() == 4;
  }

  @Test
  public void setDenominator() throws Exception {
    System.out.println("Set denominator");
    fraction.setNumerator(8);
    assert fraction.getNumerator() == 8;
  }

  @Test
  public void getNumerator() throws Exception {
    System.out.println("Get numerator");
    assert fraction.getNumerator() == 4;
  }

  @Test
  public void getDenominator() throws Exception {
    System.out.println("Get dominator");
    assert fraction.getDenominator() == 3;
  }

  @Test
  public void multiplyWith() throws Exception {
    System.out.printf("(%s) * (2 / 2) = ", fraction);
    fraction.multiply(new Fraction(2, 2));
    System.out.println(fraction);
    assert fraction.getNumerator() == 8;
    assert fraction.getDenominator() == 6;
  }

  @Test
  public void multiply() throws Exception {
    System.out.println("(1 / 2) * (2 / 1) = 2 / 2");
    Fraction multiplied = Fraction.multiply(
      new Fraction(1, 2),
      new Fraction(2, 1)
    );
    assert multiplied.getNumerator() == 2;
    assert multiplied.getDenominator() == 2;
  }
  @Test
  public void divideBy() throws Exception {
    System.out.println("(4 / 3) / (2 / 2) = 8 / 6");
    fraction.divide(new Fraction(2, 2));
    assert fraction.getNumerator() == 8;
    assert fraction.getDenominator() == 6;
  }

  @Test
  public void divide() throws Exception {
    System.out.println("(1 / 2) / (2 / 1) =  1 / 4");
    Fraction divided = Fraction.divide(
      new Fraction(1, 2),
      new Fraction(2, 1)
    );
    assert divided.getNumerator() == 1;
    assert divided.getDenominator() == 4;
  }

  @Test
  public void reduce() throws Exception {
    System.out.println("1920 / 1080 reduced: 16 / 9");
    fraction.setNumerator(1920);
    fraction.setDenominator(1080);
    fraction.reduce();
    assert fraction.getNumerator() == 16;
    assert fraction.getDenominator() == 9;
  }

  @Test
  public void add() throws Exception {
    System.out.println("(7 / 8) + (8 / 7) = 113 / 56");
    Fraction sum = Fraction.add(new Fraction(7, 8), new Fraction(8, 7));
    assert sum.getNumerator() == 113;
    assert sum.getDenominator() == 56;
  }

  @Test
  public void addTo() throws Exception {
    System.out.println("(1 / 2) + (3 / 4) + (5 / 6) + (7 / 8) = 71 / 24");
    Fraction sum = new Fraction(1, 2)
      .add(new Fraction(3, 4))
      .add(new Fraction(5, 6))
      .add(new Fraction(7, 8));
    assert sum.getNumerator() == 71;
    assert sum.getDenominator() == 24;
  }

  @Test
  public void subtract() throws Exception {
    System.out.println("(8 / 7) - (7 / 8) = 15 / 56");
    Fraction sum = Fraction.subtract(new Fraction(8, 7), new Fraction(7, 8));
    assert sum.getNumerator() == 15;
    assert sum.getDenominator() == 56;
  }

  @Test
  public void subtractFrom() throws Exception {
    System.out.println("(8 / 7) - (6 / 5) - (4 / 3) - (2 / 1) = -356 / 105");
    Fraction difference = new Fraction(8, 7)
      .subtract(new Fraction(6, 5))
      .subtract(new Fraction(4, 3))
      .subtract(new Fraction(2, 1));
    assert difference.getNumerator() == -356;
    assert difference.getDenominator() == 105;
  }


  @Test
  public void nullParam() throws Exception {
    System.out.println("No Exception is thrown");

    int oldNumerator = fraction.getNumerator();
    int oldDenominator = fraction.getDenominator();

    assert fraction.add(null).getDenominator() == oldDenominator;
    assert fraction.subtract(null).getNumerator() == oldNumerator;
    assert fraction.multiply(null).getDenominator() == oldDenominator;
    assert fraction.divide(null).getNumerator() == oldNumerator;

    Fraction.add(new Fraction(2, 3), null);
    Fraction.divide(null, new Fraction(3, 5));
    Fraction.multiply(null, null);
    Fraction.divide(null, null);
  }

  @Test(expected = Fraction.IllegalDenominatorException.class)
  public void zeroDenominator() throws Exception {
    System.out.println("IllegalDenominatorException thrown");
    fraction.setDenominator(0);
    System.out.println(fraction);
  }
}