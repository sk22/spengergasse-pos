package at.spengergasse.kai17521.sem04.lab18.task02.gui;

import at.spengergasse.kai17521.sem04.lab18.task02.control.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
class Controls extends HBox {
  Controls(Controller controller) {
    NumericTextField rows = new NumericTextField(3);
    NumericTextField cols = new NumericTextField(2);
    rows.setPrefColumnCount(1);
    cols.setPrefColumnCount(1);
    setHgrow(rows, Priority.SOMETIMES);
    setHgrow(cols, Priority.SOMETIMES);
    setAlignment(Pos.CENTER);

    setSpacing(5);

    Button create = new Button("Create");
    create.setOnAction(event -> controller.create(
      rows.getNumber(), cols.getNumber()
    ));
    getChildren().addAll(
      new Label("Rows:"), rows,
      new Label("Columns:"), cols,
      create
    );

    // initial grid
    controller.create(rows.getNumber(), cols.getNumber());
  }
}
