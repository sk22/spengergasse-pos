package at.spengergasse.kai17521.sem04.music.model;

import at.spengergasse.kai17521.sem04.music.controller.AppController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static javafx.collections.FXCollections.observableArrayList;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
public class AppState implements Serializable {
  public transient static final ExtensionFilter EXTENSION_FILTER =
    new ExtensionFilter("State", "*.ser");
  public transient static final ExtensionFilter EXTENSION_FILTER_PLAYLIST =
    new ExtensionFilter("m3u playlist", "*.m3u");

  private ObservableList<Song> library = observableArrayList();
  private ObservableList<Playlist> playlists = observableArrayList();

  private transient Property<Playlist> currentPlaylist = new SimpleObjectProperty<>();

  public AppState() {
    if (currentPlaylist.getValue() == null) {
      final Playlist initial = new Playlist("Queue");
      currentPlaylist.setValue(initial);
      playlists.add(initial);
    }
  }

  public ObservableList<Song> getLibrary() {
    return library;
  }

  public ObservableList<Playlist> getPlaylists() {
    return playlists;
  }

  public Property<Playlist> currentPlaylistProperty() {
    return currentPlaylist;
  }

  public Playlist getCurrentPlaylist() {
    return currentPlaylist.getValue();
  }

  public void setCurrentPlaylist(Playlist playlist) {
    this.currentPlaylist.setValue(playlist);
  }

  public void apply(AppStateImage image) {
    playlists.clear();
    library.clear();

    final Map<File, Song> songs = new HashMap<>();
    image.library.forEach(file -> {
      try {
        Song song = new Song(file);
        song.loadTags();
        songs.put(file, song);
        getLibrary().add(song);
      } catch (Exception ioe) {
        AppController.handleException(ioe);
      }
    });

    image.playlists.forEach(playlistImage -> {
      Playlist playlist = new Playlist(playlistImage.name);
      playlist.getSongs().setAll(playlistImage.songs.stream()
        .map(songs::get).collect(toList()));
      getPlaylists().add(playlist);
    });

    setCurrentPlaylist(playlists.get(0));
  }
}
