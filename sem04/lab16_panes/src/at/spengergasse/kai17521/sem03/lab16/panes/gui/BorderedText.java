package at.spengergasse.kai17521.sem04.lab16.panes.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;

/**
 * @author Samuel Kaiser
 * @since 2/20/2017
 */
class BorderedText extends TextFlow {
  BorderedText() {
    getChildren().add(new Label("Welcome to this window!"));
    setBorder(new Border(new BorderStroke(
      Color.GRAY,
      BorderStrokeStyle.SOLID,
      new CornerRadii(3),
      null
    )));
    setPadding(new Insets(10));
  }
}
