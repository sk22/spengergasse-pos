package at.spengergasse.kai17521.sem04.lab21.view;

import at.spengergasse.kai17521.sem04.lab21.controller.Controller;
import at.spengergasse.kai17521.sem04.lab21.model.Song;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

/**
 * @author Samuel Kaiser
 * @since 4/24/2017
 */
public class PlaylistPane extends BorderPane {
  private Controller controller;
  private ListView<Song> list;

  PlaylistPane(Controller controller) {
    this.controller = controller;
    list = new ListView<>(controller.getObservable());
    list.setEditable(true);
    list.setOnMouseClicked(controller.onEditDoubleClick);
    setCenter(list);
    setBottom(makeToolBar());
  }

  public ListView<Song> getList() {
    return list;
  }

  private Node makeToolBar() {
    Button addButton = new Button("Add");
    Button removeButton = new Button("Remove");
    Button editButton = new Button("Edit");
    addButton.setOnAction(controller.onAdd);
    removeButton.setOnAction(controller.onRemove);
    editButton.setOnAction(controller.onEdit);
    return new ToolBar(addButton, removeButton, editButton);
  }
}
