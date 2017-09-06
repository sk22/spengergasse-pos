package at.spengergasse.kai17521.sem04.music.view;

import at.spengergasse.kai17521.sem04.music.controller.AppController;
import at.spengergasse.kai17521.sem04.music.model.Playlist;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
class MainPane extends GridPane {
  MainPane(AppController controller) {
    final PlaylistsView playlistsView = new PlaylistsView(controller);
    final LibraryView libraryView = new LibraryView(controller);

    add(playlistsView, 0, 0, 1, 2);
    add(libraryView, 1, 1, 1, 1);

    GridPane.setHgrow(libraryView, Priority.ALWAYS);
    GridPane.setVgrow(playlistsView, Priority.ALWAYS);
    GridPane.setVgrow(libraryView, Priority.ALWAYS);

    setPlaylistView(controller, controller.getCurrentPlaylist());

    controller.currentPlaylistProperty().addListener((
      (observable, oldValue, newValue) -> setPlaylistView(controller, newValue)
    ));
  }

  private void setPlaylistView(AppController controller, Playlist playlist) {
    final PlaylistView playlistView = new PlaylistView(controller, playlist);
    add(playlistView, 1, 0, 1, 1);
    GridPane.setVgrow(playlistView, Priority.ALWAYS);
  }
}
