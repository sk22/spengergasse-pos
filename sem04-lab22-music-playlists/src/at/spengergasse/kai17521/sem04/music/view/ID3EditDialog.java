package at.spengergasse.kai17521.sem04.music.view;

import at.spengergasse.kai17521.sem04.music.model.Song;
import com.mpatric.mp3agic.BaseException;
import com.mpatric.mp3agic.ID3v1Genres;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Window;

import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.ButtonType.OK;

/**
 * @author Samuel Kaiser
 * @since 5/11/2017
 */
class ID3EditDialog extends Alert {
  private static final ButtonType RESET = new ButtonType("Reset", ButtonBar.ButtonData.LEFT);

  ID3EditDialog(Song song) {
    super(AlertType.NONE);

    getButtonTypes().addAll(OK, RESET);

    Window window = getDialogPane().getScene().getWindow();
    window.setOnCloseRequest(event -> window.hide());

    final Button reset = (Button) getDialogPane().lookupButton(RESET);
    reset.addEventFilter(ActionEvent.ACTION, event -> {
      event.consume();
      try {
        song.loadTags();
      } catch (Exception e) {
        Alert alert = new Alert(ERROR, e.toString());
        alert.setHeaderText("Failed to reset tags");
        alert.show();
      }
    });

    setTitle("Edit \"" + song.toString() + "\"");
    setHeaderText("Edit metadata");
    getDialogPane().setContent(new ID3Grid(song));
  }

  class ID3Grid extends GridPane {
    ID3Grid(Song song) {
      setHgap(5);
      setVgap(5);

      TextField titleText = new TextField();
      titleText.textProperty().bindBidirectional(song.titleProperty());
      TextField artistText = new TextField();
      artistText.textProperty().bindBidirectional(song.artistProperty());
      TextField albumText = new TextField();
      albumText.textProperty().bindBidirectional(song.albumProperty());
      TextField yearText = new TextField();
      yearText.textProperty().bindBidirectional(song.yearProperty());
      ComboBox<String> genreBox = new ComboBox<>(
        FXCollections.observableArrayList(ID3v1Genres.GENRES)
      );
      genreBox.getSelectionModel().select(song.getGenre());
      genreBox.getSelectionModel().selectedItemProperty().addListener(
        ((observable, oldValue, newValue) -> {
          song.setGenre(newValue);
        })
      );

      setPrefWidth(400);
      genreBox.setMaxWidth(Double.MAX_VALUE);

      setHgrow(titleText, Priority.ALWAYS);
      setHgrow(artistText, Priority.ALWAYS);
      setHgrow(albumText, Priority.ALWAYS);
      setHgrow(yearText, Priority.ALWAYS);
      setHgrow(genreBox, Priority.ALWAYS);

      addRow(0, new Label("Title"), titleText);
      addRow(1, new Label("Artist"), artistText);
      addRow(2, new Label("Album"), albumText);
      addRow(3, new Label("Year"), yearText);
      addRow(4, new Label("Genre"), genreBox);
    }
  }
}
