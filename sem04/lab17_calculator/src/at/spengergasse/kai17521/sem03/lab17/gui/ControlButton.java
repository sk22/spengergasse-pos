package at.spengergasse.kai17521.sem04.lab17.gui;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * @author Samuel Kaiser
 * @since 2/27/2017
 */
class ControlButton extends Button {
  ControlButton(String text, int size) {
    super(text);
    setPrefSize(60, 60);
    setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    setFont(new Font(size));
  }
  ControlButton(String text) {
    this(text, 25);
  }
}
