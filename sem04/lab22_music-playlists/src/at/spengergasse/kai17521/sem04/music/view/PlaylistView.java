package at.spengergasse.kai17521.sem04.music.view;

import at.spengergasse.kai17521.sem04.music.controller.AppController;
import at.spengergasse.kai17521.sem04.music.model.Playlist;
import at.spengergasse.kai17521.sem04.music.model.Song;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static javafx.geometry.Pos.CENTER_LEFT;
import static javafx.scene.input.KeyCode.DELETE;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
class PlaylistView extends TitledPane {
  PlaylistView(AppController controller, Playlist playlist) {
    final ListView<Song> listView = new ListView<>();
    listView.setCellFactory(lv -> new SongListCell() {
      @Override
      protected void updateItem(Song item, boolean empty) {
        super.updateItem(item, empty);
        setContextMenu(empty ? null :
          new PlaylistContextMenu(controller, item));
      }
    });
    listView.setOnKeyPressed(event -> {
      if (event.getCode().equals(DELETE)) controller.removeFromPlaylist(
        listView.getSelectionModel().getSelectedItem()
      );
    });
    listView.setItems(playlist.getSongs());

    final BorderPane borderPane = new BorderPane(listView);

    textProperty().bind(playlist.nameProperty());
    setContent(borderPane);
    setCollapsible(false);
    setPadding(new Insets(10));
  }

  private class PlaylistContextMenu extends ContextMenu {
    PlaylistContextMenu(AppController controller, Song song) {
      final MenuItem edit = new MenuItem("Edit");
      final MenuItem remove = new MenuItem("Remove");

      edit.setOnAction(event -> new ID3EditDialog(song).show());
      remove.setOnAction(event -> controller.removeFromPlaylist(song));

      getItems().addAll(edit, remove);
    }
  }
}
