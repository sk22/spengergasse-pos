package at.spengergasse.kai17521.sem04.lab21.view;

import at.spengergasse.kai17521.sem04.lab21.controller.Controller;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

/**
 * @author Samuel Kaiser
 * @since 4/21/2017
 */
public class RootPane extends BorderPane {
  private Controller controller = new Controller(this);
  private PlaylistPane playlist = new PlaylistPane(controller);
  private Window stage;

  public RootPane(Window stage) {
    this.stage = stage;
    setTop(getMenuBar());
    setCenter(playlist);
  }

  private MenuBar getMenuBar() {
    MenuItem open = new MenuItem("Open");
    MenuItem save = new MenuItem("Save");
    MenuItem saveAs = new MenuItem("Save as");
    open.setOnAction(controller.open);
    save.setOnAction(controller.save);
    saveAs.setOnAction(controller.saveAs);

    Menu fileMenu = new Menu("File");
    fileMenu.getItems().addAll(open, save, saveAs);
    return new MenuBar(fileMenu);
  }

  public PlaylistPane getPlaylist() {
    return playlist;
  }

  public Window getStage() {
    return stage;
  }
}
