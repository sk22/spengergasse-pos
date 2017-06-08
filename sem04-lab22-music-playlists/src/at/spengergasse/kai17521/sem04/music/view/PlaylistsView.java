package at.spengergasse.kai17521.sem04.music.view;

import at.spengergasse.kai17521.sem04.music.controller.AppController;
import at.spengergasse.kai17521.sem04.music.model.Playlist;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
class PlaylistsView extends ListView<Playlist> {
  PlaylistsView(AppController controller) {
    setItems(controller.getPlaylists());
    setOnKeyPressed(event -> {
      if (!event.getCode().equals(KeyCode.ENTER)) return;
      Playlist selected = getSelectionModel().getSelectedItem();
      if (selected == null) return;
      controller.showPlaylist(getSelectionModel().getSelectedItem());
    });

    setContextMenu(new PlaylistsContextMenu(controller));

    setCellFactory(listView -> new ListCell<Playlist>() {
      @Override
      protected void updateItem(Playlist item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
          textProperty().bind(item.nameProperty());
        } else {
          textProperty().unbind();
          setText(null);
        }
        setOnMouseClicked(empty ? null : event -> {
          if (event.getClickCount() == 2) controller.showPlaylist(item);
        });
        setContextMenu(empty ? null :
          new PlaylistContextMenu(controller, item));
      }
    });
  }

  private class PlaylistsContextMenu extends ContextMenu {
    PlaylistsContextMenu(AppController controller) {
      final MenuItem add = new MenuItem("Add Playlist");

      add.setOnAction(event -> controller.addPlaylist());

      getItems().addAll(add);
    }
  }

  private class PlaylistContextMenu extends ContextMenu {
    PlaylistContextMenu(AppController controller, Playlist playlist) {
      final MenuItem rename = new MenuItem("Rename");
      final MenuItem delete = new MenuItem("Delete");

      rename.setOnAction(event -> {
        TextInputDialog input = new TextInputDialog(playlist.getName());
        input.setTitle("Rename Playlist");
        input.setHeaderText("Enter a new name");
        input.showAndWait().ifPresent(name ->
          controller.renamePlaylist(playlist, name)
        );
      });

      delete.setOnAction(event -> controller.deletePlaylist(playlist));

      getItems().addAll(rename, delete);
    }
  }
}
