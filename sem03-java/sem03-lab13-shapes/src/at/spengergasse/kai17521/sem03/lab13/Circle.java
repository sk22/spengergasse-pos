package at.spengergasse.kai17521.sem03.lab13;

import javafx.scene.paint.Color;

/**
 * @author Samuel Kaiser
 * @since 1/24/2017
 */
public class Circle extends Shape {
  private int radius;

  public Circle(Color color, boolean border, int radius) {
    super(color, border);
    this.radius = radius;
  }

  @Override
  public double perimeter() {
    return 2 * radius * Math.PI;
  }

  @Override
  public double area() {
    return Math.PI * Math.pow(radius, 2);
  }

  @Override
  public int width() {
    return radius * 2;
  }

  @Override
  public int height() {
    return width();
  }

  @Override
  public Grid grid() {
    return new Grid(radius * 2)
      .set(radius, radius, border ? '\u25CB' : '\u25CF');
    // ¯\_(ツ)_/¯
  }

  @Override
  public String toString() {
    return String.format("Circle { radius: %d, border: %s }", radius, border);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Circle circle = (Circle) o;
    return radius == circle.radius && border == circle.border
      && (color != null ? color.equals(circle.color) : circle.color == null);

  }

  @Override
  public int hashCode() {
    int result = radius;
    result = 31 * result + (border ? 1 : 0);
    result = 31 * result + (color != null ? color.hashCode() : 0);
    return result;
  }
}
