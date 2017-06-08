package at.spengergasse.kai17521.sem04.lab19.model;

import javafx.scene.shape.Circle;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class DrawableCircle extends DrawableShape<Circle> {
  private double radius;

  public DrawableCircle(String color, double x, double y, double radius) {
    super(color, x, y);
    this.radius = radius;
  }

  @Override
  protected Circle setProperties(Circle shape) {
    super.setProperties(shape);
    shape.setRadius(radius);
    return shape;
  }

  @Override
  public Circle draw() {
    return setProperties(new Circle());
  }

  @Override
  public String toString() {
    return "Circle{" +
      "color=" + color +
      ", x=" + x +
      ", y=" + y +
      ", radius=" + radius +
      '}';
  }
}
