package at.spengergasse.kai17521.sem04.the20160310.gui;

import at.spengergasse.kai17521.sem04.the20160310.data.Contact;
import at.spengergasse.kai17521.sem04.the20160310.data.Gender;
import at.spengergasse.kai17521.sem04.the20160310.data.Relation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

import static at.spengergasse.kai17521.sem04.the20160310.data.Relation.*;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public class ContactEditor extends VBox {
  private TextField name = new TextField();
  private ComboBox<Gender> gender = new ComboBox<>();
  private ToggleGroup relation = new ToggleGroup();
  private DatePicker dateOfBirth = new DatePicker();

  ContactEditor() {
    setAlignment(Pos.CENTER_LEFT);
    setSpacing(5);

    gender.getItems().addAll(Gender.values());

    relation = new ToggleGroup();
    RadioButton friend = new RadioButton(FRIEND.toString());
    friend.setUserData(FRIEND);
    RadioButton family = new RadioButton(FAMILY.toString());
    family.setUserData(FAMILY);
    RadioButton business = new RadioButton(BUSINESS.toString());
    business.setUserData(BUSINESS);
    relation.getToggles().addAll(friend, family, business);

    getChildren().addAll(
      formGroup(
        new Label("Name: "), name,
        new Label("Gender: "), gender,
        new Label("Date of Birth: "), dateOfBirth
      ),
      formGroup(new Label("Relation: "), friend, family, business)
    );
  }

  private FlowPane formGroup(Node... children) {
    FlowPane box = new FlowPane(children);
    box.setHgap(5); box.setVgap(5);
    box.setAlignment(Pos.CENTER_LEFT);
    return box;
  }

  public Contact build() {
    Contact contact = new Contact(
      name.getText(),
      gender.getValue(),
      relation.getSelectedToggle() == null ? null :
        (Relation) relation.getSelectedToggle().getUserData(),
      dateOfBirth.getValue()
    );
    name.clear();
    return contact;
  }
}
