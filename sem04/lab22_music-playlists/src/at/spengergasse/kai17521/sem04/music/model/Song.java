package at.spengergasse.kai17521.sem04.music.model;

import com.mpatric.mp3agic.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
public class Song implements Serializable {
  private Mp3File mp3;
  private File file;

  private StringProperty title = new SimpleStringProperty();
  private StringProperty artist = new SimpleStringProperty();
  private StringProperty album = new SimpleStringProperty();
  private StringProperty year = new SimpleStringProperty();
  private StringProperty track = new SimpleStringProperty();
  private StringProperty genre = new SimpleStringProperty();
  private StringProperty string = new SimpleStringProperty();

  private ObjectProperty<ID3v1> tag = new SimpleObjectProperty<>();

  public Song(File file) throws IOException  {
    if (!file.exists()) throw new IOException("File does not exist");
    this.file = file;

    final BooleanBinding artistNotGiven =
      tag.isNull().or(artist.isEmpty());

    final StringBinding titleOrName = Bindings.when(title.isNotEmpty())
      .then(title).otherwise(file.getName());

    final StringExpression format = Bindings.format(
      "%s - %s",
      Bindings.when(artist.isNotEmpty()).then(artist)
        .otherwise("Unknown"),
      Bindings.when(title.isNotEmpty()).then(title)
        .otherwise("Unknown")
    );

    string.bind(Bindings.when(artistNotGiven)
      .then(titleOrName).otherwise(format));
  }

  /**
   * Loads the mp3 file into the {@link Song#mp3} property
   * @throws IOException File could not be loaded
   * @throws InvalidDataException File's data is not valid
   * @throws UnsupportedTagException File's tag is not supported
   */
  public void loadTags()
    throws IOException, InvalidDataException, UnsupportedTagException {
    this.mp3 = new Mp3File(file);
    tag.set(getTag());
    if (getTag() == null) return;
    if (getTag().getTitle() != null) setTitle(getTag().getTitle());
    if (getTag().getArtist() != null) setArtist(getTag().getArtist());
    if (getTag().getAlbum() != null) setAlbum(getTag().getAlbum());
    if (getTag().getYear() != null) setYear(getTag().getYear());
    if (getTag().getTrack() != null) setTrack(getTag().getTrack());
    if (getTag().getGenreDescription() != null) {
      setGenre(getTag().getGenreDescription());
    }
  }

  public ID3v1 getTag() {
    if (mp3 == null) return null;
    if (mp3.hasId3v2Tag()) return mp3.getId3v2Tag();
    if (mp3.hasId3v1Tag()) return mp3.getId3v2Tag();
    return null;
  }

  public Mp3File getMp3File() {
    return mp3;
  }

  public String getTitle() {
    return title.get();
  }

  public StringProperty titleProperty() {
    return title;
  }

  public void setTitle(String title) {
    this.title.set(title);
  }

  public String getArtist() {
    return artist.get();
  }

  public StringProperty artistProperty() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist.set(artist);
  }

  public String getAlbum() {
    return album.get();
  }

  public StringProperty albumProperty() {
    return album;
  }

  public void setAlbum(String album) {
    this.album.set(album);
  }

  public String getYear() {
    return year.get();
  }

  public StringProperty yearProperty() {
    return year;
  }

  public void setYear(String year) {
    this.year.set(year);
  }

  public String getTrack() {
    return track.get();
  }

  public StringProperty trackProperty() {
    return track;
  }

  public void setTrack(String track) {
    this.track.set(track);
  }

  public String getGenre() {
    return genre.get();
  }

  public StringProperty genreProperty() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre.set(genre);
  }

  public String getString() {
    return string.get();
  }

  public StringProperty stringProperty() {
    return string;
  }

  public void setString(String string) {
    this.string.set(string);
  }

  public File getFile() {
    return file;
  }

  @Override
  public String toString() {
    if (mp3 == null || getTitle() == null) return file.getName();
    return getTitle() + (getArtist() != null ? " - " + getArtist() : "");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Song song = (Song) o;

    return file != null ? file.equals(song.file) : song.file == null;
  }

  @Override
  public int hashCode() {
    return file != null ? file.hashCode() : 0;
  }
}
