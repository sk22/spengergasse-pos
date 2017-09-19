package at.spengergasse.kai17521.sem04.lab19.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.io.Serializable;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public abstract class DrawableShape<T extends Shape> implements Serializable {
  String color;
  double x;
  double y;

  DrawableShape(String color, double x, double y) {
    this.color = color;
    this.x = x;
    this.y = y;
  }

  protected T setProperties(T shape) {
    shape.setLayoutX(x);
    shape.setLayoutY(y);
    shape.setFill(Color.web(color));
    return shape;
  }

  public abstract T draw();

  @Override
  public abstract String toString();
}
