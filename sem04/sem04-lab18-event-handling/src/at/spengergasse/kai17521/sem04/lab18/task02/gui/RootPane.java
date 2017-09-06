package at.spengergasse.kai17521.sem04.lab18.task02.gui;

import at.spengergasse.kai17521.sem04.lab18.task02.control.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class RootPane extends BorderPane {
  public RootPane() {
    setPrefSize(500, 600);
    MemoryGrid grid = new MemoryGrid();
    setPadding(new Insets(10));
    TextArea log = new TextArea();
    log.setPrefRowCount(7);
    log.setEditable(false);
    Controller controller = new Controller(grid, log);
    setMargin(grid, new Insets(10, 0, 10, 0));
    setTop(new Controls(controller));
    setCenter(grid);
    setBottom(log);
  }
}
