package at.spengergasse.kai17521.sem04.the20160310.gui;

import at.spengergasse.kai17521.sem04.the20160310.control.TheHandle;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
class AppMenu extends MenuBar {
  private TheHandle handler;

  AppMenu(TheHandle handler) {
    this.handler = handler;
    getMenus().addAll(createFileMenu(), createSortMenu());
  }

  private Menu createFileMenu() {
    Menu menu = new Menu("File");
    MenuItem save = new MenuItem("Save");
    MenuItem load = new MenuItem("Load");
    MenuItem exit = new MenuItem("Exit");
    save.setUserData("save");
    load.setUserData("load");
    exit.setUserData("exit");
    menu.getItems().addAll(save, load, exit);
    menu.getItems().forEach(this::setActionHandler);
    return menu;
  }
  private Menu createSortMenu() {
    Menu menu = new Menu("Sort");
    MenuItem byName = new MenuItem("By Name");
    MenuItem byRelation = new MenuItem("By Relation");
    MenuItem byDateOfBirth = new MenuItem("By Date of Birth");
    byName.setUserData("sortByName");
    byRelation.setUserData("sortByRelation");
    byDateOfBirth.setUserData("sortByDateOfBirth");
    menu.getItems().addAll(byName, byRelation, byDateOfBirth);
    menu.getItems().forEach(this::setActionHandler);
    return menu;
  }

  private void setActionHandler(MenuItem item) {
    item.setOnAction(handler);
  }
}
