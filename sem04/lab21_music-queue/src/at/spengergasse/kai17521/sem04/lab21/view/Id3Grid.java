package at.spengergasse.kai17521.sem04.lab21.view;

import at.spengergasse.kai17521.id3.Id3Field;
import at.spengergasse.kai17521.id3.Id3Tag;
import at.spengergasse.kai17521.id3.Id3Version;
import at.spengergasse.kai17521.id3.fields.Genre;
import at.spengergasse.kai17521.sem04.lab21.model.Song;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Samuel Kaiser
 * @since 4/24/2017
 */
public class Id3Grid extends GridPane {
  private Song song;

  private TextField titleText = new TextField();
  private TextField artistText = new TextField();
  private TextField albumText = new TextField();
  private TextField yearText = new TextField();
  private ComboBox<Genre> genreBox =
    new ComboBox<>(FXCollections.observableArrayList(Genre.values()));
  private TextField pathText = new TextField();

  Id3Grid() {
    setHgap(5);
    setVgap(5);

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
    addRow(5, new Label("Path"), pathText);
  }

  public TextField getTitleText() {
    return titleText;
  }

  public TextField getArtistText() {
    return artistText;
  }

  public TextField getAlbumText() {
    return albumText;
  }

  public TextField getYearText() {
    return yearText;
  }

  public ComboBox<Genre> getGenreBox() {
    return genreBox;
  }

  public TextField getPathText() {
    return pathText;
  }

  public void setSong(Song song) {
    this.song = song;
  }

  public Song getSong() {
    try {
      if (song == null) song = new Song(new File(pathText.getText()));
      song.setFile(new File(pathText.getText()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    song.setTag(Id3Version.V1, new Id3Tag(Arrays.asList(
      new Id3Field(Id3Version.Id3V1FieldMeta.TITLE, getTitleText().getText()),
      new Id3Field(Id3Version.Id3V1FieldMeta.ARTIST, getArtistText().getText()),
      new Id3Field(Id3Version.Id3V1FieldMeta.ALBUM, getAlbumText().getText()),
      new Id3Field(Id3Version.Id3V1FieldMeta.YEAR, getYearText().getText()),
      new Id3Field(Id3Version.Id3V1FieldMeta.GENRE, getGenreBox().getValue())
    )));
    return song;
  }

  public void loadId3Tag() {
    Id3Tag id3Tag = song.getTag(Id3Version.V1);
    titleText.setText(id3Tag.get("TITLE").getValue().toString());
    artistText.setText(id3Tag.get("ARTIST").getValue().toString());
    albumText.setText(id3Tag.get("ALBUM").getValue().toString());
    yearText.setText(id3Tag.get("YEAR").getValue().toString());
    Object genreObject = id3Tag.get("GENRE").getValue() == null
      ? "" : id3Tag.get("GENRE").getValue();
    Genre genre = genreObject instanceof Genre ? (Genre) genreObject : null;
    genreBox.setValue(genre);

    pathText.setText(song.getFile().getAbsolutePath());
  }

  String validate() {
    if (pathText.getText().isEmpty()) {
      return "Path must be given";
    }
    if (!new File(pathText.getText()).exists()) {
      return "File does not exist";
    }
    return null;
  }

  Optional<ButtonType> showAlert(String error) {
    return new Alert(Alert.AlertType.ERROR, error).showAndWait();
  }
}
