package at.spengergasse.kai17521.sem04.lab16.panes.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * @author Samuel Kaiser
 * @since 2/20/2017
 */
class RandomControls extends VBox {
  RandomControls() {
    setSpacing(10);

    TextField textField = new TextField("f****n awesome");
    Label label = new Label("How are you?");

    HBox howAreYou = new HBox(label, textField);
    howAreYou.setAlignment(Pos.CENTER_LEFT);
    HBox.setMargin(label, new Insets(0, 10, 0, 0));
    HBox.setHgrow(textField, Priority.ALWAYS);

    Slider slider = new Slider(0, 1, 1);

    ToggleGroup group = new ToggleGroup();

    RadioButton english = new RadioButton("English");
    english.setToggleGroup(group);
    RadioButton german = new RadioButton("German");
    german.setToggleGroup(group);
    HBox options = new HBox(english, german);
    options.setSpacing(10);

    getChildren().addAll(howAreYou, slider, options);
  }
}
