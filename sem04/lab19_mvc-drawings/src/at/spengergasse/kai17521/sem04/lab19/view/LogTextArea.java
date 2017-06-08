package at.spengergasse.kai17521.sem04.lab19.view;

import javafx.scene.control.TextArea;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class LogTextArea extends TextArea {
  public void println(String text) {
    appendText(text + "\n");
  }
}
