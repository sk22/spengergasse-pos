package at.spengergasse.kai17521.sem04.lab21.controller;

import at.spengergasse.kai17521.sem04.lab21.model.Song;
import at.spengergasse.kai17521.sem04.lab21.model.SongList;
import at.spengergasse.kai17521.sem04.lab21.view.AddDialog;
import at.spengergasse.kai17521.sem04.lab21.view.EditDialog;
import at.spengergasse.kai17521.sem04.lab21.view.Id3Grid;
import at.spengergasse.kai17521.sem04.lab21.view.RootPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import javax.jnlp.FileContents;
import javax.swing.*;
import java.io.*;

/**
 * @author Samuel Kaiser
 * @since 4/24/2017
 */
public class Controller {
  private RootPane root;
  private ObservableList<Song> songs = FXCollections.observableArrayList();
  private AddDialog addDialog;
  private File opened;

  private final static FileChooser.ExtensionFilter EXTENSION_FILTER =
    new FileChooser.ExtensionFilter(
      "Serialized Song List", "*.ser"
    );

  public Controller(RootPane root) {
    this.root = root;
  }

  private RootPane getRoot() {
    return root;
  }

  public void setAddDialog(AddDialog addDialog) {
    this.addDialog = addDialog;
  }

  public ObservableList<Song> getObservable() {
    return songs;
  }

  public EventHandler<ActionEvent> onAdd = event ->
    new AddDialog(this).showAndWait().ifPresent(songs::add);

  public EventHandler<ActionEvent> onRemove = event -> {
    ListView<Song> listView = getRoot().getPlaylist().getList();
    Song selectedSong = listView.getSelectionModel().getSelectedItem();
    songs.remove(selectedSong);
  };

  public EventHandler<MouseEvent> onEditDoubleClick = event -> {
    if (event.getClickCount() == 2) edit();
  };

  public EventHandler<ActionEvent> onEdit = event -> edit();

  private void edit() {
    ListView<Song> listView = getRoot().getPlaylist().getList();
    Song song = listView.getSelectionModel().getSelectedItem();
    if (song == null) return;

    int index = songs.indexOf(song);
    new EditDialog(song).showAndWait()
      .ifPresent(newSong -> songs.set(index, newSong));
  }

  public EventHandler<ActionEvent> onPickFile = event -> {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open song");
    File file = fileChooser.showOpenDialog(getRoot().getStage());
    if (file == null) return;
    Id3Grid id3Grid = addDialog.getId3Grid();
    try {
      id3Grid.setSong(new Song(file));
      id3Grid.loadId3Tag();
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public EventHandler<ActionEvent> save = event -> {
    if (opened == null) saveAs();
    else save();
  };

  public EventHandler<ActionEvent> saveAs = event -> saveAs();

  public EventHandler<ActionEvent> open = event -> open();

  private void save() {
    SongList songList = new SongList();
    songList.addAll(songs);

    try {
      ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(opened)
      );
      oos.writeObject(songList);
    } catch (IOException e) {
      handleException(e);
    }
  }

  private void saveAs() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(EXTENSION_FILTER);
    fileChooser.setTitle("Save as");
    File file = fileChooser.showSaveDialog(getRoot().getStage());
    if (file == null) return;
    opened = file;
    save();
  }

  private void open() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(EXTENSION_FILTER);
    fileChooser.setTitle("Open");
    File file = fileChooser.showOpenDialog(getRoot().getStage());
    if (file == null) return;
    opened = file;

    try {
      ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream(opened)
      );
      Object read = ois.readObject();
      if (!(read instanceof SongList)) return;
      SongList readList = (SongList) read;
      songs.clear();
      songs.addAll(readList);
    } catch (Exception e) {
      handleException(e);
    }
  }

  private void handleException(Exception e) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An Exception occurred");
    alert.setContentText(e.toString());
    alert.showAndWait();
  }
}
