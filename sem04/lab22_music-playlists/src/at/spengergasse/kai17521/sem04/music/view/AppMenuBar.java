package at.spengergasse.kai17521.sem04.music.view;

import at.spengergasse.kai17521.sem04.music.controller.AppController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
class AppMenuBar extends MenuBar {
  AppMenuBar(AppController controller) {
    final MenuItem open = new MenuItem("Open");
    open.setOnAction(event -> controller.open());

    final MenuItem save = new MenuItem("Save");
    save.setOnAction(event -> controller.save());

    final MenuItem saveAs = new MenuItem("Save as...");
    saveAs.setOnAction(event -> controller.saveAs());

    final MenuItem addToLibrary = new MenuItem("Add");
    addToLibrary.setOnAction(event -> controller.addToLibrary());

    final MenuItem savePlaylist = new MenuItem("Save Playlist");
    savePlaylist.setOnAction(event -> controller.savePlaylist());

    final MenuItem openPlaylist = new MenuItem("Open Playlist");
    openPlaylist.setOnAction(event -> controller.openPlaylist());

    getMenus().addAll(
      new Menu(
        "File",
        null,
        open,
        save,
        saveAs,
        savePlaylist,
        openPlaylist
      ),
      new Menu("Library", null, addToLibrary)
    );
  }
}
