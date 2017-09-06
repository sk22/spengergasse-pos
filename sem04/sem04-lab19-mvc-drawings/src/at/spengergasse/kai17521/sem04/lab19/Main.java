package at.spengergasse.kai17521.sem04.lab19;

import at.spengergasse.kai17521.sem04.lab19.view.AppRoot;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Parent root = new AppRoot(primaryStage);
    Scene scene = new Scene(root);
    primaryStage.setMinHeight(500);
    primaryStage.setMinWidth(500);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Drawing");
    primaryStage.show();
  }
}
