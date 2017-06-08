package at.spengergasse.kai17521.sem04.lab19.model;

import javafx.scene.shape.Rectangle;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class DrawableRectangle extends DrawableShape<Rectangle> {
  private double width;
  private double height;

  public DrawableRectangle(
    String color, double x, double y, double width, double height
  ) {
    super(color, x, y);
    this.width = width;
    this.height = height;
  }

  @Override
  protected Rectangle setProperties(Rectangle shape) {
    super.setProperties(shape);
    shape.setWidth(width);
    shape.setHeight(height);
    return shape;
  }

  @Override
  public Rectangle draw() {
    return setProperties(new Rectangle());
  }

  @Override
  public String toString() {
    return "Rectangle{" +
      "color=" + color +
      ", x=" + x +
      ", y=" + y +
      ", width=" + width +
      ", height=" + height +
      '}';
  }
}
