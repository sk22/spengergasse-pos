package at.spengergasse.kai17521.sem04.lab17;

import at.spengergasse.kai17521.sem04.lab17.gui.Calculator;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import static at.spengergasse.kai17521.sem04.lab17.Operation.*;

/**
 * @author Samuel Kaiser
 * @since 2/28/2017
 */
public class KeyHandler implements EventHandler<KeyEvent> {
  private Calculator calculator;
  public KeyHandler(Calculator calculator) {
    this.calculator = calculator;
  }

  @Override
  public void handle(KeyEvent event) {
    State state = calculator.getState();
    State update;
    switch (event.getCode()) {
      case DIGIT0: case NUMPAD0:
        update = state.addDigit("0"); break;
      case DIGIT1: case NUMPAD1:
        update = state.addDigit("1"); break;
      case DIGIT2: case NUMPAD2:
        update = state.addDigit("2"); break;
      case DIGIT3: case NUMPAD3:
        update = state.addDigit("3"); break;
      case DIGIT4: case NUMPAD4:
        update = state.addDigit("4"); break;
      case DIGIT5: case NUMPAD5:
        update = state.addDigit("5"); break;
      case DIGIT6: case NUMPAD6:
        update = state.addDigit("6"); break;
      case DIGIT7: case NUMPAD7:
        update = state.addDigit("7"); break;
      case DIGIT8: case NUMPAD8:
        update = state.addDigit("8"); break;
      case DIGIT9: case NUMPAD9:
        update = state.addDigit("9"); break;
      case ADD:
        update = state.changeOperation(ADDITION); break;
      case SUBTRACT:
        update = state.changeOperation(SUBTRACTION); break;
      case DIVIDE:
        update = state.changeOperation(DIVISION); break;
      case MULTIPLY:
        update = state.changeOperation(MULTIPLICATION); break;
      case ENTER:
        update = state.calculate(); break;
      case DECIMAL: case COMMA: case PERIOD:
        update = state.setFractional(true); break;
      case BACK_SPACE:
        update = state.deleteDigit(); break;
      case ESCAPE:
        update = state.clear(); break;
      case DELETE:
        update = state.clearEntry(); break;
      default:
        update = null;
    }

    if (update != null) calculator.updateState(update);
  }
}
