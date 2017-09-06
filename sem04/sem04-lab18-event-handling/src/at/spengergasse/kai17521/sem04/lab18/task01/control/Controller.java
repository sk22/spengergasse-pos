package at.spengergasse.kai17521.sem04.lab18.task01.control;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class Controller {
  private List<Button> buttons = new ArrayList<>();
  private Pane pane;

  public void setPane(Pane pane) {
    this.pane = pane;
  }

  public void add(Button button) {
    buttons.add(button);
    update();
  }

  public void remove(Button button) {
    buttons.remove(button);
    update();
  }

  private void update() {
    pane.getChildren().clear();
    pane.getChildren().addAll(buttons);
  }
}
