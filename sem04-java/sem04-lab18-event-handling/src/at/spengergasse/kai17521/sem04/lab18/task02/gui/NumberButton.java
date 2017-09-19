package at.spengergasse.kai17521.sem04.lab18.task02.gui;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class NumberButton extends Button {
  private int x;
  private int y;

  public NumberButton(int number, int x, int y) {
    setUserData(number);
    setFont(new Font(20));
    this.x = x;
    this.y = y;
  }

  public void click() {
    setText((getUserData()).toString());
    setDefaultButton(true);
  }

  public void unclick() {
    setText("");
    setDefaultButton(false);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
