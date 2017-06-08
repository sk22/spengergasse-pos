package at.spengergasse.kai17521.sem04.lab20;

import at.spengergasse.kai17521.sem04.lab20.view.Layout;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 4/3/2017
 */
public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Parent layout = new Layout();
    primaryStage.setScene(new Scene(layout));
    primaryStage.setTitle("Memory");
    primaryStage.setMinHeight(330);
    primaryStage.setMinWidth(270);
    primaryStage.show();
  }
}
