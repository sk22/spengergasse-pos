package at.spengergasse.kai17521.sem04.music.controller;

import at.spengergasse.kai17521.sem04.music.model.AppState;
import at.spengergasse.kai17521.sem04.music.model.AppStateImage;
import at.spengergasse.kai17521.sem04.music.model.Playlist;
import at.spengergasse.kai17521.sem04.music.model.Song;
import com.mpatric.mp3agic.BaseException;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.List;

import static at.spengergasse.kai17521.sem04.music.model.AppState.EXTENSION_FILTER;
import static at.spengergasse.kai17521.sem04.music.model.AppState.EXTENSION_FILTER_PLAYLIST;
import static java.util.stream.Collectors.toList;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.ButtonType.NO;
import static javafx.scene.control.ButtonType.YES;
import static javafx.stage.FileChooser.*;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
public class AppController {
  private AppState state = new AppState();
  private Window window;
  private File openedFile;


  public AppController(Window window) {
    this.window = window;
    openedFile = new File("state.ser");
    doOpen(openedFile);
  }

  public Window getWindow() {
    return window;
  }

  public void save() {
    if (openedFile == null) {
      saveAs();
    } else {
      doSave(openedFile);
    }
  }

  public void saveAs() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(EXTENSION_FILTER);
    final File file = fileChooser.showSaveDialog(window);
    if (file != null) {
      doSave(file);
      openedFile = (file);
    }
  }

  private void doSave(File file) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(file)
      );
      oos.writeObject(new AppStateImage(state));
    } catch (IOException e) {
      new Alert(ERROR, e.toString()).show();
    }
  }

  public void open() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(EXTENSION_FILTER);
    final File file = fileChooser.showOpenDialog(window);
    if (file != null) {
      doOpen(file);
      openedFile = file;
    }
  }

  private void doOpen(File file) {
    try {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
      Object read = ois.readObject();
      if (!(read instanceof AppStateImage)) {
        throw new ReflectiveOperationException("Invalid class.");
      }
      AppStateImage image = (AppStateImage) read;
      openedFile = file;
      state.apply(image);
    } catch (Exception e) {
      new Alert(ERROR, e.toString()).show();
    }
  }

  public ObservableList<Song> getLibrary() {
    return state.getLibrary();
  }

  public ObservableList<Playlist> getPlaylists() {
    return state.getPlaylists();
  }

  public Property<Playlist> currentPlaylistProperty() {
    return state.currentPlaylistProperty();
  }

  public Playlist getCurrentPlaylist() {
    return state.getCurrentPlaylist();
  }

  public void showPlaylist(Playlist playlist) {
    state.setCurrentPlaylist(playlist);
  }

  public void addToLibrary(File file) {
    if (file == null) return;
    if (!file.getName().matches(".*\\.mp3")) {
      final Alert alert = new Alert(ERROR, "File is not an mp3 file");
      alert.setHeaderText("File could not be added");
      alert.show();
      return;
    }

    try {
      Song song = new Song(file);
      song.loadTags();
      getLibrary().add(song);
    } catch (IOException ioe) {
      final Alert alert = new Alert(ERROR, ioe.toString());
      alert.setHeaderText("Failed to add the specified song");
      alert.show();
    } catch (BaseException e) {
      System.err.println(e.toString());
    }
  }

  public void addToCurrentPlayist(Song item) {
    getCurrentPlaylist().getSongs().add(item);
  }

  public void deleteSong(Song song) {
    if (song == null) return;
    Alert confirmation = new Alert(
      CONFIRMATION,
      "Are you sure you want to delete \"" + song.toString() + "\"?",
      YES,
      NO
    );
    confirmation.showAndWait().filter(type -> type.equals(ButtonType.YES))
      .ifPresent(type -> {
        getPlaylists().forEach(playlist -> playlist.getSongs().removeAll(song));
        getLibrary().remove(song);
      });;
  }

  public void removeFromPlaylist(Song song) {
    getCurrentPlaylist().getSongs().remove(song);
  }

  public List<String> getPlaylistNames() {
    return getPlaylists().stream().map(Playlist::toString).collect(toList());
  }

  public Playlist getPlaylistByName(String name) {
    return getPlaylists().stream()
      .filter(playlist -> playlist.getName().equals(name))
      .findFirst().orElse(null);
  }

  public void addSongToPlaylist(Song song, Playlist playlist) {
    playlist.getSongs().add(song);
  }

  private String getNewPlaylistName(int i) {
    String name = i > 0 ? "New Playlist " + i : "New Playlist";
    return (getPlaylistByName(name) != null)
      ? getNewPlaylistName(i + 1) : name;
  }

  public void addPlaylist() {
    getPlaylists().add(new Playlist(getNewPlaylistName(0)));
  }

  public void deletePlaylist(Playlist playlist) {
    getPlaylists().remove(playlist);
  }

  public void renamePlaylist(Playlist playlist, String name) {
    playlist.setName(name);
  }

  public void addToLibrary() {
    final FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new ExtensionFilter(
      "MP3 files", "*.mp3"
    ));

    final List<File> files = fileChooser
      .showOpenMultipleDialog(getWindow());
    if (files != null) files.forEach(this::addToLibrary);
  }

  public void savePlaylist() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(EXTENSION_FILTER_PLAYLIST);
    final File file = fileChooser.showSaveDialog(window);
    if (file != null) doPlaylistSave(file);
  }

  private void doPlaylistSave(File file) {
    try {
      final PrintWriter writer = new PrintWriter(new FileWriter(file));
      getCurrentPlaylist().getSongs().forEach(song ->
        writer.println(song.getFile().getPath()));
      writer.close();
    } catch (IOException ioe) {
      handleException(ioe);
    }
  }

  public void openPlaylist() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(EXTENSION_FILTER_PLAYLIST);
    final File file = fileChooser.showOpenDialog(window);
    if (file != null) doPlaylistOpen(file);
  }

  private void doPlaylistOpen(File file) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      final List<Song> songs = reader.lines().map(line -> {
        try {
          return new Song(new File(line));
        } catch (IOException ioe) {
          handleException(ioe);
          return null;
        }
      }).collect(toList());
      Playlist playlist = new Playlist(file.getName());
      playlist.getSongs().addAll(songs);
      getPlaylists().add(playlist);
    } catch (IOException ioe) {
      handleException(ioe);
    }
  }

  public static void handleException(Exception e) {
    new Alert(ERROR, e.toString()).show();
  }
}
