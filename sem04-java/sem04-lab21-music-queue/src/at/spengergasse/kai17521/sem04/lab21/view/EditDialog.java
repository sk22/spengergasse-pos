package at.spengergasse.kai17521.sem04.lab21.view;

import at.spengergasse.kai17521.sem04.lab21.model.Song;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * @author Samuel Kaiser
 * @since 4/24/2017
 */
public class EditDialog extends Dialog<Song> {
  public EditDialog(Song song) {
    final ButtonType EDIT = new ButtonType(
      "Edit", ButtonBar.ButtonData.OK_DONE
    );
    setHeaderText("Edit song");
    setTitle("Edit");

    getDialogPane().getButtonTypes().addAll(EDIT, ButtonType.CANCEL);

    Id3Grid id3Grid = new Id3Grid();
    id3Grid.setSong(song);
    id3Grid.loadId3Tag();

    Button editButton = (Button) getDialogPane().lookupButton(EDIT);
    editButton.addEventFilter(ActionEvent.ACTION, event -> {
      String error = id3Grid.validate();
      if (error != null) {
        event.consume();
        id3Grid.showAlert(error);
      }
    });

    setResultConverter(type -> type == EDIT ? id3Grid.getSong() : null);
    getDialogPane().setContent(id3Grid);
  }
}
