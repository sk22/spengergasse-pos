package at.spengergasse.kai17521.sem04.lab18.task02;

import at.spengergasse.kai17521.sem04.lab18.task02.gui.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class MemoryGame extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Memory");
    Scene scene = new Scene(new RootPane());
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
