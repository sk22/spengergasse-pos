package at.spengergasse.kai17521.sem04.lab20.controller;

import at.spengergasse.kai17521.sem04.lab20.model.Cell;
import at.spengergasse.kai17521.sem04.lab20.model.Game;
import at.spengergasse.kai17521.sem04.lab20.model.State;
import at.spengergasse.kai17521.sem04.lab20.view.Layout;
import at.spengergasse.kai17521.sem04.lab20.view.NewGameDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * @author Samuel Kaiser
 * @since 4/3/2017
 */
public class Controller {
  private Layout layout;
  private Game game = new Game(4, 4);

  private static final ButtonType PLAY_AGAIN = new ButtonType(
  "Play again", ButtonBar.ButtonData.YES
  );

  public Controller(Layout layout) {
    this.layout = layout;
  }

  public EventHandler<ActionEvent> newGame = e -> newGame();

  public void newGame() {
    new NewGameDialog().showAndWait().ifPresent(dimensions -> {
      this.game = new Game(dimensions.getKey(), dimensions.getValue());
      layout.update();
    });
  }

  public EventHandler<ActionEvent> exit = e -> exit();

  public void exit() {
    System.exit(0);
  }

  public EventHandler<ActionEvent> cellClicked = e -> {
    if (!(e.getSource() instanceof Layout.CellView)) return;
    Layout.CellView cellView = (Layout.CellView) e.getSource();
    Cell cell = cellView.getCell();
    game.click(cell);
    layout.update();

    if (game.isWon()) {
      Alert alert = new Alert(
        Alert.AlertType.INFORMATION,
        "Fails: " + game.getFails() + "\n" +
          "Elapsed time: " + game.getElapsedTime().getSeconds() + " seconds",
        PLAY_AGAIN,
        ButtonType.CLOSE
      );
      alert.setTitle("You won!");
      alert.setHeaderText("Congratulations");
      alert.showAndWait().ifPresent(type -> {
        if (type == PLAY_AGAIN) {
          this.game = new Game(game.getGrid().length, game.getGrid()[0].length);
          layout.update();
        }
      });
    }
  };

  public Game getGame() {
    return game;
  }
}
