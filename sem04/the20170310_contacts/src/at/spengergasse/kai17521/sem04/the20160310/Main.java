package at.spengergasse.kai17521.sem04.the20160310;

import at.spengergasse.kai17521.sem04.the20160310.gui.AppRoot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    AppRoot root = new AppRoot();
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
