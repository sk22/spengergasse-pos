package at.spengergasse.kai17521.sem03.lab13;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 1/27/2017
 */
public class CanvasTest {
  private Canvas canvas;
  private File file = new File("test.ser");

  @Before
  public void add() throws Exception {
    canvas = new Canvas();
    canvas.add(new Parallelogram(null, false, 3, 4, 5));
    canvas.add(new Circle(null, true, 5));
  }

  @Test
  public void write() throws Exception {
    canvas.write(file);
  }

  @Test
  public void read() throws Exception {
    Canvas c = new Canvas();
    c.read(file);
    assertEquals(c, canvas);
  }

  @Test
  public void sort() throws Exception {
    canvas.sort(Shape::compareByArea);
    assertTrue(canvas.get(0).area() > canvas.get(1).area());

    canvas.sort(Shape::compareByPerimeter);
    assertTrue(canvas.get(0).perimeter() > canvas.get(1).perimeter());
  }

  @Test
  public void toStringTest() throws Exception {
    System.out.println(canvas);
  }
}
