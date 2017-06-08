package at.spengergasse.kai17521.sem04.lab19.view;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class CircleAdder extends ShapeAdder {
  public final TextField xField = new TextField();
  public final TextField yField = new TextField();
  public final TextField radiusField = new TextField();
  public final ColorPicker colorPicker = new ColorPicker();

  CircleAdder() {
    super();
    addRow(0, new Label("X"), xField);
    addRow(1, new Label("Y"), yField);
    addRow(2, new Label("Radius"), radiusField);
    addRow(3, new Label("Color"), colorPicker);
  }
}
