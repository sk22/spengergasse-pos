package at.spengergasse.kai17521.sem04.lab18.task02.gui;

import javafx.scene.control.TextField;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class NumericTextField extends TextField {
  NumericTextField(int value) {
    super(String.valueOf(value));
  }

  @Override
  public void replaceText(int start, int end, String text) {
    if (validate(text)) {
      super.replaceText(start, end, text);
    }
  }

  @Override
  public void replaceSelection(String text) {
    if (validate(text)) {
      super.replaceSelection(text);
    }
  }

  int getNumber() {
    try {
      return Integer.valueOf(getText());
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  private boolean validate(String text) {
    return text.matches("[0-9]*");
  }
}
