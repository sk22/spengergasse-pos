package at.spengergasse.kai17521.sem04.music.view;

import at.spengergasse.kai17521.sem04.music.controller.AppController;
import at.spengergasse.kai17521.sem04.music.model.Song;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import static java.util.stream.Collectors.toList;
import static javafx.scene.input.KeyCode.DELETE;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
class LibraryView extends TitledPane {
  LibraryView(AppController controller) {
    final ListView<Song> listView = new ListView<>(controller.getLibrary());

    listView.setContextMenu(new LibraryContextMenu(controller));
    listView.setCellFactory(lv -> new SongListCell() {
      @Override
      protected void updateItem(Song item, boolean empty) {
        super.updateItem(item, empty);
        setOnMouseClicked(empty ? null : event -> {
          if (event.getClickCount() == 2) controller.addToCurrentPlayist(item);
        });
        setContextMenu(empty ? null :
          new SongContextMenu(controller, item));
      }
    });

    listView.setOnKeyPressed(event -> {
      if (event.getCode().equals(DELETE)) controller.deleteSong(
        listView.getSelectionModel().getSelectedItem()
      );
    });

    final BorderPane borderPane = new BorderPane(listView);

    setContent(borderPane);
    setText("Library");
    setCollapsible(false);
    setPadding(new Insets(10));
  }

  private class SongContextMenu extends ContextMenu {
    SongContextMenu(AppController controller, Song song) {
      final MenuItem add = new MenuItem("Add To Current Playlist");
      final MenuItem edit = new MenuItem("Edit");
      final MenuItem delete = new MenuItem("Delete");
      final Menu addToPlaylist = new Menu("Add To Playlist");

      add.setOnAction(event -> controller.addToCurrentPlayist(song));

      addToPlaylist.getItems().setAll(
        controller.getPlaylistNames().stream()
          .map(name -> {
            final MenuItem item = new MenuItem(name);
            item.setOnAction(event -> controller.addSongToPlaylist(
              song,
              controller.getPlaylistByName(name)
            ));
            return item;
          }).collect(toList())
      );

      edit.setOnAction(event -> new ID3EditDialog(song).show());
      delete.setOnAction(event -> controller.deleteSong(song));

      getItems().addAll(add, edit, delete, addToPlaylist);
    }
  }

  private class LibraryContextMenu extends ContextMenu {
    LibraryContextMenu(AppController controller) {
      final MenuItem add = new MenuItem("Add Songs");

      add.setOnAction(event -> controller.addToLibrary());

      getItems().addAll(add);
    }
  }
}
