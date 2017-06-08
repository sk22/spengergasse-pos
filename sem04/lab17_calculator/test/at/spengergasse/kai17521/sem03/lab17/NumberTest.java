package at.spengergasse.kai17521.sem04.lab17;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 2/28/2017
 */
public class NumberTest {
  @Test
  public void getNumber() throws Exception {
    Number number = new Number("123", 2);
    assertEquals(1.23, number.getNumber(), 0);
  }

  @Test
  public void getNumberString() throws Exception {
    Number number = new Number("123.321");
    assertEquals(123.321, number.getNumber(), 0);
  }

  @Test
  public void toStringTest() throws Exception {
    Number number = new Number("123", 2);
    assertEquals("1.23", number.toString());
  }

  @Test
  public void stringConstructorNoDecimals() throws Exception {
    Number number = new Number("22.0");
    assertEquals("22", number.digits);
    assertEquals(0, number.decimals);
  }

  @Test
  public void stringConstructor() throws Exception {
    Number number = new Number("123.321");
    assertEquals("123321", number.digits);
    assertEquals(3, number.decimals);
  }
}
