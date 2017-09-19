package at.spengergasse.kai17521.sem03.lab13;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.paint.Color;

import java.util.stream.IntStream;

/**
 * @author Samuel Kaiser
 * @since 1/24/2017
 */
public class Parallelogram extends Shape {
  private int base;
  private int height;
  private int side;

  public Parallelogram(Color color, boolean border,
                       int base, int height, int side) {
    super(color, border);
    this.base = base;
    this.height = height;
    this.side = side;
  }

  @Override
  public double perimeter() {
    return 2 * (base + side);
  }

  @Override
  public double area() {
    return base * height;
  }

  @Override
  public int width() {
    return base + height - 1;
  }

  @Override
  public int height() {
    return height;
  }

  @Override
  public Grid grid() {
    final int length = base + height;
    Grid grid = new Grid(length, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < base; x++) {
        char symbol = ' ';
        if (!border || x == 0 || x == base - 1 || y == 0 || y == height - 1) {
          symbol = Grid.SYMBOL;
        }
        grid.set(x + y, height - y - 1, symbol);
      }
    }

    return grid;
  }

  @Override
  public String toString() {
    return String.format("Parallelogram { base: %d, height: %d, side: %d, " +
      "border: %s }", base, height, side, border);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Parallelogram that = (Parallelogram) o;
    return base == that.base && height == that.height && side == that.side
      && border == that.border && (color != null ? color.equals(that.color)
      : that.color == null);

  }

  @Override
  public int hashCode() {
    int result = base;
    result = 31 * result + height;
    result = 31 * result + side;
    result = 31 * result + (border ? 1 : 0);
    result = 31 * result + (color != null ? color.hashCode() : 0);
    return result;
  }
}
