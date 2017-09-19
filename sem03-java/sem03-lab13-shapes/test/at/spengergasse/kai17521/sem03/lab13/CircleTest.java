package at.spengergasse.kai17521.sem03.lab13;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 1/24/2017
 */
public class CircleTest {
  private Circle circle = new Circle(new Color(0, 1, 0, 1), false, 5);

  @Test
  public void area() throws Exception {
    assertEquals(78.53981, circle.area(), 0.00001);
  }

  @Test
  public void perimeter() throws Exception {
    assertEquals(31.41592, circle.perimeter(), 0.00001);
  }

  @Test
  public void toStringTest() throws Exception {
    System.out.println(circle.toString());
  }

  @Test
  public void filled() throws Exception {
    System.out.println(new Circle(null, false, 2).grid());
  }

  @Test
  public void border() throws Exception {
    System.out.println(new Circle(null, true, 2).grid());
  }
}
