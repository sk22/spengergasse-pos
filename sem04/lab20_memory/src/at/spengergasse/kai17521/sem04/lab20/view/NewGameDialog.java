package at.spengergasse.kai17521.sem04.lab20.view;

import at.spengergasse.kai17521.sem04.lab20.model.Game;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * @author Samuel Kaiser
 * @since 4/3/2017
 */
public class NewGameDialog extends Dialog<Pair<Integer, Integer>> {
  private TextField rows = new TextField("4");
  private TextField cols = new TextField("4");

  public NewGameDialog() {
    setTitle("New Game");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);

    rows.textProperty().addListener(getValidator(rows.textProperty()));
    cols.textProperty().addListener(getValidator(cols.textProperty()));
    grid.addRow(0, new Label("Rows"), rows);
    grid.addRow(1, new Label("Columns"), cols);

    getDialogPane().setHeaderText("Start a new game");
    getDialogPane().setContent(grid);
    getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    setResultConverter(b -> b == ButtonType.CANCEL ? null : new Pair<>(
      Integer.parseInt(rows.getText()), Integer.parseInt(cols.getText())
    ));
  }


  private ChangeListener<String> getValidator(StringProperty field) {
    return (observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        field.setValue(oldValue);
      }
      Node ok = getDialogPane().lookupButton(ButtonType.OK);
      ok.setDisable(
        rows.getText().isEmpty()
          || cols.getText().isEmpty()
          || !newValue.matches("\\d*[1-9]\\d*")
      );
    };
  }
}
