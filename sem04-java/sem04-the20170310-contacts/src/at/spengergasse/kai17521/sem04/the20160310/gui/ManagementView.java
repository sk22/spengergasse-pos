package at.spengergasse.kai17521.sem04.the20160310.gui;

import at.spengergasse.kai17521.sem04.the20160310.data.Management;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public class ManagementView extends VBox {
  private ContactEditor editor = new ContactEditor();
  private VBox contactsView = new VBox(10);
  private Management management;

  ManagementView(Management management, EventHandler<ActionEvent> handler) {
    this.management = management;
    setPadding(new Insets(10));
    Button addButton = new Button("Add");
    addButton.setUserData("add");
    addButton.setDefaultButton(true);
    addButton.setOnAction(handler);

    HBox adder = new HBox(15, editor, addButton);
    HBox.setHgrow(editor, Priority.ALWAYS);
    editor.setMaxWidth(Double.MAX_VALUE);
    setMargin(contactsView, new Insets(15, 0, 0, 0));
    getChildren().addAll(adder, contactsView);
  }

  public ContactEditor getContactEditor() {
    return editor;
  }

  public VBox getContactsView() {
    return contactsView;
  }

  public void reload() {
    getContactsView().getChildren().clear();
    management.forEach(
      contact -> getContactsView().getChildren().add(new ContactView(contact))
    );
  }
}
