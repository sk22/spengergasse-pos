package at.spengergasse.kai17521.sem04.lab17.gui;

import at.spengergasse.kai17521.sem04.lab17.KeyHandler;
import at.spengergasse.kai17521.sem04.lab17.State;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

/**
 * @author Samuel Kaiser
 * @since 2/27/2017
 */
public class Calculator extends BorderPane {
  private State state;
  private View view = new View(this);

  public Calculator() {
    Numpad numpad = new Numpad(this);
    setPadding(new Insets(5));
    setMargin(view, new Insets(0, 0, 10, 0));
    setTop(view);
    setCenter(numpad);
    updateState(new State());
  }

  public State getState() {
    return state;
  }

  public void updateState(State state) {
    this.state = state;
    System.out.println(state);
    view.update(state);
  }
}
