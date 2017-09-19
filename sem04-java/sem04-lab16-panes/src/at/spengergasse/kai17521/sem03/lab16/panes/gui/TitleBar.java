package at.spengergasse.kai17521.sem04.lab16.panes.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;

/**
 * @author Samuel Kaiser
 * @since 2/20/2017
 */
class TitleBar extends TextFlow {
  TitleBar() {
    setPadding(new Insets(10));
    Label label = new Label("Welcome");
    label.setTextFill(Color.WHITE);
    label.setFont(Font.font(30));

    getChildren().add(label);
    setBackground(
      new Background(new BackgroundFill(Color.gray(0.1), null, null))
    );
  }
}
