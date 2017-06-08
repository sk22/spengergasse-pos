package at.spengergasse.kai17521.sem04.music.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.Serializable;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
public class Playlist implements Serializable {
  private StringProperty name;
  private ObservableList<Song> songs = observableArrayList();

  public Playlist(String name) {
    this.name = new SimpleStringProperty(name);
  }

  public String getName() {
    return name.get();
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public StringProperty nameProperty() {
    return name;
  }

  public ObservableList<Song> getSongs() {
    return songs;
  }

  @Override
  public String toString() {
    return name.get();
  }
}
