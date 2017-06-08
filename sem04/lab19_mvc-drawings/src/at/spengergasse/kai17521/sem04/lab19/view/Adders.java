package at.spengergasse.kai17521.sem04.lab19.view;

import at.spengergasse.kai17521.sem04.lab19.controller.Handlers;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class Adders extends VBox {
  private Accordion accordion = new Accordion();

  Adders(Handlers handlers) {
    TitledPane rectangleAdder = new TitledPane(
      "Rectangle", new RectangleAdder()
    );
    accordion.getPanes().addAll(
      rectangleAdder,
      new TitledPane("Circle", new CircleAdder())
    );
    accordion.setExpandedPane(rectangleAdder);

    Button addButton = new Button("Add");
    addButton.setOnAction(handlers.addHandler);

    Button clearButton = new Button("Clear");
    clearButton.setOnAction(handlers.clearHandler);

    setVgrow(accordion, Priority.ALWAYS);
    ToolBar toolBar = new ToolBar(addButton, clearButton);
    getChildren().addAll(accordion, toolBar);
  }

  public ShapeAdder getCurrent() {
    Node current = accordion.getExpandedPane().getContent();
    if (!(current instanceof ShapeAdder)) return null;
    return (ShapeAdder) current;
  }
}
