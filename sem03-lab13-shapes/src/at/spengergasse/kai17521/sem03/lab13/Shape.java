package at.spengergasse.kai17521.sem03.lab13;

import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * @author Samuel Kaiser
 * @since 1/23/2017
 */
public abstract class Shape implements Serializable {
  protected boolean border;
  protected Color color;

  public Shape(Color color, boolean border) {
    this.color = color;
    this.border = border;
  }

  public abstract double perimeter();
  public abstract double area();
  public abstract int width();
  public abstract int height();
  public abstract Grid grid();

  public boolean hasBorder() {
    return border;
  }

  public Color getColor() {
    return color;
  }

  public static int compareByArea(Shape a, Shape b) {
    return (int) Math.round(b.area() - a.area());
  }

  public static int compareByPerimeter(Shape a, Shape b) {
    return (int) Math.round(b.perimeter() - a.perimeter());
  }
}
