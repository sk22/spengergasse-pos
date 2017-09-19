package at.spengergasse.kai17521.sem04.lab18.task01.gui;

import at.spengergasse.kai17521.sem04.lab18.task01.control.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Window;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class RootBox extends VBox {
  public RootBox(Window window) {
    setPrefSize(500, 350);
    setPadding(new Insets(10));
    Controller controller = new Controller();

    ButtonFlow buttonFlow = new ButtonFlow(controller);
    buttonFlow.setMaxHeight(Double.MAX_VALUE);
    ScrollPane scrollPane = new ScrollPane(buttonFlow);
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);
    scrollPane.setBackground(
      new Background(new BackgroundFill(Color.TRANSPARENT, null, null))
    );
    setVgrow(scrollPane, Priority.ALWAYS);
    setMargin(scrollPane, new Insets(0, 0, 10, 0));
    getChildren().add(scrollPane);

    ButtonAdder buttonAdder = new ButtonAdder(controller, window);
    getChildren().add(buttonAdder);
  }
}
