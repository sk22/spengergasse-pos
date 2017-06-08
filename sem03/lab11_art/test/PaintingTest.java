import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 1/9/2017
 */
public class PaintingTest {
  private Painting painting;

  @Before
  public void constructValid() {
    painting = new Painting("Foo", "Bar", LocalDate.of(2017, 1, 9),
      new Painting.Dimensions(192, 108), Painting.Technique.ACRYL);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructInvalid() {
    new Painting(null, null, null, null, null);
  }

  @Test
  public void transportCosts() {
    assertEquals(62.208f, painting.transportCosts(), 0);
  }

  @Test
  public void toStringTest() {
    System.out.println(painting);
  }
}