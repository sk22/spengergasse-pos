package at.spengergasse.kai17521.sem04.lab21.view;

import at.spengergasse.kai17521.sem04.lab21.controller.Controller;
import at.spengergasse.kai17521.sem04.lab21.model.Song;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * @author Samuel Kaiser
 * @since 4/24/2017
 */
public class AddDialog extends Dialog<Song> {
  private Id3Grid id3Grid = new Id3Grid();

  public AddDialog(Controller controller) {
    final ButtonType ADD = new ButtonType(
      "Add", ButtonBar.ButtonData.OK_DONE
    );
    setHeaderText("Add song");
    setTitle("Add");

    Button fileButton = new Button("Pick File");
    controller.setAddDialog(this);
    fileButton.setOnAction(controller.onPickFile);

    getDialogPane().getButtonTypes().addAll(
      ADD,
      ButtonType.CANCEL
    );

    Button addButton = (Button) getDialogPane().lookupButton(ADD);
    addButton.addEventFilter(ActionEvent.ACTION,event -> {
      String error = id3Grid.validate();
      if (error != null) {
        event.consume();
        id3Grid.showAlert(error);
      }
    });

    setResultConverter(type -> type == ADD ? id3Grid.getSong() : null);

    VBox vBox = new VBox(fileButton, id3Grid);
    VBox.setMargin(fileButton, new Insets(0, 0, 10, 0));
    getDialogPane().setContent(vBox);
  }

  public Id3Grid getId3Grid() {
    return id3Grid;
  }
}
