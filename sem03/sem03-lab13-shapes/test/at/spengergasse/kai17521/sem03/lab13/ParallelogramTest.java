package at.spengergasse.kai17521.sem03.lab13;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 1/27/2017
 */
public class ParallelogramTest {
  private Parallelogram parallelogram =
    new Parallelogram(new Color(1, 0, 0, 1), false, 5, 4, 3);

  @Test
  public void perimeter() throws Exception {
    assertEquals(16, parallelogram.perimeter(), 0.0001);
  }

  @Test
  public void area() throws Exception {
    assertEquals(20, parallelogram.area(), 0.0001);
  }

  @Test
  public void toStringTest() throws Exception {
    System.out.println(parallelogram.toString());
  }

  @Test
  public void filled() throws Exception {
    System.out.println(new Parallelogram(null, false, 4, 7, 3).grid());
  }

  @Test
  public void border() throws Exception {
    System.out.println(new Parallelogram(null, true, 4, 7, 3).grid());
  }

}