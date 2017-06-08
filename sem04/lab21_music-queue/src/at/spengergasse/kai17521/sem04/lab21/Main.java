package at.spengergasse.kai17521.sem04.lab21;

import at.spengergasse.kai17521.sem04.lab21.view.RootPane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 4/21/2017
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Parent root = new RootPane(primaryStage);
    Scene scene = new Scene(root);
    primaryStage.setTitle("Music");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
