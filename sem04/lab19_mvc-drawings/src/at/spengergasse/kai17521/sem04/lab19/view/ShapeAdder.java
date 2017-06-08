package at.spengergasse.kai17521.sem04.lab19.view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public abstract class ShapeAdder extends GridPane {
  ShapeAdder() {
    setHgap(5);
    setVgap(5);
    setPadding(new Insets(10));
  }
}
