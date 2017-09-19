package at.spengergasse.kai17521.sem04.lab18.task02.control;

import at.spengergasse.kai17521.sem04.lab18.task02.gui.MemoryGrid;
import at.spengergasse.kai17521.sem04.lab18.task02.gui.NumberButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.util.Random;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class Controller {
  private MemoryGrid grid;
  private TextArea log;
  private int previous;

  private void revealAll() {
    grid.getChildren().forEach(node -> {
      NumberButton button = (NumberButton) node;
      button.click();
    });
  }

  public Controller(MemoryGrid grid, TextArea log) {
    this.grid = grid;
    this.log = log;
    grid.addEventHandler(ActionEvent.ACTION, new ClickHandler());
  }

  private void clearAll() {
    grid.getChildren().forEach(node -> {
      NumberButton button = (NumberButton) node;
      button.unclick();
    });
  }

  public void create(int rows, int cols) {
    log.appendText(String.format("Rows: %d, Cols: %d\n", rows, cols));
    previous = 0;
    log.clear();
    grid.getChildren().clear();
    Random random = new Random();
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < cols; x++) {
        grid.add(new NumberButton(random.nextInt(50) + 1, x, y), x, y);
      }
    }
  }

  private class ClickHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      if (!(event.getTarget() instanceof NumberButton)) return;
      NumberButton button = (NumberButton) event.getTarget();
      int value = (Integer) button.getUserData();
      log.appendText(String.format(
        "%d:%d ... %d\n", button.getX(), button.getY(), value
      ));

      if (value < previous) {
        log.appendText(String.format(
          "%d is not greater or equal to %d. ", value, previous
        ));
        log.appendText("Try again.\n");
        clearAll();
      }
      previous = value;
      button.click();
    }
  }
}
