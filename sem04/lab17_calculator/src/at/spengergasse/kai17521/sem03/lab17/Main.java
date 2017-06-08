package at.spengergasse.kai17521.sem04.lab17;

import at.spengergasse.kai17521.sem04.lab17.gui.Calculator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 2/27/2017
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Calculator");
    BorderPane root = new BorderPane(
      new Calculator()
    );
    Scene scene = new Scene(root);
    primaryStage.setMinHeight(410);
    primaryStage.setMinWidth(300);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
