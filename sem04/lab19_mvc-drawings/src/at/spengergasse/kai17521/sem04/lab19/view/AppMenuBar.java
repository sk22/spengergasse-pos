package at.spengergasse.kai17521.sem04.lab19.view;

import at.spengergasse.kai17521.sem04.lab19.controller.Handlers;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
class AppMenuBar extends MenuBar {
  AppMenuBar(Handlers handlers) {
    getMenus().add(fileMenu(handlers));
  }

  private Menu fileMenu(Handlers handlers) {
    MenuItem open = new MenuItem("Open...");
    open.setUserData("open");
    MenuItem save = new MenuItem("Save");
    save.setUserData("save");
    MenuItem saveAs = new MenuItem("Save as...");
    saveAs.setUserData("saveAs");

    Menu menu = new Menu("File", null, open, save, saveAs);
    menu.getItems().forEach(i -> i.setOnAction(handlers.fileHandler));
    return menu;
  }
}
