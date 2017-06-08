package at.spengergasse.kai17521.sem04.music;

import at.spengergasse.kai17521.sem04.music.controller.AppController;
import at.spengergasse.kai17521.sem04.music.view.AppPane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    final AppController controller = new AppController(primaryStage);
    primaryStage.setTitle("Music");
    final Scene scene = new Scene(new AppPane(controller), 1000, 700);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
