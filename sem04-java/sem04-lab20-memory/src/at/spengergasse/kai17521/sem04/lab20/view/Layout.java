package at.spengergasse.kai17521.sem04.lab20.view;

import at.spengergasse.kai17521.sem04.lab20.controller.Controller;
import at.spengergasse.kai17521.sem04.lab20.model.Cell;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;

/**
 * @author Samuel Kaiser
 * @since 4/3/2017
 */
public class Layout extends BorderPane {
  private Controller controller;

  public Layout() {
    this.controller = new Controller(this);

    GameMenu gameMenu = new GameMenu();
    MenuBar menuBar = new MenuBar(gameMenu);
    setTop(menuBar);
    update();
  }

  public void update() {
    GameView gameView = new GameView();
    VBox.setVgrow(gameView, Priority.ALWAYS);
    HBox statusBar = new HBox(
      new Label("Fails: " + controller.getGame().getFails())
    );
    statusBar.setPadding(new Insets(10));
    VBox box = new VBox(
      gameView,
      statusBar
    );
    setCenter(box);
  }


  private class GameMenu extends Menu {
    private GameMenu() {
      setText("Game");
      MenuItem newGame = new MenuItem("New Game");
      newGame.setOnAction(controller.newGame);
      MenuItem exit = new MenuItem("Exit");
      exit.setOnAction(controller.exit);
      getItems().addAll(newGame, exit);
    }
  }

  private class GameView extends GridPane {
    private GameView() {
      Cell[][] grid = controller.getGame().getGrid();
      setHgap(5);
      setVgap(5);
      setPadding(new Insets(10));
      for (int y = 0; y < grid.length; y++) {
        Cell[] row = grid[y];
        for (int x = 0; x < row.length; x++) {
          CellView cellView = new CellView(row[x]);
          cellView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
          GameView.setHgrow(cellView, Priority.ALWAYS);
          GameView.setVgrow(cellView, Priority.ALWAYS);
          add(cellView, x, y);
        }
      }
    }
  }

  public class CellView extends Button {
    private Cell cell;

    CellView(Cell cell) {
      this.cell = cell;
      setText(cell.getValue().toString());

      switch (cell.getState()) {
        case ACTIVE:
          setDefaultButton(true);
          break;
        case HIDDEN:
          setTextFill(Color.TRANSPARENT);
          break;
        case VISIBLE:
          setDefaultButton(false);
          break;
      }

      setFont(new Font(20));
      setOnAction(controller.cellClicked);
    }

    public Cell getCell() {
      return cell;
    }
  }
}
