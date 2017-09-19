package at.spengergasse.kai17521.sem04.lab18.task02.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class MemoryGrid extends GridPane {
  MemoryGrid() {
    setHgap(5);
    setVgap(5);
  }

  public void add(Button button, int x, int y) {
    setVgrow(button, Priority.ALWAYS);
    setHgrow(button, Priority.ALWAYS);
    button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    super.add(button, x, y);
  }
}
